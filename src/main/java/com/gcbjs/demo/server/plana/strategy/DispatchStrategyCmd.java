package com.gcbjs.demo.server.plana.strategy;

import com.gcbjs.demo.constants.DealerTypeEnum;
import com.gcbjs.demo.constants.UrgentLevelEnum;
import com.gcbjs.demo.constants.UserLevelEnum;
import com.gcbjs.demo.mappers.model.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName DispatchStrategyCmd
 * @Description 分配请求参数
 * @Author yuzhangbin
 * @Date 2024/1/24 11:09
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DispatchStrategyCmd {

    /**
     * 待分配用户
     */
    private List<UserInfo> users;

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
