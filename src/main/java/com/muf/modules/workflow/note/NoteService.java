package com.muf.modules.workflow.note;

public interface NoteService {
    NoteResponse createNote(CreateNoteRequest request);
    NoteResponse updateNote(Integer id, UpdateNoteRequest request);
}
