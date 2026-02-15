package com.muf.modules.workflow.activity;

import com.muf.common.exception.customfollowup.InvalidActivityTypeException;
import com.muf.common.exception.customfollowup.InvalidRelatedEntityException;
import com.muf.modules.master.lead.entity.domain.Lead;
import com.muf.modules.master.lead.repository.LeadRepository;
import com.muf.modules.workflow.opportunity.OpportunityRepository;
import com.muf.modules.workflow.reminder.Reminder;
import com.muf.modules.workflow.reminder.ReminderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final LeadRepository leadRepository;
    private final OpportunityRepository opportunityRepository;

    private static final List<String> VALID_ACTIVITIES_TYPES = Arrays.asList("CALL", "EMAIL", "MEETING", "TASK");

    @Override
    @Transactional
    public ActivityResponse createActivity(CreateActivityRequest request) {
        // validate activity type
        if (!VALID_ACTIVITIES_TYPES.contains(request.getType())) {
            throw new InvalidActivityTypeException(request.getType());
        }

        // validate related entity exists
        validateRelatedEntity(request.getRelatedType(), request.getRelatedId());

        Activity activity = new Activity();
        activity.setType(request.getType());
        activity.setSubject(request.getSubject());
        activity.setNote(request.getNote());
        activity.setRelatedType(request.getRelatedType());
        activity.setRelatedId(request.getRelatedId());
        activity.setDueDate(request.getDueDate());
        activity.setStatus(request.getStatus());
        activity.setCreatedAt(new Date());

        Activity savedActivity = activityRepository.save(activity);

        return toActivityResponse(savedActivity);

    }


    // Helper methods
    private void validateRelatedEntity(String entityType, Integer entityId) {
        boolean exists = false;

        switch (entityType) {
            case "LEAD":
                exists = leadRepository.existsById(entityId);
                break;
            case "ACCOUNT":
//                exists = accountRepository.existsById(entityId);
                break;
            case "CONTACT":
//                exists = contactRepository.existsById(entityId);
                break;
            case "OPPORTUNITY":
                exists = opportunityRepository.existsById(entityId);
                break;
            case "TASK":
//                exists = taskRepository.existsById(entityId);
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
/*                case "ACCOUNT":
                    return accountRepository.findById(entityId)
                            .map(Account::getCompanyName)
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
                            .orElse("Unknown");*/
                default:
                    return "Unknown";
            }
        } catch (Exception e) {
            return "Unknown";
        }
    }

    private ActivityResponse toActivityResponse(Activity activity) {
        ActivityResponse response = new ActivityResponse();
        response.setId(activity.getId());
        response.setType(activity.getType());
        response.setSubject(activity.getSubject());
        response.setNote(activity.getNote());
        response.setRelatedType(activity.getRelatedType());
        response.setRelatedId(activity.getRelatedId());
        response.setDueDate(activity.getDueDate());
        response.setStatus(activity.getStatus());
        response.setCreatedAt(activity.getCreatedAt());

        // Get related entity name
        String relatedName = getRelatedEntityName(activity.getRelatedType(), activity.getRelatedId());
        response.setRelatedName(relatedName);

        return response;
    }

    private String getRelatedEntityName(String entityType, Integer entityId) {
        try {
            switch (entityType) {
                case "LEAD":
                    return leadRepository.findById(entityId)
                            .map(Lead::getName)
                            .orElse("unknown");
/*                case "ACCOUNT":
                    return accountRepository.findById(entityId)
                            .map(Account::getCompanyName)
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
                            .orElse("Unknown");*/
                default:
                    return "Unknown";
            }
        } catch (Exception e) {
            return "unknown";
        }
    }
}
