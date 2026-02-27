package com.muf.common.exception.customopportunity;

import com.muf.base.exception.BusinessException;

public class OpportunityNotFoundException extends BusinessException{
    public OpportunityNotFoundException(Integer id) {
        super("Opportunity not found with id: " + id);
    }
}
