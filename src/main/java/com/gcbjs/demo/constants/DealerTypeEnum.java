package com.gcbjs.demo.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
  *@ClassName DealerTypeEnum
  *@Description 车商类型
  *@Author yuzhangbin
  *@Date 2024/1/24 14:00
  *@Version 1.0
**/
@Getter
@AllArgsConstructor
public enum DealerTypeEnum {


    //精品
    EXCELLENT("精品"),
    //普通
    NORMAL("普通"),
    //高风险
    HIGH_RISK("高风险"),
    ;
     private String desc;
}
