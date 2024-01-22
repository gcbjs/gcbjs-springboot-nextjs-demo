package com.gcbjs.demo.json.vo;

import com.gcbjs.demo.mappers.model.NoteInfo;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName NoteVo
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/1/22 15:43
 * @Version 1.0
 **/
@Data
public class NoteVO implements Serializable {
    /**
     * 主键
     */
    private Long noteId;

    private String title;

    private String content;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;

    public static NoteVO of(NoteInfo noteInfo) {
        NoteVO noteVO = new NoteVO();
        noteVO.setNoteId(noteInfo.getId());
        noteVO.setTitle(noteInfo.getTitle());
        noteVO.setContent(noteInfo.getContent());
        noteVO.setUpdateTime(noteInfo.getUpdateTime());
        noteVO.setCreateTime(noteInfo.getCreateTime());
        return noteVO;
    }
}
