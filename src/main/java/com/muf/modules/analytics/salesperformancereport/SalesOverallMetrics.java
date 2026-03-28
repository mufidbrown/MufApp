package com.muf.modules.analytics.salesperformancereport;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SalesOverallMetrics {
    private Integer totalDeals;
    private BigDecimal totalRevenue;
    private Double averageWinRate;
    private Integer totalSalesReps;
    private SalesPerformanceResponse topPerformer;
}
