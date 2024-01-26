package com.gcbjs.demo.json.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName AssignDubyParam
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/1/26 10:46
 * @Version 1.0
 **/
@Data
public class AssignDutyParam implements Serializable {

    private Long userId;

    /**
     * 值班日期 yyyy-mm-dd
     */
    private String dutyDate;

    private String dutyType;
}
