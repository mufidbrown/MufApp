package com.muf.modules.workflow.reminder;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class CreateReminderRequest {
    @NotBlank(message = "Entity type is required")
    @Pattern(regexp = "LEAD|ACCOUNT|CONTACT|OPPORTUNITY|TASK", message = "Entity type must be LEAD, ACCOUNT, CONTACT, OPPORTUNITY, or TASK")
    private String entityType;

    @NotNull(message = "Entity ID is required")
    private Integer entityId;

    @NotNull(message = "Remind time is required")
    @Future(message = "Remind time must be in the future")
    private LocalDateTime remindAt;

    private String status; // PENDING, SENT, CANCELLED
}
