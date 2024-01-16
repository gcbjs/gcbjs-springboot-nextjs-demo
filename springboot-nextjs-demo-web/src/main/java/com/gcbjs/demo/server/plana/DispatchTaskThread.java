package com.gcbjs.demo.server.plana;

import com.gcbjs.demo.mappers.model.UserInfo;
import com.gcbjs.demo.server.plana.cmd.DispatchTaskCmd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;

/**
 * @ClassName DispatchTaskThread
 * @Description 派单线程
 * @Author yuzhangbin
 * @Date 2024/1/16 10:05
 * @Version 1.0
 **/
@Slf4j
public class DispatchTaskThread implements Runnable {

    private TicketAppService ticketAppService;

    private UserQueryService userQueryService;

    private Long ticketId;

    public DispatchTaskThread(Long ticketId,
                              TicketAppService ticketAppService,
                              UserQueryService userAppService) {
        this.ticketId = ticketId;
        this.ticketAppService = ticketAppService;
        this.userQueryService = userAppService;
    }

    @Override
    public void run() {
        log.info("开始派单");
        try {
            List<UserInfo> freeUsers = userQueryService.getFreeUsers();
            if (CollectionUtils.isEmpty(freeUsers)) {
                log.info("没有空闲的业务员,工单重新放入队列");
                // 等待五秒钟重新派单
                Thread.sleep(5000);
                TicketQueue.getInstance().put(this.ticketId);
                return;
            }
            //开始选择业务员
            UserInfo userInfo = dispatchUser(freeUsers);
            DispatchTaskCmd dispatchTaskCmd = DispatchTaskCmd.builder()
                    .userId(userInfo.getUserId())
                    .ticketId(this.ticketId)
                    .build();
            Boolean dispatch = ticketAppService.dispatch(dispatchTaskCmd);
            if (dispatch) {
                log.info("派单成功,工单id:{},业务员id:{}", this.ticketId, userInfo.getUserId());
                return;
            }
            log.info("派单失败,重新放入队列");
            TicketQueue.getInstance().put(this.ticketId);
        }catch (Exception e){
            log.error("派单异常,重新放入队列",e);
            TicketQueue.getInstance().put(this.ticketId);
        }
    }

    /**
     * 分配业务员按照当前时间
     */
    private UserInfo dispatchUser(List<UserInfo> freeUsers) {
        freeUsers.sort(Comparator.comparing(UserInfo::getUpdateTime));
        return freeUsers.get(0);
    }
}
