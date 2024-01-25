package com.gcbjs.demo.json.param;

import com.gcbjs.demo.constants.DealerTypeEnum;
import com.gcbjs.demo.constants.UrgentLevelEnum;
import com.gcbjs.demo.constants.UserLevelEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName CreateTicketParam
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/1/24 14:30
 * @Version 1.0
 **/
@Data
public class CreateTicketParam implements Serializable {

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
