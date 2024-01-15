package com.gcbjs.demo.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
  *@ClassName TicketStatusEnum
  *@Description 工单状态
  *@Author yuzhangbin
  *@Date 2024/1/15 15:37
  *@Version 1.0
**/
@AllArgsConstructor
@Getter
public enum TicketStatusEnum {

    WAITING("待分配"),
    DEALING("处理中"),
    //已处理
    HANDLED("已处理"),
    //已失效
    INVALID("已失效"),
    ;

    private String desc;
}
