package com.muf.modules.workflow.opportunity;

import com.muf.common.exception.customleadflow.LeadNotFoundException;
import com.muf.common.exception.customopportunity.InvalidLeadStatusException;
import com.muf.common.exception.customopportunity.OpportunityNotFoundException;
import com.muf.modules.master.lead.entity.domain.Lead;
import com.muf.modules.master.lead.repository.LeadRepository;
import com.muf.modules.workflow.opportunity.mapper.OpportunityMapper;
import com.muf.modules.workflow.opportunity.rules.OpportunityStageRules;
import com.muf.modules.workflow.opportunity.validator.OpportunityValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;



@Service
@RequiredArgsConstructor
public class OpportunityServiceImpl implements OpportunityService {

    private final OpportunityRepository opportunityRepository;
    private final OpportunityStageHistoryRepository opportunityStageHistoryRepository;
    private final LeadRepository leadRepository;
    private final OpportunityValidator opportunityValidator;
    private final OpportunityStageRules opportunityStageRules;
    private final OpportunityMapper opportunityMapper;

    @Override
    @Transactional
    public OpportunityResponse createOpportunity(CreateOpportunityRequest request) {
        // validate lead exists
        Lead lead = leadRepository.findByIdWithDetails(request.getLeadId())
                .orElseThrow(() -> new LeadNotFoundException(request.getLeadId()));

        // validate lead status is QUALIFIED
        if (!"QUALIFIED".equals(lead.getStatus())){
            throw new InvalidLeadStatusException(lead.getStatus());
        }

        // validate probability (if provided)
        if (request.getProbability() != null){
            opportunityValidator.validateProbability(request.getProbability());
        }

        Opportunity opportunity = new Opportunity();
        opportunity.setLeadId(request.getLeadId());
        opportunity.setLead(lead);
        opportunity.setStage("PROSPECTING"); // Default stage
        opportunity.setValue(request.getValue());

        // Set probability - use provided or default based on stage
        opportunity.setProbability(request.getProbability() != null
                ? request.getProbability()
                : OpportunityStageRules.DEFAULT_PROBABILITY.get("PROSPECTING"));


        opportunity.setExpectedCloseDate(request.getExpectedCloseDate());

        Opportunity savedOpportunity = opportunityRepository.save(opportunity);

        // Create initial stage history
        OpportunityStageHistory opportunityStageHistory = new OpportunityStageHistory();
        opportunityStageHistory.setOpportunityId(savedOpportunity.getId());
        opportunityStageHistory.setFromStage(null);
        opportunityStageHistory.setToStage("PROSPECTING");
        opportunityStageHistory.setChangedAt(LocalDateTime.now());
        opportunityStageHistoryRepository.save(opportunityStageHistory);

        // Reload with lead details
        Opportunity opportunityWithLead = opportunityRepository.findByIdWithLead(savedOpportunity.getId())
                .orElseThrow(() -> new OpportunityNotFoundException(savedOpportunity.getId()));

        return opportunityMapper.toOpportunityResponse(opportunityWithLead);
    }


}
