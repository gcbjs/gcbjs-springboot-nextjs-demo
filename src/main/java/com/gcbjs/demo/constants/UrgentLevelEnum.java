package com.gcbjs.demo.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
  *@ClassName UrgentLevelEnum
  *@Description 紧急程度枚举
  *@Author yuzhangbin
  *@Date 2024/1/24 14:08
  *@Version 1.0
**/
@Getter
@AllArgsConstructor
public enum UrgentLevelEnum {

    //紧急
    URGENT("紧急"),
    //普通
    NORMAL("普通"),
    ;
    private String desc;
}
