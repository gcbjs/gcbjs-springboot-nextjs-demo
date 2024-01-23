package com.gcbjs.demo.mappers;

import com.gcbjs.demo.mappers.model.NoteInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoteInfoMapper {

    List<NoteInfo> getAllNotes();

    NoteInfo getByNoteId(@Param("noteId") Long noteId);

    int updateNote(NoteInfo note);

    int deleteNote(@Param("noteId") Long noteId);

    int addNote(NoteInfo note);
}