package com.muf.modules.analytics.pipelinesummary;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PipelineOverallMetrics {
    private Integer totalOpportunities;
    private BigDecimal totalPipelineValue;
    private BigDecimal weightPipelineValue;
    private BigDecimal expectedRevenue;
}
