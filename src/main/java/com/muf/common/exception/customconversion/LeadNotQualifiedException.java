package com.muf.common.exception.customconversion;

import com.muf.base.exception.BusinessException;

public class LeadNotQualifiedException extends BusinessException {
    public LeadNotQualifiedException(Integer leadId, String currentStatus) {
        super("Lead " + leadId + " must be QUALIFIED or CONVERTED to convert. Current status: " + currentStatus);
    }
}
