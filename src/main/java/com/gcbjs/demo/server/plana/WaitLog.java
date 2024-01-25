package com.gcbjs.demo.server.plana;

import com.gcbjs.demo.constants.DealerTypeEnum;
import com.gcbjs.demo.constants.UrgentLevelEnum;
import com.gcbjs.demo.constants.UserLevelEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName WaitLog
 * @Description 等待分配的工单实体
 * @Author yuzhangbin
 * @Date 2024/1/24 11:21
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WaitLog {

    /**
     * 工单id
     */
    private Long ticketId;

    /**
     * 贷款金额
     */
    private Long loanAmount;

    /**
     * 用户级别
     */
    private UserLevelEnum userLevel;


    /**
     * 车商类型（精品车商、普通车商）
     */
    private DealerTypeEnum dealerType;

    /**
     * 紧急程度（1~9：:越大越紧急）
     */
    private UrgentLevelEnum urgentLevel;


}
