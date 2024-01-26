package com.gcbjs.demo.json.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName QueryScheduleParam
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/1/26 13:44
 * @Version 1.0
 **/
@Data
public class QueryScheduleParam implements Serializable {

    private String userId;

    private String currentMonth;
}
