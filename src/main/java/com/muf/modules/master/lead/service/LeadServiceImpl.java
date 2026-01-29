package com.muf.modules.master.lead.service;

import com.muf.common.exception.customleadflow.DuplicatePhoneException;
import com.muf.common.exception.customleadflow.LeadNotFoundException;
import com.muf.common.exception.customleadflow.LeadSourceNotFoundException;
import com.muf.common.exception.customleadflow.UserNotFoundException;
import com.muf.modules.master.lead.dto.CreateLeadRequest;
import com.muf.modules.master.lead.dto.LeadResponse;
import com.muf.modules.master.lead.entity.domain.Lead;
import com.muf.modules.master.lead.entity.domain.LeadHistory;
import com.muf.modules.master.lead.entity.domain.LeadSource;
import com.muf.modules.master.lead.repository.LeadAssignmentRepository;
import com.muf.modules.master.lead.repository.LeadHistoryRepository;
import com.muf.modules.master.lead.repository.LeadRepository;
import com.muf.modules.master.lead.repository.LeadSourceRepository;
import com.muf.modules.master.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LeadServiceImpl implements LeadService {
    private final LeadRepository leadRepository;
    private final LeadSourceRepository leadSourceRepository;
    private final UserRepository userRepository;
    private final LeadAssignmentRepository leadAssignmentRepository;
    private final LeadHistoryRepository leadHistoryRepository;

    private static final Map<String, List<String>> VALID_TRANSITIONS = new HashMap<>();

    static {
        VALID_TRANSITIONS.put("NEW", Arrays.asList("CONTACTED"));
        VALID_TRANSITIONS.put("CONTACTED", Arrays.asList("QUALIFIED", "LOST"));
        VALID_TRANSITIONS.put("QUALIFIED", Arrays.asList("CONVERTED", "LOST"));
        VALID_TRANSITIONS.put("CONVERTED", Arrays.asList());
        VALID_TRANSITIONS.put("LOST", Arrays.asList());
    }

    @Override
    @Transactional
    public LeadResponse createLead(CreateLeadRequest request){

        // Duplicate phone check
        if (leadRepository.existByPhone(request.getPhone())){
            throw new DuplicatePhoneException(request.getPhone());
        }

        // LeadSource validation
        LeadSource leadSource = leadSourceRepository.findById(request.getSourceCode())
                .orElseThrow(() -> new LeadSourceNotFoundException(request.getSourceCode()));

        // Owner validation (if provided)
        if (request.getOwnerId() != null){
            userRepository.findById(request.getOwnerId())
                    .orElseThrow(() -> new UserNotFoundException(request.getOwnerId()));
        }

        // Create lead with default status = NEW
        Lead lead = new Lead();
        lead.setName(request.getName());
        lead.setPhone(request.getPhone());
        lead.setEmail(request.getEmail());
        lead.setSourceCode(request.getSourceCode());
        lead.setStatus("NEW"); //Default status
        lead.setOwnerId(request.getOwnerId());
        lead.setCreatedAt(LocalDateTime.now());

        Lead saveLead = leadRepository.save(lead);

        // Create initial lead history
        LeadHistory history = new LeadHistory();
        history.setLeadId(saveLead.getId());
        history.setFromStatus(null);
        history.setToStatus("NEW");
        history.setChangedAt(LocalDateTime.now());
        leadHistoryRepository.save(history);

        // Reload with associations
        Lead leadWithDetails = leadRepository.findByIdWithDetails(saveLead.getId())
                .orElseThrow(() -> new LeadNotFoundException(saveLead.getId()));

        return toLeadResponse(leadWithDetails);
    }

    private LeadResponse toLeadResponse(Lead lead) {
        LeadResponse response = new LeadResponse();
        response.setId(lead.getId());
        response.setName(lead.getName());
        response.setPhone(lead.getPhone());
        response.setEmail(lead.getEmail());
        response.setSourceCode(lead.getSourceCode());
        response.setStatus(lead.getStatus());
        response.setOwnerId(lead.getOwnerId());
        response.setCreatedAt(lead.getCreatedAt());

        // Handle lazy-loaded associations
        if (lead.getLeadSource() != null) {
            response.setSourceName(lead.getLeadSource().getName());
        }

        if (lead.getOwner() != null) {
            response.setOwnerName(lead.getOwner().getFullName());
        }

        return response;
    }
}
