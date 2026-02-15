package com.muf.modules.workflow.note;

import com.muf.common.exception.customfollowup.InvalidRelatedEntityException;
import com.muf.common.exception.customleadflow.UserNotFoundException;
import com.muf.modules.master.contact.Contact;
import com.muf.modules.master.contact.ContactRepository;
import com.muf.modules.master.customer.CustomerAccount;
import com.muf.modules.master.customer.CustomerAccountRepository;
import com.muf.modules.master.lead.entity.domain.Lead;
import com.muf.modules.master.lead.repository.LeadRepository;
import com.muf.modules.master.user.repository.UserRepository;
import com.muf.modules.workflow.opportunity.OpportunityRepository;
import com.muf.modules.workflow.task.Task;
import com.muf.modules.workflow.task.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final LeadRepository leadRepository;
    private final CustomerAccountRepository accountRepository;
    private final ContactRepository contactRepository;
    private final OpportunityRepository opportunityRepository;
    private final TaskRepository taskRepository;

    @Override
    @Transactional
    public NoteResponse createNote(CreateNoteRequest request) {
        // validate entity exist
        validateEntity(request.getEntityType(), request.getEntityId());

        // validate user exists (if provided)
        if(request.getCreatedBy() != null) {
            userRepository.findByIdWithRoles(request.getCreatedBy())
                    .orElseThrow(() -> new UserNotFoundException(request.getCreatedBy()));
        }

        Note note = new Note();
        note.setEntityId(request.getEntityId());
        note.setEntityType(request.getEntityType());
        note.setContent(request.getContent());
//        note.getCreatedBy(request.getCreatedBy());
        note.setCreatedAt(new Date());

        Note savedNote = noteRepository.save(note);

        return toNoteResponse(savedNote);
    }

    // Helper methods

    private void validateEntity(String entityType, Integer entityId) {
        boolean exists = false;

        switch (entityType) {
            case "LEAD":
                exists = leadRepository.existsById(entityId);
                break;
            case "ACCOUNT":
                exists = accountRepository.existsById(entityId);
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

    private NoteResponse toNoteResponse(Note note) {
        NoteResponse response = new NoteResponse();
        response.setId(note.getId());
        response.setEntityType(note.getEntityType());
        response.setEntityId(note.getEntityId());
        response.setContent(note.getContent());
//        response.setCreatedBy(note.getCreatedBy());
        response.setCreatedAt(new Date());


        // Get entity name
        String entityName = getEntityName(note.getEntityType(), note.getEntityId());
        response.setEntityName(entityName);

        // Get creator name
//        if (note.getCreatedBy() != null) {
//            String creatorName = userRepository.findByIdWithRoles(note.getCreatedBy())
//                    .map(User::getName)
//                    .orElse("Unknown");
//            response.setCreatedByName(creatorName);
//        }

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
                    return accountRepository.findById(entityId)
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
