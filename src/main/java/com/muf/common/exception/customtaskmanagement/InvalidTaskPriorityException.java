package com.muf.common.exception.customtaskmanagement;

import com.muf.base.exception.BusinessException;

public class InvalidTaskPriorityException extends BusinessException {
    public InvalidTaskPriorityException(String priority) {
        super("Invalid priority: " + priority + ". Allowed: LOW, MEDIUM, HIGH, URGENT");
    }
}
