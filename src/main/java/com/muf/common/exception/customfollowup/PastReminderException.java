package com.muf.common.exception.customfollowup;

import com.muf.base.exception.BusinessException;

public class PastReminderException extends BusinessException {
    public PastReminderException() {
        super("Reminder time cannot be in the past");
    }
}
