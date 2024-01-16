package com.gcbjs.demo.server.plana;

import com.gcbjs.demo.mappers.TicketInfoMapper;
import com.gcbjs.demo.mappers.UserInfoMapper;
import com.gcbjs.demo.mappers.model.TicketInfo;
import com.gcbjs.demo.mappers.model.UserInfo;
import com.gcbjs.demo.server.plana.cmd.DispatchTaskCmd;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName TicketAppService
 * @Description 工单应用服务
 * @Author yuzhangbin
 * @Date 2024/1/16 09:51
 * @Version 1.0
 **/
@Slf4j
@Service
public class TicketAppService {

    @Resource
    private TicketInfoMapper ticketInfoMapper;
    @Resource
    private UserInfoMapper userInfoMapper;
    /**
     * 监听工单请求，创建派单工单
     * 调用派单主线程，去分配任务
     */
    @Transactional(rollbackFor = Exception.class)
    public void receiveTicket() {
        TicketInfo ticketInfo = TicketInfo.create();
        ticketInfoMapper.insert(ticketInfo);
        //加入工单队列
        TicketQueue.getInstance().put(ticketInfo.getTicketId());
    }

    /**
     * 派单
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean dispatch(DispatchTaskCmd dispatchTaskCmd) {
        UserInfo userInfo = userInfoMapper.findByUserId(dispatchTaskCmd.getUserId());
        if (!userInfo.checkNoWorking()) {
            log.error("业务员状态不正确，业务员id:{},重新分配", userInfo.getUserId());
            return Boolean.FALSE;
        }
        TicketInfo ticketInfo = ticketInfoMapper.findByTicketId(dispatchTaskCmd.getTicketId());
        if (!ticketInfo.canBeDispatch()) {
            log.error("工单状态不正确，工单id:{}", ticketInfo.getTicketId());
            return Boolean.TRUE;
        }
        ticketInfo.dispatch(userInfo.getName(),userInfo.getUserId());
        userInfo.working();
        ticketInfoMapper.update(ticketInfo);
        userInfoMapper.updateStatus(userInfo);
        return Boolean.TRUE;
    }


    public List<TicketInfo> getWaitingList() {
        return ticketInfoMapper.findWaitingTickets();
    }


    /**
    * 完成工单
    * @param ticketId
    * @return void
    * @date: 2024/1/16 21:54
    */
    @Transactional(rollbackFor = Exception.class)
    public void finish(Long ticketId) {
        TicketInfo ticketInfo = ticketInfoMapper.findByTicketId(ticketId);
        ticketInfo.handled();
        UserInfo userInfo = userInfoMapper.findByUserId(ticketInfo.getReceiverId());
        userInfo.free();
        ticketInfoMapper.update(ticketInfo);
        userInfoMapper.updateStatus(userInfo);
    }

}
