package com.muf.common.exception.customsalesperformance;

import com.muf.base.exception.BusinessException;

public class InvalidDateRangeException extends BusinessException {
    public InvalidDateRangeException(String message) {
        super("invalid date range: " + message);
    }
}
