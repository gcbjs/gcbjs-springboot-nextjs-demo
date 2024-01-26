package com.gcbjs.demo.json;

import com.alibaba.fastjson.JSON;
import com.gcbjs.demo.constants.TicketStatusEnum;
import com.gcbjs.demo.json.param.CountTicketParam;
import com.gcbjs.demo.json.param.CreateTicketParam;
import com.gcbjs.demo.json.param.QueryTicketParam;
import com.gcbjs.demo.json.param.QueryUserParam;
import com.gcbjs.demo.json.vo.TicketVO;
import com.gcbjs.demo.json.vo.UserInfoVO;
import com.gcbjs.demo.mappers.TicketInfoMapper;
import com.gcbjs.demo.mappers.UserInfoMapper;
import com.gcbjs.demo.mappers.model.TicketInfo;
import com.gcbjs.demo.mappers.model.UserInfo;
import com.gcbjs.demo.server.ScheduleAppService;
import com.gcbjs.demo.server.plana.TicketAppService;
import com.gcbjs.demo.server.plana.TicketQueue;
import com.gcbjs.demo.server.plana.WaitLog;
import com.gcbjs.demo.server.plana.cmd.CreateTicketCmd;
import com.gcbjs.demo.util.Page;
import com.gcbjs.demo.util.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName DemoController
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/1/12 17:58
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/ticketApi")
public class TicketController {

    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private TicketAppService ticketAppService;
    @Resource
    private ScheduleAppService scheduleAppService;
    @Resource
    private TicketInfoMapper ticketInfoMapper;


    /**
    * 分页查询工单列表
     *
    * @param param
    * @return com.gcbjs.demo.util.Result<com.gcbjs.demo.util.Page<com.gcbjs.demo.json.vo.TicketVO>>
    * @date: 2024/1/24 17:20
    */
    @RequestMapping(path = "/ticketpage", method = RequestMethod.POST)
    public Result<Page<TicketVO>> page(@RequestBody QueryTicketParam param) {
        Page<TicketInfo> tickets = ticketAppService.getTickets(param);
        return Result.success(new Page<>(tickets.getTotalNumber(),
                tickets.getCurrentIndex(),
                tickets.getPageSize(),
                tickets.getItems().stream().map(TicketVO::of).toList()));
    }


    /**
     * 统计不同状态的工单数量
     * @return
     */
    @RequestMapping(path = "/count", method = RequestMethod.GET)
    public Result<Map<String, Integer>> count() {
        List<String> list = ticketInfoMapper.findAllGroupByStatus();
        return Result.success(list.stream()
                .collect(Collectors.toMap(
                        // 使用 Function.identity() 作为 key 映射
                        // 使用 Collectors.counting() 作为 value 映射，表示相同 key 的数量
                        key -> key,
                        value -> 1,
                        Integer::sum
                )));
    }



    @RequestMapping(path = "/userpage", method = RequestMethod.POST)
    public Result<Page<UserInfoVO>> userPage(@RequestBody QueryUserParam param) {
        PageInfo<UserInfo> pageInfo = PageHelper.startPage(param.getPageIndex(), param.getPageSize()).doSelectPageInfo(
                () -> userInfoMapper.findList(param)
        );
        if (CollectionUtils.isEmpty(pageInfo.getList())) {
            return Result.success(new Page<>((int)pageInfo.getTotal(),
                    pageInfo.getPageNum(),
                    pageInfo.getPageSize(),
                    List.of()));
        }
        return Result.success(new Page<>((int)pageInfo.getTotal(),
                pageInfo.getPageNum(),
                pageInfo.getPageSize(),pageInfo.getList().stream().map(UserInfoVO::of).toList()));
    }


    /**
     * 手动生成工单
     */
    @RequestMapping(path = "/createTicket", method = RequestMethod.POST)
    public void createTicket(@RequestBody CreateTicketParam param) {
        CreateTicketCmd cmd = CreateTicketCmd.builder()
                .dealerType(param.getDealerType())
                .loanAmount(param.getLoanAmount())
                .urgentLevel(param.getUrgentLevel())
                .userLevel(param.getUserLevel())
                .build();
        ticketAppService.receiveTicket(cmd);
    }


