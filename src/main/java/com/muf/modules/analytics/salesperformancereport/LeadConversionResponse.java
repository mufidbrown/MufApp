package com.muf.modules.analytics.salesperformancereport;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LeadConversionResponse {
    private Integer totalLeads;
    private Integer convertedLeads;
    private Integer qualifiedLeads;
    private Integer lostLeads;
    private Double conversionRate;
    private Double qualificationRate;
    private BigDecimal averageConversionValue;
}
