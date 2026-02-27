package com.muf.modules.workflow.opportunity;

import com.muf.common.constant.LeadStatus;
import com.muf.common.constant.OpportunityStage;
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

        // 1️⃣ Validate Lead Exists
        Lead lead = leadRepository.findByIdWithDetails(request.getLeadId())
                .orElseThrow(() -> new LeadNotFoundException(request.getLeadId()));

        // 2️⃣ Validate Lead Status is QUALIFIED (enum)
        LeadStatus leadStatus = LeadStatus.valueOf(lead.getStatus());
        if (leadStatus != LeadStatus.QUALIFIED) {
            throw new InvalidLeadStatusException(leadStatus.name());
        }

        // 3️⃣ Validate Probability If Provided
        if (request.getProbability() != null) {
            opportunityValidator.validateProbability(request.getProbability());
        }

        // 4️⃣ Initial Stage Always PROSPECTING
        OpportunityStage stage = OpportunityStage.PROSPECTING;

        Opportunity opportunity = new Opportunity();
        opportunity.setLeadId(lead.getId());
        opportunity.setLead(lead);
        opportunity.setStage(stage.name());
        opportunity.setValue(request.getValue());

        // 5️⃣ Set Probability → provided OR default rule
        opportunity.setProbability(
                request.getProbability() != null
                        ? request.getProbability()
                        : OpportunityStageRules.DEFAULT_PROBABILITY.get(stage)
        );

        opportunity.setExpectedCloseDate(request.getExpectedCloseDate());

        // 6️⃣ Save Opportunity
        Opportunity savedOpportunity = opportunityRepository.save(opportunity);


        // 7️⃣ Create Stage History
        OpportunityStageHistory history = new OpportunityStageHistory();
        history.setOpportunityId(savedOpportunity.getId());
        history.setFromStage(null);
        history.setToStage(stage.name());
        history.setChangedAt(LocalDateTime.now());
        opportunityStageHistoryRepository.save(history);

        // 8️⃣ Reload with lead
        Opportunity opportunityWithLead = opportunityRepository.findByIdWithLead(savedOpportunity.getId())
                .orElseThrow(() -> new OpportunityNotFoundException(savedOpportunity.getId()));

        // 9️⃣ Map to Response
        return opportunityMapper.toOpportunityResponse(opportunityWithLead);
    }


    @Override
    @Transactional
    public OpportunityResponse updateOpportunity(Integer id, UpdateOpportunityRequest request) {

        Opportunity opportunity = opportunityRepository.findById(id)
                .orElseThrow(() -> new OpportunityNotFoundException(id));

        // Update value
        if (request.getValue() != null) {
            opportunity.setValue(request.getValue());
        }

        // Update probability (validated)
        if (request.getProbability() != null) {
            opportunityValidator.validateProbability(request.getProbability());
            opportunity.setProbability(request.getProbability());
        }

        // Update expected close date
        if (request.getExpectedCloseDate() != null) {
            opportunity.setExpectedCloseDate(request.getExpectedCloseDate());
        }

        opportunityRepository.save(opportunity);

        Opportunity withLead = opportunityRepository.findByIdWithLead(id)
                .orElseThrow(() -> new OpportunityNotFoundException(id));

        return opportunityMapper.toOpportunityResponse(withLead);
    }

}
