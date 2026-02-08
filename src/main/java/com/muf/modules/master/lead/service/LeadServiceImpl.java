package com.muf.modules.master.lead.service;

import com.muf.common.exception.customleadflow.*;
import com.muf.modules.master.lead.dto.*;
import com.muf.modules.master.lead.entity.domain.Lead;
import com.muf.modules.master.lead.entity.domain.LeadAssignment;
import com.muf.modules.master.lead.entity.domain.LeadHistory;
import com.muf.modules.master.lead.entity.domain.LeadSource;
import com.muf.modules.master.lead.repository.LeadAssignmentRepository;
import com.muf.modules.master.lead.repository.LeadHistoryRepository;
import com.muf.modules.master.lead.repository.LeadRepository;
import com.muf.modules.master.lead.repository.LeadSourceRepository;
import com.muf.modules.master.user.entity.domain.User;
import com.muf.modules.master.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
//        LeadSource leadSource = leadSourceRepository.findById(request.getSourceCode())
//                .orElseThrow(() -> new LeadSourceNotFoundException(request.getSourceCode()));

        // Owner validation (if provided)
//        if (request.getOwnerId() != null){
//            userRepository.findById(request.getOwnerId())
//                    .orElseThrow(() -> new UserNotFoundException(request.getOwnerId()));
//        }

        // Create lead with default status = NEW
        Lead lead = new Lead();
        lead.setName(request.getName());
        lead.setPhone(request.getPhone());
        lead.setEmail(request.getEmail());
//        lead.setSourceCode(request.getSourceCode());
        lead.setStatus("NEW"); //Default status
//        lead.setOwnerId(request.getOwnerId());
        lead.setCreatedAt(new Date());

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

    @Override
    @Transactional
    public LeadResponse assignLead(Integer leadId, AssignLeadRequest request) {
        // Validate lead exists
        Lead lead = leadRepository.findByIdWithDetails(leadId)
                .orElseThrow(() -> new LeadNotFoundException(leadId));

        // Validate user exists
        User user = userRepository.findByIdWithRoles(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException(request.getUserId()));

        // Update owner
        lead.setOwnerId(user.getId());
        leadRepository.save(lead);

        // Create assignment record
        LeadAssignment assignment = new LeadAssignment();
        assignment.setLeadId(leadId);
        assignment.setUserId(request.getUserId());
        assignment.setAssignedAt(new Date());
        leadAssignmentRepository.save(assignment);

        // Reload with associations
        Lead leadWithDetails = leadRepository.findByIdWithDetails(leadId)
                .orElseThrow(() -> new LeadNotFoundException(leadId));

        return toLeadResponse(leadWithDetails);
    }

    @Override
    @Transactional
    public LeadResponse updateLeadStatus(Integer leadId, UpdateLeadStatusRequest request){
        // Validate lead exist
        Lead lead = leadRepository.findByIdWithDetails(leadId)
                .orElseThrow(() -> new LeadNotFoundException(leadId));

        String currentStatus = lead.getStatus();
        String newStatus = request.getStatus();

        // validate status transition
        if (!isValidTransition(currentStatus, newStatus)) {
            throw new InvalidStatusTransitionException(currentStatus, newStatus);
        }

        // update status
        lead.setStatus(newStatus);
        leadRepository.save(lead);

        // create history record (audit)
        LeadHistory history = new LeadHistory();
        history.setLeadId(leadId);
        history.setFromStatus(currentStatus);
        history.setToStatus(newStatus);
        history.setChangedAt(LocalDateTime.now());
        leadHistoryRepository.save(history);

        // Reload with associations
        Lead leadWithDetails = leadRepository.findByIdWithDetails(leadId)
                .orElseThrow(() -> new LeadNotFoundException(leadId));

        return toLeadResponse(leadWithDetails);
    }

    @Override
    @Transactional(readOnly = true)
    public LeadResponse getLeadById(Integer leadId){
        Lead lead = leadRepository.findByIdWithDetails(leadId)
                .orElseThrow(() -> new LeadNotFoundException(leadId));
        return toLeadResponse(lead);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LeadHistoryResponse> getLeadHistory(Integer leadId) {
        // validate lead exists
        if (!leadRepository.existsById(leadId)) {
            throw new LeadNotFoundException(leadId);
        }

        List<LeadHistory> histories = leadHistoryRepository.findByLeadIdOrderByChangedAtDesc(leadId);

        return histories.stream()
                .map(this::toLeadHistoryResponse)
                .collect(Collectors.toList());
    }

    // Helper methods
    private boolean isValidTransition(String fromStatus, String toStatus){
        if (fromStatus.equals(toStatus)){
            return true; // same status
        }

        List<String> allowedTransitions = VALID_TRANSITIONS.get(fromStatus);
        return allowedTransitions != null && allowedTransitions.contains(toStatus);
    }


    private LeadResponse toLeadResponse(Lead lead) {
        LeadResponse response = new LeadResponse();
        response.setId(lead.getId());
        response.setName(lead.getName());
        response.setPhone(lead.getPhone());
        response.setEmail(lead.getEmail());
//        response.setSourceCode(lead.getSourceCode());
        response.setStatus(lead.getStatus());
        response.setOwnerId(lead.getOwnerId());
        response.setCreatedAt(new Date());


        // Handle lazy-loaded associations
        if (lead.getLeadSource() != null) {
            response.setSourceName(lead.getLeadSource().getName());
        }

        if (lead.getOwner() != null) {
            response.setOwnerName(lead.getOwner().getFullName());
        }

        return response;
    }


    private LeadHistoryResponse toLeadHistoryResponse(LeadHistory history) {
        LeadHistoryResponse response = new LeadHistoryResponse();
        response.setId(history.getId());
        response.setLeadId(history.getLeadId());
        response.setFromStatus(history.getFromStatus());
        response.setToStatus(history.getToStatus());
        response.setChangedAt(history.getChangedAt());
        return response;
    }
}
