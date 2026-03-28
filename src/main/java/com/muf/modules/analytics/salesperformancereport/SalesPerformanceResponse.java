package com.muf.modules.analytics.salesperformancereport;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SalesPerformanceResponse {
    private Integer userId;
    private String fullName;
    private Integer totalDeals;
    private BigDecimal totalRevenue;
    private Double winRate;
    private Integer wonDeals;
    private Integer lostDeals;
    private BigDecimal averageDealSize;
}
