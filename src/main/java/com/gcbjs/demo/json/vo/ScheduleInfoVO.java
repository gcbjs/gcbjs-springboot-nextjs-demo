package com.gcbjs.demo.json.vo;

import com.gcbjs.demo.constants.ShiftInfoEnum;
import com.gcbjs.demo.mappers.model.ScheduleInfo;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @ClassName ScheduleInfoVO
 * @Description 值班信息
 * @Author yuzhangbin
 * @Date 2024/1/23 17:48
 * @Version 1.0
 **/
@Data
public class ScheduleInfoVO implements Serializable {


    /**
     * 员工id
     */
    private Long userId;

    private String name;

    private String mobile;

    /**
     * 排班日期
     */
    private String scheduleDateStr;

    /**
     * 班次
     */
    private String scheduleDesc;


    public static ScheduleInfoVO of(ScheduleInfo info){
        ScheduleInfoVO vo = new ScheduleInfoVO();
        vo.setUserId(info.getUserId());
        vo.setName(info.getName());
        vo.setMobile(info.getMobile());
        vo.setScheduleDateStr(info.getDate().toString());
        vo.setScheduleDesc(info.getShift().getDesc());
        return vo;
    }
}
