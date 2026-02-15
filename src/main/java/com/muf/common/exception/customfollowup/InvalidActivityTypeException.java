package com.muf.common.exception.customfollowup;

import com.muf.base.exception.BusinessException;

public class InvalidActivityTypeException extends BusinessException {
    public InvalidActivityTypeException(String type) {
        super("Invalid activity type: " + type + ". Allowed types: CALL, EMAIL, MEETING, TASK");
    }
}
