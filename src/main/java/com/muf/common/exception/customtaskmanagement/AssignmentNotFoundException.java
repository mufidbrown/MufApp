package com.muf.common.exception.customtaskmanagement;

import com.muf.base.exception.BusinessException;

public class AssignmentNotFoundException extends BusinessException {
    public AssignmentNotFoundException(String entityType, Long entityId, Long userId) {
        super("Assignment not found for " + entityType + " " + entityId + " and user " + userId);
    }
}
