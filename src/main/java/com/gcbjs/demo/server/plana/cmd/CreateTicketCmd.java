package com.gcbjs.demo.server.plana.cmd;

import com.gcbjs.demo.constants.DealerTypeEnum;
import com.gcbjs.demo.constants.UrgentLevelEnum;
import com.gcbjs.demo.constants.UserLevelEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName CreateTicketCmd
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/1/24 14:32
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTicketCmd {

    /**
     * 贷款金额
     */
    Long loanAmount;

    /**
     * 用户级别
     */
    UserLevelEnum userLevel;

    /**
     * 车商类型（精品车商、普通车商）
     */
    DealerTypeEnum dealerType;

    /**
     * 紧急程度
     */
    UrgentLevelEnum urgentLevel;

}
