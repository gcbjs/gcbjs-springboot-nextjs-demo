package com.gcbjs.demo.mappers.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
  *@ClassName NoteInfo
  *@Description 博客信息表
  *@Author yuzhangbin
  *@Date 2024/1/22 15:32
  *@Version 1.0
**/
@Data
public class NoteInfo {
    /**
     * 主键
     */
    private Long id;

    private String title;

    private String content;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;
}