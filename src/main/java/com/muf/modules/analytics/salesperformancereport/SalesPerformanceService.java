package com.muf.modules.analytics.salesperformancereport;

import com.muf.modules.analytics.pipelinesummary.DashboardSummary;
import com.muf.modules.analytics.pipelinesummary.PipelineSummaryResponse;

public interface SalesPerformanceService {
    SalesPerformanceSummary getSalesPerformanceReport();
/*    PipelineSummaryResponse getPipelineSummaryReport();
    LeadConversionResponse getLeadConversionReport();
    SalesActivityReportResponse getSalesActivityReport();
    DashboardSummary getDashboardSummaryReport();*/
}
