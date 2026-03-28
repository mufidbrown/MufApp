package com.muf.common.exception.customsalesperformance;

import com.muf.base.exception.BusinessException;

public class SalesPerformanceReportNotFoundException extends BusinessException {
    public SalesPerformanceReportNotFoundException(String reportType) {
        super("Report not found: " + reportType);
    }
}
