package com.gcbjs.demo.server.plana;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @ClassName ScheduledTaskService
 * @Description 定时任务
 * @Author yuzhangbin
 * @Date 2024/1/17 16:39
 * @Version 1.0
 **/
@Component
public class ScheduledTaskService {

//    /**
//     * 定时任务
//     *
//     * @param
//     * @return void
//     * @date: 2024/1/17 16:41
//     */
//    @Scheduled(fixedRate = 20000)
//    public void processAuditLogs() {
//        AuditLog auditLog = TicketQueue.getInstance().takeAuditLog();
//    }
//
//    private boolean isOverTwoHours(LocalDateTime dispatchTime) {
//        // 判断当前时间与派单时间是否大于2小时
//        LocalDateTime now = LocalDateTime.now();
//        return now.minusHours(2).isAfter(dispatchTime);
//    }
}
