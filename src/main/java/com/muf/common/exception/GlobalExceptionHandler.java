package com.muf.common.exception;

import com.muf.base.exception.BusinessException;
import com.muf.common.exception.customconversion.ConversionNotFoundException;
import com.muf.common.exception.customconversion.LeadAlreadyConvertedException;
import com.muf.common.exception.customconversion.LeadNotQualifiedException;
import com.muf.common.exception.customconversion.NoOpportunityForLeadException;
import com.muf.common.exception.customcustomercontact.ContactNotFoundException;
import com.muf.common.exception.customcustomercontact.CustomerAccountNotFoundException;
import com.muf.common.exception.customcustomercontact.DuplicateContactEmailException;
import com.muf.common.exception.customcustomercontact.DuplicateCustomerAccountException;
import com.muf.common.exception.customfollowup.*;
import com.muf.common.exception.customleadflow.*;
import com.muf.common.exception.customopportunity.*;
import com.muf.common.exception.customtaskmanagement.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ... lead management exception handlers ...

    @ExceptionHandler(DuplicatePhoneException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicatePhone(DuplicatePhoneException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(LeadSourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleLeadSourceNotFound(LeadSourceNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LeadNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleLeadNotFound(LeadNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFound(UserNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidStatusTransitionException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidStatusTransition(InvalidStatusTransitionException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


    // ... activity follow up exception handlers ...

    @ExceptionHandler(InvalidRelatedEntityException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidRelatedEntity(InvalidRelatedEntityException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidActivityTypeException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidActivityType(InvalidActivityTypeException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PastReminderException.class)
    public ResponseEntity<Map<String, Object>> handlePastReminder(PastReminderException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ActivityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleActivityNotFound(ActivityNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoteNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNoteNotFound(NoteNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReminderNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleReminderNotFound(ReminderNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


    // ... opportunity management exception handlers ...

    @ExceptionHandler(OpportunityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleOpportunityNotFound(OpportunityNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidLeadStatusException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidLeadStatus(InvalidLeadStatusException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidStageTransitionException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidStageTransition(InvalidStageTransitionException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidProbabilityException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidProbability(InvalidProbabilityException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FinalStageException.class)
    public ResponseEntity<Map<String, Object>> handleFinalStage(FinalStageException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


    // ... customeraccount & contact management exception handlers ...

    @ExceptionHandler(CustomerAccountNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleCustomerAccountNotFound(CustomerAccountNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ContactNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleContactNotFound(ContactNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateCustomerAccountException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateCustomerAccount(DuplicateCustomerAccountException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DuplicateContactEmailException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateContactEmail(DuplicateContactEmailException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }


    // ... task management exception handlers ...

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleTaskNotFound(TaskNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidTaskStatusTransitionException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidTaskStatusTransition(InvalidTaskStatusTransitionException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FinalTaskStatusException.class)
    public ResponseEntity<Map<String, Object>> handleFinalTaskStatus(FinalTaskStatusException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidTaskPriorityException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidTaskPriority(InvalidTaskPriorityException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AssignmentNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleAssignmentNotFound(AssignmentNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


    // ... conversion exception handlers ...

    @ExceptionHandler(LeadAlreadyConvertedException.class)
    public ResponseEntity<Map<String, Object>> handleLeadAlreadyConverted(LeadAlreadyConvertedException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(LeadNotQualifiedException.class)
    public ResponseEntity<Map<String, Object>> handleLeadNotQualified(LeadNotQualifiedException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoOpportunityForLeadException.class)
    public ResponseEntity<Map<String, Object>> handleNoOpportunityForLead(NoOpportunityForLeadException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConversionNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleConversionNotFound(ConversionNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Map<String, Object>> handleBusinessException(BusinessException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        return buildErrorResponse("Internal server error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", status.value());
        response.put("error", status.getReasonPhrase());
        response.put("message", message);
        return new ResponseEntity<>(response, status);
    }
}
