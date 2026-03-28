package com.muf.modules.workflow.assignment.validator;


import com.muf.common.exception.customfollowup.InvalidRelatedEntityException;
import com.muf.modules.master.contact.ContactRepository;
import com.muf.modules.master.customeraccount.CustomerAccountRepository;
import com.muf.modules.master.lead.repository.LeadRepository;
import com.muf.modules.master.user.repository.UserRepository;
import com.muf.modules.workflow.assignment.AssignmentRepository;
import com.muf.modules.workflow.opportunity.OpportunityRepository;
import com.muf.modules.workflow.task.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssignmentValidator {

    private final AssignmentRepository assignmentRepository;
    private final UserRepository userRepository;
    private final LeadRepository leadRepository;
    private final CustomerAccountRepository customerAccountRepository;
    private final ContactRepository contactRepository;
    private final OpportunityRepository opportunityRepository;
    private final TaskRepository taskRepository;

    public void validateEntity(String entityType, Integer entityId) {
        boolean exists = false;

        switch (entityType) {
            case "LEAD":
                exists = leadRepository.existsById(entityId);
                break;
            case "ACCOUNT":
                exists = customerAccountRepository.existsById(entityId);
                break;
            case "CONTACT":
                exists = contactRepository.existsById(entityId);
                break;
            case "OPPORTUNITY":
                exists = opportunityRepository.existsById(entityId);
                break;
            case "TASK":
                exists = taskRepository.existsById(entityId);
                break;
            default:
                throw new InvalidRelatedEntityException(entityType, entityId);
        }

        if (!exists) {
            throw new InvalidRelatedEntityException(entityType, entityId);
        }
    }

}
