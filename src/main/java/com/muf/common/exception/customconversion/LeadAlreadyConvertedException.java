package com.muf.common.exception.customconversion;


import com.muf.base.exception.BusinessException;

public class LeadAlreadyConvertedException extends BusinessException {
    public LeadAlreadyConvertedException(Integer leadId) {
        super("Lead " + leadId + " has already been converted");
    }
}
