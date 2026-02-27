package com.muf.modules.workflow.opportunity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OpportunityStageHistoryResponse {
    private Integer id;
    private Integer opportunityId;
    private String fromStage;
    private String toStage;
    private LocalDateTime changedAt;
}
