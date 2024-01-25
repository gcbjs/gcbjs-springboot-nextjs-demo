package com.gcbjs.demo.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
  *@ClassName UserLevelEnum
  *@Description 用户等级
  *@Author yuzhangbin
  *@Date 2024/1/24 13:49
  *@Version 1.0
**/
@Getter
@AllArgsConstructor
public enum UserLevelEnum {

    //高风险
    HIGH_RISK("高风险"),
    //一般
    NORMAL("一般"),
    //优秀
    EXCELLENT("优秀"),
    ;
    private String desc;
}
