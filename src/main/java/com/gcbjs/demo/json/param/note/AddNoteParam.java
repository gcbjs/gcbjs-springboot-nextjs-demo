package com.gcbjs.demo.json.param.note;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName AddNoteParam
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/1/23 20:51
 * @Version 1.0
 **/
@Data
public class AddNoteParam implements Serializable {

    private String title;

    private String content;
}