    /**
     * 启动后，补全等待队列
     */
    @RequestMapping(path = "/initWaitingTickets", method = RequestMethod.POST)
    public void initWaitingTickets() {
        List<TicketInfo> waitingList = ticketAppService.getWaitingList();
        if (CollectionUtils.isEmpty(waitingList)) {
            return;
        }
        TicketQueue.getInstance().putAllTicket(waitingList.stream().map(v ->
                WaitLog.builder().ticketId(v.getTicketId())
                        .dealerType(v.getDealerType())
                        .urgentLevel(v.getUrgentLevel())
                        .userLevel(v.getUserLevel())
                        .loanAmount(v.getLoanAmount())
                        .build()).collect(Collectors.toList()));
    }


//    /**
//     * 实时获取用户队列
//     */
//    @RequestMapping(path = "/getUserQueue", method = RequestMethod.GET)
//    public Map<WorkStatusEnum, List<UserInfoVO>> getUserQueue() {
//        List<UserInfo> all = userInfoMapper.findList();
//        //按照状态分组
//        return all.stream()
//                .collect(Collectors.groupingBy(UserInfo::getWorkStatus,
//                        Collectors.mapping(UserInfoVO::of, Collectors.toList())));
//    }

    /**
    * 统计当日的工单数量
    * @param 
    * @return com.gcbjs.demo.util.Result<java.lang.Long>
    * @date: 2024/1/25 16:50
    */
    @RequestMapping(path = "/countTodayTicket", method = RequestMethod.GET)
    public Result<Long> countTodayTicket(@RequestParam("day") String day) {
        return Result.success(ticketInfoMapper.countTodayTicket(day));
    }


    @RequestMapping(path = "/finishTicket", method = RequestMethod.POST)
    public void finishTicket(@RequestParam("ticketId") Long ticketId) {
        ticketAppService.finish(ticketId);
    }

    /**
     * 获取指定日期的值班人信息
     *
     * @param targetDate
     * @return java.util.List<java.lang.Long>
     * @date: 2024/1/18 16:18
     */
    @RequestMapping(path = "/getUserIdsByTargetDate", method = RequestMethod.GET)
    public Result<List<UserInfoVO>> getUserIdsByTargetDate(@RequestParam("targetDate") String targetDate) {
        List<UserInfo> userInfos = scheduleAppService.getUserIdsByDate(LocalDate.parse(targetDate));
        if (CollectionUtils.isEmpty(userInfos)) {
            return Result.success(List.of());
        }
        return Result.success(userInfos.stream().map(UserInfoVO::of).toList());
    }

    @RequestMapping(path = "/getTicketStatusCountForCurrentWeek", method = RequestMethod.GET)
    public Result<Map<String,Long>> getTicketStatusCountForCurrentWeek(){

        // 获取本周的起始日期
        LocalDate startOfWeek = LocalDate.now().with(DayOfWeek.MONDAY);

        // 获取本周的结束日期
        LocalDate endOfWeek = LocalDate.now().with(DayOfWeek.SUNDAY);
        List<Map<String, Object>> count = ticketInfoMapper.getTicketStatusCountForCurrentWeek(startOfWeek, endOfWeek);
        return Result.success(countWeekNum(count));
    }

    private Map<String,Long> countWeekNum(List<Map<String, Object>> count) {
        if (CollectionUtils.isEmpty(count)) {
            return null;
        }
        Map<String,Long> result = Maps.newHashMap();
        long total = 0L;
        for (Map<String, Object> map : count) {
            total += (long) map.get("count");
            if (TicketStatusEnum.HANDLED.name().equals(map.get("ticket_status").toString())) {
                result.put(map.get("ticket_status").toString(), (Long) map.get("count"));
            }
        }
        result.put("TOTAL", total);
        return result;
    }






}
