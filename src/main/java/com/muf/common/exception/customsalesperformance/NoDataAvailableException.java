package com.muf.common.exception.customsalesperformance;

import com.muf.base.exception.BusinessException;

public class NoDataAvailableException extends BusinessException {
    public NoDataAvailableException(String reportType) {
        super("No data available for report: " + reportType);
    }
}
