package com.muf.modules.workflow.opportunity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class OpportunityResponse {
    private Integer id;
    private Integer leadId;
    private String leadName;
    private String stage;
    private BigDecimal value;
    private Integer probability;
    private LocalDate expectedCloseDate;
    private LocalDate createdAt;
}
