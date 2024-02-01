package com.gcbjs.demo.json.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName DirtyUserData
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/1/31 14:44
 * @Version 1.0
 **/
@Data
public class DirtyUserData implements Serializable {

    /**
     * 手机号
     */
     private String  mobile;

    /**
     * 值班日期
     */
    private String dutyDate;
}
