package com.gcbjs.demo.server.plana;

import com.gcbjs.demo.mappers.model.TicketInfo;
import com.gcbjs.demo.mappers.model.UserInfo;
import com.gcbjs.demo.server.ScheduleAppService;
import com.gcbjs.demo.server.plana.cmd.DispatchTaskCmd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

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

    private ScheduleAppService scheduleAppService;

    private Long ticketId;

    public DispatchTaskThread(Long ticketId,
                              TicketAppService ticketAppService,
                              UserQueryService userAppService,
                              ScheduleAppService scheduleAppService) {
        this.ticketId = ticketId;
        this.ticketAppService = ticketAppService;
        this.userQueryService = userAppService;
        this.scheduleAppService = scheduleAppService;
    }

    @Override
    public void run() {
        log.info("开始派单");
        try {
            //获取当前值班人员
            List<UserInfo> userInfos = scheduleAppService.getUserIdsByDate(LocalDate.now());
            if (CollectionUtils.isEmpty(userInfos)) {
                log.info("当天没有值班人员,工单重新放入队列");
                // 等待五秒钟重新派单
                Thread.sleep(5000);
                TicketQueue.getInstance().putTicket(this.ticketId);
                return;
            }
            List<UserInfo> freeUsers = userQueryService.getUsersByUserIds(
                    userInfos.stream().map(UserInfo::getUserId).toList());
            if (CollectionUtils.isEmpty(freeUsers)) {
                log.info("没有空闲的业务员,工单重新放入队列");
                // 等待五秒钟重新派单
                Thread.sleep(5000);
                TicketQueue.getInstance().putTicket(this.ticketId);
                return;
            }
            //开始选择业务员
            UserInfo userInfo = dispatchUser(freeUsers);
            DispatchTaskCmd dispatchTaskCmd = DispatchTaskCmd.builder()
                    .userId(userInfo.getUserId())
                    .ticketId(this.ticketId)
                    .build();
            TicketInfo ticketInfo = ticketAppService.dispatch(dispatchTaskCmd);
            if (Objects.nonNull(ticketInfo)) {
                log.info("派单成功,工单id:{},业务员id:{}", this.ticketId, userInfo.getUserId());
                TicketQueue.getInstance().putAuditLog(AuditLog.builder()
                                .dispatchTime(ticketInfo.getDispatchTime())
                                .ticketId(ticketInfo.getTicketId())
                                .userId(ticketInfo.getReceiverId())
                        .build());
                return;
            }
            log.info("派单失败,重新放入队列");
            TicketQueue.getInstance().putTicket(this.ticketId);
        }catch (Exception e){
            log.error("派单异常,重新放入队列",e);
            TicketQueue.getInstance().putTicket(this.ticketId);
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
