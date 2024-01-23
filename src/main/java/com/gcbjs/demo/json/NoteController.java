package com.gcbjs.demo.json;

import com.gcbjs.demo.json.param.note.AddNoteParam;
import com.gcbjs.demo.json.param.note.UpdateNoteParam;
import com.gcbjs.demo.json.vo.NoteVO;
import com.gcbjs.demo.mappers.NoteInfoMapper;
import com.gcbjs.demo.mappers.model.NoteInfo;
import com.gcbjs.demo.util.Result;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName NoteController
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/1/22 15:41
 * @Version 1.0
 **/
@RestController
@RequestMapping("/note")
public class NoteController {

    @Resource
    private NoteInfoMapper noteInfoMapper;


    @RequestMapping(path = "/getAllNotes", method = RequestMethod.GET)
    public Result<List<NoteVO>> getAllNotes() {
        List<NoteInfo> all = noteInfoMapper.getAllNotes();
        return Result.success(all.stream().map(NoteVO::of).toList());
    }

    @RequestMapping(path = "/getNote", method = RequestMethod.GET)
    public Result<NoteVO> getNote(@RequestParam("noteId") Long noteId) {
        NoteInfo noteInfo = noteInfoMapper.getByNoteId(noteId);
        if (Objects.isNull(noteInfo)) {
            return null;
        }
        return Result.success(NoteVO.of(noteInfo));
    }

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(path = "/updateNote", method = RequestMethod.POST)
    public Result<Boolean> updateNote(@RequestBody UpdateNoteParam param) {
        NoteInfo noteInfo = new NoteInfo();
        noteInfo.setId(param.getNoteId());
        noteInfo.setTitle(param.getTitle());
        noteInfo.setContent(param.getContent());
        noteInfoMapper.updateNote(noteInfo);
        return Result.success(Boolean.TRUE);
    }

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(path = "/addNote", method = RequestMethod.POST)
    public Result<Long> addNote(@RequestBody AddNoteParam param) {
        NoteInfo noteInfo = new NoteInfo();
        noteInfo.setTitle(param.getTitle());
        noteInfo.setContent(param.getContent());
        noteInfoMapper.addNote(noteInfo);
        return Result.success(noteInfo.getId());
    }

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(path = "/deleteNote", method = RequestMethod.POST)
    public Result<Boolean> deleteNote(@RequestParam("noteId") Long noteId) {
        noteInfoMapper.deleteNote(noteId);
        return Result.success(Boolean.TRUE);
    }


}
