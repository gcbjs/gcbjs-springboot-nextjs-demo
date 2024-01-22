package com.gcbjs.demo.json;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName AddUserParam
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/1/22 13:41
 * @Version 1.0
 **/
@Data
public class AddUserParam implements Serializable {

    private String name;
    private String mobile;
}
