package com.muf.common.exception.customtaskmanagement;

import com.muf.base.exception.BusinessException;

public class TaskNotFoundException extends BusinessException {
    public TaskNotFoundException(Integer id) {
        super("Task with id " + id);
    }
}
