package com.gcbjs.demo.server.plana;

import com.gcbjs.demo.constants.WorkStatusEnum;
import com.gcbjs.demo.mappers.model.ScheduleInfo;
import com.gcbjs.demo.mappers.model.TicketInfo;
import com.gcbjs.demo.mappers.model.UserInfo;
import com.gcbjs.demo.server.ScheduleAppService;
import com.gcbjs.demo.server.plana.cmd.DispatchTaskCmd;
import com.gcbjs.demo.server.plana.strategy.DispatchStrategy;
import com.gcbjs.demo.server.plana.strategy.DispatchStrategyCmd;
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

    private DispatchStrategy dispatchStrategy;

    private WaitLog waitLog;

    public DispatchTaskThread(WaitLog waitLog,
                              TicketAppService ticketAppService,
                              UserQueryService userAppService,
                              ScheduleAppService scheduleAppService,
                              DispatchStrategy dispatchStrategy) {
        this.waitLog = waitLog;
        this.ticketAppService = ticketAppService;
        this.userQueryService = userAppService;
        this.scheduleAppService = scheduleAppService;
        this.dispatchStrategy = dispatchStrategy;
    }

    @Override
    public void run() {
        log.info("开始派单");
        try {
            //获取当前值班人员
            List<ScheduleInfo> scheduleInfos = scheduleAppService.getUserIdsByDate(LocalDate.now());
            if (CollectionUtils.isEmpty(scheduleInfos)) {
                log.info("当天没有值班人员,工单重新放入队列");
                // 等待五秒钟重新派单-会有队列积压风险
                Thread.sleep(5000);
                TicketQueue.getInstance().putTicket(this.waitLog);
                return;
            }
            List<UserInfo> users = userQueryService.getUsersByUserIds(scheduleInfos.stream()
                    .map(ScheduleInfo::getUserId).toList());
            List<UserInfo> freeUsers = users.stream()
                    .filter(v -> v.getWorkStatus() == WorkStatusEnum.FREE).toList();
            if (CollectionUtils.isEmpty(freeUsers)) {
                log.info("没有空闲的业务员,工单重新放入队列");
                // 等待五秒钟重新派单-会有队列积压风险
                Thread.sleep(5000);
                TicketQueue.getInstance().putTicket(this.waitLog);
                return;
            }
            //开始选择业务员
            UserInfo userInfo = dispatchUser(freeUsers, waitLog);
            DispatchTaskCmd dispatchTaskCmd = DispatchTaskCmd.builder()
                    .userId(userInfo.getUserId())
                    .ticketId(this.waitLog.getTicketId())
                    .build();
            TicketInfo ticketInfo = ticketAppService.dispatch(dispatchTaskCmd);
            if (Objects.nonNull(ticketInfo)) {
                log.info("派单成功,工单id:{},业务员id:{}", this.waitLog.getTicketId(), userInfo.getUserId());
                TicketQueue.getInstance().putAuditLog(AuditLog.builder()
                        .dispatchTime(ticketInfo.getDispatchTime())
                        .ticketId(ticketInfo.getTicketId())
                        .userId(ticketInfo.getReceiverId())
                        .build());
                return;
            }
            log.info("派单失败,重新放入队列");
            Thread.sleep(5000);
            TicketQueue.getInstance().putTicket(this.waitLog);
        } catch (Exception e) {
            log.error("派单异常,重新放入队列", e);
            TicketQueue.getInstance().putTicket(this.waitLog);
        }
    }

    /**
     * 分配业务员按照当前时间
     */
    private UserInfo dispatchUser(List<UserInfo> freeUsers, WaitLog waitLog) {
        //可以自定义策略
        return dispatchStrategy.dispatch(DispatchStrategyCmd.builder()
                        .dealerType(waitLog.getDealerType())
                        .users(freeUsers)
                        .loanAmount(waitLog.getLoanAmount())
                        .userLevel(waitLog.getUserLevel())
                        .urgentLevel(waitLog.getUrgentLevel())
                .build());
    }
}
