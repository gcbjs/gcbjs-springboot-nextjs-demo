package com.gcbjs.demo.server.plana;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName AuditLog
 * @Description 审核记录实体
 * @Author yuzhangbin
 * @Date 2024/1/17 15:56
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
public class AuditLog {

    /**
     * 工单id
     */
    private Long ticketId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 派单时间
     */
    private LocalDateTime dispatchTime;

}
