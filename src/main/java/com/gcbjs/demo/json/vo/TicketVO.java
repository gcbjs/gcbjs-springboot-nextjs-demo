package com.gcbjs.demo.json.vo;

import com.gcbjs.demo.mappers.model.TicketInfo;
import com.gcbjs.demo.util.DateTimeUtils;
import com.gcbjs.demo.util.MoneyUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName TicketVO
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/1/24 16:00
 * @Version 1.0
 **/
@Data
public class TicketVO implements Serializable {

    Long ticketId;

    /**
     * 工单状态
     */
    String ticketStatusDesc;

    /**
     * 派单时间
     */
    String dispatchTimeDesc;

    /**
     * 优先级
     */
    Integer priority;

    /**
     * 创建时间
     */
    String createTimeStr;
    /**
     * 接单人名称
     */
    String receiverName;

    /**
     * 完成时间
     */
    String finishTimeStr;


    /**
     * 贷款金额(元)
     */
    String loanAmountStr;

    /**
     * 用户级别
     */
    String userLevelDesc;


    /**
     * 车商类型（精品车商、普通车商）
     */
    String dealerTypeDesc;

    /**
     * 紧急程度（1~9：:越大越紧急）
     */
    String urgentLevelDesc;


    public static TicketVO of(TicketInfo ticketInfo){
        if (ticketInfo == null) {
            return null;
        }
        TicketVO vo = new TicketVO();
        vo.setTicketId(ticketInfo.getTicketId());
        vo.setTicketStatusDesc(ticketInfo.getTicketStatus().getDesc());
        vo.setDispatchTimeDesc(DateTimeUtils.formatLocalDateTime(ticketInfo.getDispatchTime(),"yyyy-MM-dd HH:mm:ss"));
        vo.setPriority(ticketInfo.getPriority());
        vo.setCreateTimeStr(DateTimeUtils.formatLocalDateTime(ticketInfo.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
        vo.setReceiverName(ticketInfo.getReceiverName());
        vo.setFinishTimeStr(DateTimeUtils.formatLocalDateTime(ticketInfo.getFinishTime(),"yyyy-MM-dd HH:mm:ss"));
        vo.setLoanAmountStr(MoneyUtils.formatAmountInYuan(ticketInfo.getLoanAmount()));
        vo.setUserLevelDesc(ticketInfo.getUserLevel().getDesc());
        vo.setDealerTypeDesc(ticketInfo.getDealerType().getDesc());
        vo.setUrgentLevelDesc(ticketInfo.getUrgentLevel().getDesc());
        return vo;
    }
}
