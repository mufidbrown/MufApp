package com.muf.common.exception.customopportunity;

import com.muf.base.exception.BusinessException;

public class InvalidStageTransitionException extends BusinessException {
    public InvalidStageTransitionException(String fromStage, String toStage) {
        super("Invalid stage transition from " + fromStage + " to " + toStage);
    }
}
