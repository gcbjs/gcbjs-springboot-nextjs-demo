package com.gcbjs.demo.json.param.note;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName UpdateNoteParam
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/1/23 20:40
 * @Version 1.0
 **/
@Data
public class UpdateNoteParam implements Serializable {

    private Long noteId;

    private String title;

    private String content;
}
