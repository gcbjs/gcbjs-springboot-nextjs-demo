package com.gcbjs.demo.json;

import com.gcbjs.demo.json.vo.NoteVO;
import com.gcbjs.demo.mappers.NoteInfoMapper;
import com.gcbjs.demo.mappers.model.NoteInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public List<NoteVO> getAllNotes() {
        List<NoteInfo> all = noteInfoMapper.getAllNotes();
        return all.stream().map(NoteVO::of).toList();
    }

    @RequestMapping(path = "/getNote", method = RequestMethod.GET)
    public NoteVO getNote(@RequestParam("noteId") Long noteId) {
        NoteInfo noteInfo = noteInfoMapper.getByNoteId(noteId);
        if (Objects.isNull(noteInfo)) {
            return null;
        }
        return NoteVO.of(noteInfo);
    }

}
