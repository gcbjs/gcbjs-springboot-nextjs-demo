package com.gcbjs.demo.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
  *@ClassName ShiftInfoEnum
  *@Description 
  *@Author yuzhangbin
  *@Date 2024/1/18 14:37
  *@Version 1.0
**/
@Getter
@AllArgsConstructor
public enum ShiftInfoEnum {

    //全天
    ALL("全天"),
    ;
    private String desc;
}
