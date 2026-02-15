package com.muf.common.exception.customfollowup;

import com.muf.base.exception.BusinessException;

public class NoteNotFoundException extends BusinessException {
    public NoteNotFoundException(Integer id) {
        super("Note not found with id: " + id);
    }
}
