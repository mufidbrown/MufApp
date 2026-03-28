package com.muf.common.exception.customconversion;

import com.muf.base.exception.BusinessException;

public class ConversionNotFoundException extends BusinessException {
    public ConversionNotFoundException(Integer leadId) {
        super("Conversion not found for lead " + leadId);
    }
}

