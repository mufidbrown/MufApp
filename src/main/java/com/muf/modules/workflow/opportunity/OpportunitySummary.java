package com.muf.modules.workflow.opportunity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class OpportunitySummary {
    private Long id;
    private String stage;
    private BigDecimal value;
    private Integer probability;
    private LocalDate expectedCloseDate;
}

