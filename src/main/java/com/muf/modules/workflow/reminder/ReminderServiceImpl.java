package com.muf.modules.workflow.reminder;

import com.muf.common.exception.customfollowup.InvalidRelatedEntityException;
import com.muf.common.exception.customfollowup.PastReminderException;
import com.muf.modules.master.contact.Contact;
import com.muf.modules.master.contact.ContactRepository;
import com.muf.modules.master.customer.CustomerAccount;
import com.muf.modules.master.customer.CustomerAccountRepository;
import com.muf.modules.master.lead.entity.domain.Lead;
import com.muf.modules.master.lead.repository.LeadRepository;
import com.muf.modules.workflow.note.Note;
import com.muf.modules.workflow.note.NoteResponse;
import com.muf.modules.workflow.opportunity.OpportunityRepository;
import com.muf.modules.workflow.task.Task;
import com.muf.modules.workflow.task.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ReminderServiceImpl implements ReminderService {

    private final ReminderRepository reminderRepository;
    private final LeadRepository leadRepository;
    private final CustomerAccountRepository customerAccountRepository;
    private final ContactRepository contactRepository;
    private final OpportunityRepository opportunityRepository;
    private final TaskRepository taskRepository;

    public ReminderResponse createReminder(CreateReminderRequest request) {
        // validate entity exists
        validateEntity(request.getEntityType(), request.getEntityId());

        // validate reminder time is in the future
        if (request.getRemindAt().isBefore(LocalDateTime.now())){
            throw new PastReminderException();
        }

        Reminder reminder = new Reminder();
        reminder.setEntityType(request.getEntityType());
        reminder.setEntityId(request.getEntityId());
        reminder. setRemindAt(request.getRemindAt());
        reminder.setStatus(request.getStatus() != null ? request.getStatus() : "PENDING");

        Reminder savedReminder = reminderRepository.save(reminder);

        return toReminderResponse(savedReminder);
    }


    private void validateEntity(String entityType, Integer entityId) {
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

    private ReminderResponse toReminderResponse(Reminder reminder) {
        ReminderResponse response = new ReminderResponse();
        response.setId(reminder.getId());
        response.setEntityType(reminder.getEntityType());
        response.setEntityId(reminder.getEntityId());
        response.setRemindAt(LocalDateTime.now());
        response.setStatus(reminder.getStatus());

        // Get entity name
        String entityName = getEntityName(reminder.getEntityType(), reminder.getEntityId());
        response.setEntityName(entityName);

        return response;
    }

    private String getEntityName(String entityType, Integer entityId) {
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
