package com.muf.modules.analytics.salesperformancereport;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SalesPerformanceFilterRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer userId;
    private String stage;
}
