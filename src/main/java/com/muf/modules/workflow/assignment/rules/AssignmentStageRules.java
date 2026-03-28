package com.muf.modules.workflow.assignment.rules;

import com.muf.modules.master.contact.Contact;
import com.muf.modules.master.contact.ContactRepository;
import com.muf.modules.master.customeraccount.CustomerAccount;
import com.muf.modules.master.customeraccount.CustomerAccountRepository;
import com.muf.modules.master.lead.entity.domain.Lead;
import com.muf.modules.master.lead.repository.LeadRepository;
import com.muf.modules.master.user.repository.UserRepository;
import com.muf.modules.workflow.assignment.AssignmentRepository;
import com.muf.modules.workflow.opportunity.OpportunityRepository;
import com.muf.modules.workflow.task.Task;
import com.muf.modules.workflow.task.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssignmentStageRules {

    private final AssignmentRepository assignmentRepository;
    private final UserRepository userRepository;
    private final LeadRepository leadRepository;
    private final CustomerAccountRepository customerAccountRepository;
    private final ContactRepository contactRepository;
    private final OpportunityRepository opportunityRepository;
    private final TaskRepository taskRepository;

    public String getEntityName(String entityType, Integer entityId) {
        try {
            switch (entityType) {
                case "LEAD":
                    return leadRepository.findById(entityId)
                            .map(Lead::getName)
                            .orElse("Unknown");
                case "ACCOUNT":
                    return customerAccountRepository.findById(entityId)
                            .map(CustomerAccount::getCompanyName)
                            .orElse("Unknown");
                case "CONTACT":
                    return contactRepository.findById(entityId)
                            .map(Contact::getFullName)
                            .orElse("Unknown");
                case "OPPORTUNITY":
                    return "Opportunity #" + entityId;
                case "TASK":
                    return taskRepository.findById(entityId)
                            .map(Task::getTitle)
                            .orElse("Unknown");
                default:
                    return "Unknown";
            }
        } catch (Exception e) {
            return "Unknown";
        }
    }
}
