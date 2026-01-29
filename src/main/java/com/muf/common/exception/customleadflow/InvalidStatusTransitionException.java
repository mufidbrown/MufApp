package com.muf.common.exception.customleadflow;

import com.muf.base.exception.BusinessException;

public class InvalidStatusTransitionException extends BusinessException {
    public InvalidStatusTransitionException(String from, String to) {
        super("Invalid status transition from " + from + " to " + to);
    }
}
