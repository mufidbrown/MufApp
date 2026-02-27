package com.muf.modules.workflow.opportunity;

public interface OpportunityService {
    OpportunityResponse createOpportunity(CreateOpportunityRequest request);
    OpportunityResponse updateOpportunity(Integer id, UpdateOpportunityRequest request);
}
