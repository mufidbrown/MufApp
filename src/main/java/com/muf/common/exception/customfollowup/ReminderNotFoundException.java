package com.muf.common.exception.customfollowup;

import com.muf.base.exception.BusinessException;

public class ReminderNotFoundException extends BusinessException {
    public ReminderNotFoundException(Integer id) {
        super("Reminder not found with id: " + id);
    }
}
