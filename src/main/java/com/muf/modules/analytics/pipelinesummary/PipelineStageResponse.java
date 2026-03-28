package com.muf.modules.analytics.pipelinesummary;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PipelineStageResponse {
    private String stage;
    private Integer dealCount;
    private BigDecimal totalValue;
    private Double averageValue;
    private Integer averageProbability;
}
