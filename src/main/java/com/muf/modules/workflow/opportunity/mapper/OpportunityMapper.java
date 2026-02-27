package com.muf.modules.workflow.opportunity.mapper;

import com.muf.modules.workflow.opportunity.Opportunity;
import com.muf.modules.workflow.opportunity.OpportunityResponse;
import com.muf.modules.workflow.opportunity.OpportunityStageHistory;
import com.muf.modules.workflow.opportunity.OpportunityStageHistoryResponse;
import org.springframework.stereotype.Component;

@Component
public class OpportunityMapper {

    public OpportunityResponse toOpportunityResponse(Opportunity opportunity) {
        OpportunityResponse response = new OpportunityResponse();
        response.setId(opportunity.getId());
        response.setLeadId(opportunity.getLeadId());
        response.setStage(opportunity.getStage());
        response.setValue(opportunity.getValue());
        response.setProbability(opportunity.getProbability());
        response.setExpectedCloseDate(opportunity.getExpectedCloseDate());

        // Handle lazy-loaded lead
        if (opportunity.getLead() != null) {
            response.setLeadName(opportunity.getLead().getName());
        }

        return response;
    }

    public OpportunityStageHistoryResponse toStageHistoryResponse(OpportunityStageHistory history) {
        OpportunityStageHistoryResponse response = new OpportunityStageHistoryResponse();
        response.setId(history.getId());
        response.setOpportunityId(history.getOpportunityId());
        response.setFromStage(history.getFromStage());
        response.setToStage(history.getToStage());
        response.setChangedAt(history.getChangedAt());
        return response;
    }

}
