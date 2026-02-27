package com.muf.common.exception.customopportunity;

import com.muf.base.exception.BusinessException;

public class InvalidLeadStatusException extends BusinessException {
    public InvalidLeadStatusException(String currentStatus) {
        super("Lead must be QUALIFIED to create opportunity. Current status: " + currentStatus);
    }
}
