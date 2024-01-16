package com.gcbjs.demo.json;

import com.gcbjs.demo.constants.WorkStatusEnum;
import com.gcbjs.demo.json.vo.UserInfoVO;
import com.gcbjs.demo.mappers.UserInfoMapper;
import com.gcbjs.demo.mappers.model.TicketInfo;
import com.gcbjs.demo.mappers.model.UserInfo;
import com.gcbjs.demo.server.plana.TicketAppService;
import com.gcbjs.demo.server.plana.TicketQueue;
import jakarta.annotation.Resource;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
@RequestMapping("/api")
public class DemoController {

    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private TicketAppService ticketAppService;



    @RequestMapping(path = "/list",method = RequestMethod.GET)
    public List<String> getList() {
        return List.of("a","b","c");
    }


    @RequestMapping(path = "/userList",method = RequestMethod.GET)
    public List<UserInfoVO> userList() {
        List<UserInfo> list = userInfoMapper.findAll();
        return list.stream().map(UserInfoVO::of).toList();
    }


    /**
     * 手动生成工单
     */
    @RequestMapping(path = "/createTicket",method = RequestMethod.POST)
    public void createTicket() {
        ticketAppService.receiveTicket();
    }


    /**
     * 启动后，补全等待队列
     */
    @RequestMapping(path = "/initWaitingTickets",method = RequestMethod.POST)
    public void initWaitingTickets() {
        List<TicketInfo> waitingList = ticketAppService.getWaitingList();
        if (CollectionUtils.isEmpty(waitingList)) {
            return;
        }
        TicketQueue.getInstance().putAll(waitingList.stream().map(TicketInfo::getTicketId).toList());
    }




    /**
     * 实时获取用户队列
     */
    @RequestMapping(path = "/getUserQueue",method = RequestMethod.GET)
    public Map<WorkStatusEnum,List<UserInfoVO>> getUserQueue() {
        List<UserInfo> all = userInfoMapper.findAll();
        //按照状态分组
        return all.stream()
                .collect(Collectors.groupingBy(UserInfo::getWorkStatus,
                        Collectors.mapping(UserInfoVO::of, Collectors.toList())));
    }

    @RequestMapping(path = "/finishTicket",method = RequestMethod.POST)
    public void finishTicket(@RequestParam("ticketId") Long ticketId) {
        ticketAppService.finish(ticketId);
    }

    /**
     * 实时获取每日工单数据
     * 待分配、处理中、已处理
     */

    /**
     * 工单手动改派
     */



}
