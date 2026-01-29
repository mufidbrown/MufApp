package com.muf.common.exception.customleadflow;

import com.muf.base.exception.BusinessException;

public class LeadSourceNotFoundException extends BusinessException {
    public LeadSourceNotFoundException(String sourceCode) {
        super("Lead source not found: " + sourceCode);
    }
}
