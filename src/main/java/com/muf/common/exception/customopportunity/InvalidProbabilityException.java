package com.muf.common.exception.customopportunity;

import com.muf.base.exception.BusinessException;

public class InvalidProbabilityException extends BusinessException {
    public InvalidProbabilityException(Integer probability) {
        super("Probability must be between 0 and 100. Provided: " + probability);
    }
}
