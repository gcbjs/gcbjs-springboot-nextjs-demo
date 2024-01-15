package com.gcbjs.demo.mappers.model;

import com.gcbjs.demo.constants.TicketStatusEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName AuditLogEntity
 * @Description 工单信息
 * @Author yuzhangbin
 * @Date 2024/1/15 14:34
 * @Version 1.0
 **/
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketInfo {

    Long ticketId;

    /**
     * 工单状态
     */
    TicketStatusEnum ticketStatus;

    /**
     * 派单时间
     */
    LocalDateTime dispatchTime;

    /**
     * 优先级
     */
    Integer priority;

    /**
     * 创建时间
     */
    LocalDateTime createTime;

    /**
     * 更新时间
     */
    LocalDateTime updateTime;

    /**
     * 接单人id
     */
    Long receiverId;
    /**
     * 接单人名称
     */
    String receiverName;

    protected TicketInfo() {
    }

    public TicketInfo(TicketStatusEnum ticketStatus, Integer priority) {
        this.ticketStatus = ticketStatus;
        this.priority = priority;
    }

    /**
     * 创建工单
     *
     * @param priority
     * @return com.gcbjs.demo.repository.model.TicketEntity
     * @date: 2024/1/15 15:41
     */
    public static TicketInfo create(Integer priority) {
        return new TicketInfo(TicketStatusEnum.WAITING, priority);
    }


    /**
     * 分配工单
     */
    public void dispatch(String receiverName, Long receiverId) {
        this.ticketStatus = TicketStatusEnum.DEALING;
        this.dispatchTime = LocalDateTime.now();
        this.receiverName = receiverName;
        this.receiverId = receiverId;
    }

    /**
     * 重新分配
     */
    public void reDispatch(String receiverName, Long receiverId) {
        this.dispatchTime = LocalDateTime.now();
        this.receiverName = receiverName;
        this.receiverId = receiverId;
    }

    /**
     * 工单已处理
     */
    public void handled() {
        this.ticketStatus = TicketStatusEnum.HANDLED;
    }

    /**
     * 判断工单处理是否已超时，超时规则为：当前时间-工单派单时间大于10分钟
     */
    public boolean isTimeout() {
        return LocalDateTime.now().minusMinutes(10).isAfter(this.dispatchTime);
    }

    /**
     * 等待分配超时后提升优先级。提升规则为：当前时间-创建时间大于10分钟，且工单状态为待分配
     */
    public void increasePriority() {
        if (TicketStatusEnum.WAITING != this.ticketStatus) {
            return;
        }
        if (LocalDateTime.now().minusMinutes(10).isAfter(this.createTime)) {
            this.priority++;
        }
    }

}
