package com.gcbjs.demo.json.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName QueryTicketParam
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/1/24 16:12
 * @Version 1.0
 **/
@Data
public class QueryTicketParam implements Serializable {

    private Integer pageSize = 10;

    private Integer pageIndex = 1;

    private String ticketStatus;

}
