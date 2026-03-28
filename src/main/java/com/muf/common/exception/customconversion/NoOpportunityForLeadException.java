package com.muf.common.exception.customconversion;

import com.muf.base.exception.BusinessException;

public class NoOpportunityForLeadException extends BusinessException {
    public NoOpportunityForLeadException(Integer leadId) {
        super("No opportunity found for lead " + leadId + ". Cannot convert without opportunity.");
    }
}
