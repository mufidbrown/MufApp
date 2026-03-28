package com.muf.modules.workflow.assignment;

import com.muf.common.exception.customleadflow.UserNotFoundException;
import com.muf.modules.master.contact.ContactRepository;
import com.muf.modules.master.customeraccount.CustomerAccountRepository;
import com.muf.modules.master.lead.repository.LeadRepository;
import com.muf.modules.master.user.entity.domain.User;
import com.muf.modules.master.user.repository.UserRepository;
import com.muf.modules.workflow.assignment.mapper.AssignmentMapper;
import com.muf.modules.workflow.assignment.validator.AssignmentValidator;
import com.muf.modules.workflow.opportunity.OpportunityRepository;
import com.muf.modules.workflow.task.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final UserRepository userRepository;
    private final LeadRepository leadRepository;
    private final CustomerAccountRepository customerAccountRepository;
    private final ContactRepository contactRepository;
    private final OpportunityRepository opportunityRepository;
    private final TaskRepository taskRepository;
    private final AssignmentValidator assignmentValidator;
    private final AssignmentMapper assignmentMapper;

    @Override
    @Transactional
    public AssignmentResponse createAssignment(CreateAssignmentRequest request) {

        // validate user exists
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException(request.getUserId()));

        // validate entity exists
        assignmentValidator.validateEntity(request.getEntityType(), request.getEntityId());

        Assignment assignment = new Assignment();
        assignment.setEntityType(request.getEntityType());
        assignment.setEntityId(request.getEntityId());
        assignment.setUserId(user.getId());

        assignmentRepository.save(assignment);

        return assignmentMapper.toAssignmentResponse(assignment);
    }

    @Override
    @Transactional
    public List<AssignmentResponse> getAssignmentsByEntity(String entityType, Integer entityId){
        // Validate entity exists
        assignmentValidator.validateEntity(entityType, entityId);

        List<Assignment> assignments = assignmentRepository.findByEntity(entityType, entityId);

        return assignments.stream()
                .map(assignmentMapper::toAssignmentResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<AssignmentResponse> getAssignmentsByUser(Integer userId){
        // validate user exists
        if (!userRepository.existsById(userId)){
            throw new UserNotFoundException(userId);
        }

        List<Assignment> assignments = assignmentRepository.findByUserId(userId);

        return assignments.stream()
                .map(assignmentMapper::toAssignmentResponse)
                .collect(Collectors.toList());

    }
}
