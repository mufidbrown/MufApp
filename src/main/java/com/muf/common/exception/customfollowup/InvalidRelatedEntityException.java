package com.muf.common.exception.customfollowup;

import com.muf.base.exception.BusinessException;

public class InvalidRelatedEntityException extends BusinessException {
    public InvalidRelatedEntityException(String entityType, Integer entityId) {
        super("Related entity not found: " + entityType + " with id " + entityId);

    }
}
