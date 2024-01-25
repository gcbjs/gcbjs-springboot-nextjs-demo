package com.gcbjs.demo.json.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName QueryUserParam
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/1/25 14:44
 * @Version 1.0
 **/
@Data
public class QueryUserParam implements Serializable {

    private Integer pageSize = 10;

    private Integer pageIndex = 1;
}
