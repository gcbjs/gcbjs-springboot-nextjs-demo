package com.gcbjs.demo.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
  *@ClassName AbilityLevelEnum
  *@Description 
  *@Author yuzhangbin
  *@Date 2024/1/24 15:34
  *@Version 1.0
**/
@Getter
@AllArgsConstructor
public enum AbilityLevelEnum {

    //高时效
    HIGH("高时效"),
    //低时效
    LOW("低时效"),
    //新人
    NEW("新人"),
    ;
    private String desc;
}
