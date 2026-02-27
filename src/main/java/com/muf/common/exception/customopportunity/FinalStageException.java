package com.muf.common.exception.customopportunity;

import com.muf.base.exception.BusinessException;

public class FinalStageException extends BusinessException {
    public FinalStageException(String stage) {
        super("Cannot change stage from final state: " + stage);
    }
}
