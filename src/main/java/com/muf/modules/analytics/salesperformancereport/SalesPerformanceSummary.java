package com.muf.modules.analytics.salesperformancereport;

import lombok.Data;

import java.util.List;

@Data
public class SalesPerformanceSummary {
    private List<SalesPerformanceResponse> salesPerformance;
    private SalesOverallMetrics salesOverallMetrics;
}
