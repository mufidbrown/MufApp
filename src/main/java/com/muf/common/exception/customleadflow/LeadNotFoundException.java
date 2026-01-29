package com.muf.common.exception.customleadflow;

import com.muf.base.exception.BusinessException;

public class LeadNotFoundException extends BusinessException {
    public LeadNotFoundException(Long id) {
        super("Lead not found with id: " + id);
    }
}
