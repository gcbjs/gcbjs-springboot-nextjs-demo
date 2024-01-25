package com.gcbjs.demo.json;

import com.gcbjs.demo.constants.WorkStatusEnum;
import com.gcbjs.demo.json.param.CreateTicketParam;
import com.gcbjs.demo.json.param.QueryTicketParam;
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
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(path = "/page", method = RequestMethod.GET)
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



    @RequestMapping(path = "/userList", method = RequestMethod.GET)
    public List<UserInfoVO> userList() {
        List<UserInfo> list = userInfoMapper.findAll();
        return list.stream().map(UserInfoVO::of).toList();
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


    /**
     * 实时获取用户队列
     */
    @RequestMapping(path = "/getUserQueue", method = RequestMethod.GET)
    public Map<WorkStatusEnum, List<UserInfoVO>> getUserQueue() {
        List<UserInfo> all = userInfoMapper.findAll();
        //按照状态分组
        return all.stream()
                .collect(Collectors.groupingBy(UserInfo::getWorkStatus,
                        Collectors.mapping(UserInfoVO::of, Collectors.toList())));
    }

    @RequestMapping(path = "/finishTicket", method = RequestMethod.POST)
    public void finishTicket(@RequestParam("ticketId") Long ticketId) {
        ticketAppService.finish(ticketId);
    }

    /**
     * 实时获取工单数据
     * 待分配、处理中、已处理
     */
    @RequestMapping(path = "/getDailyDataInRealTime", method = RequestMethod.GET)
    public Map<String, Integer> getDailyDataInRealTime() {

        return null;
    }

    /**
     * 工单手动改派
     */


    /**
     * 获取指定日期的值班人信息
     *
     * @param targetDate
     * @return java.util.List<java.lang.Long>
     * @date: 2024/1/18 16:18
     */
    @RequestMapping(path = "/getUserIdsByTargetDate", method = RequestMethod.GET)
    public List<UserInfoVO> getUserIdsByTargetDate(@RequestParam("targetDate") String targetDate) {
        List<UserInfo> userInfos = scheduleAppService.getUserIdsByDate(LocalDate.parse(targetDate));
        return userInfos.stream().map(UserInfoVO::of).toList();
    }




}
