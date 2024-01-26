package com.gcbjs.demo.json.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName CountTicketParam
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/1/25 16:59
 * @Version 1.0
 **/
@Data
public class CountTicketParam implements Serializable {

    private String type;

    private String day;

    private String month;

    private String year;

}
