package com.muf.common.exception.customtaskmanagement;

import com.muf.base.exception.BusinessException;

public class InvalidTaskStatusTransitionException extends BusinessException {
    public InvalidTaskStatusTransitionException(String fromStatus, String toStatus) {
        super("Invalid task status transition from " + fromStatus + " to " + toStatus);
    }
}
