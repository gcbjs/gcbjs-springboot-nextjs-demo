package com.gcbjs.demo.mappers.model;

import com.gcbjs.demo.constants.ShiftInfoEnum;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
  *@ClassName Schedule
  *@Description 排班记录
  *@Author yuzhangbin
  *@Date 2024/1/18 14:29
  *@Version 1.0
**/
@Getter
public class ScheduleInfo implements Serializable {
    /**
    * 主键
    */
    private Long scheduleId;

    /**
    * 员工id
    */
    private Long userId;

    private String name;

    private String mobile;

    /**
    * 排班日期
    */
    private LocalDate date;

    /**
    * 班次
    */
    private ShiftInfoEnum shift;

    private LocalDateTime createTime;



    private ScheduleInfo(){

    }



    public ScheduleInfo(Long userId,
                        LocalDate date,
                        ShiftInfoEnum shift,
                        String name,
                        String mobile) {
        this.userId = userId;
        this.date = date;
        this.shift = shift;
        this.name = name;
        this.mobile = mobile;
    }
}