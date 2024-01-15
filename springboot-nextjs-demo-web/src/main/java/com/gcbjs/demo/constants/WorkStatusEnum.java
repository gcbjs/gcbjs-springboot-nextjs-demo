package com.gcbjs.demo.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
  *@ClassName WorkStatusEnum
  *@Description 工作状态
  *@Author yuzhangbin
  *@Date 2024/1/15 16:14
  *@Version 1.0
**/
@Getter
@AllArgsConstructor
public enum WorkStatusEnum {

    /**
     * 工作中
     */
    WORKING("工作中"),
    ;

    private String desc;


    public static String getDescByCode(String code) {
        WorkStatusEnum workStatusEnum = Arrays.stream(WorkStatusEnum.values())
                .filter(e -> e.name().equals(code)).findFirst()
                .orElse(null);
        return workStatusEnum == null ? null : workStatusEnum.getDesc();
    }
}
