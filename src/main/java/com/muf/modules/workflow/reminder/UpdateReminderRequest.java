package com.muf.modules.workflow.reminder;

import jakarta.validation.constraints.Future;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateReminderRequest {
    @Future(message = "Remind time must be in the future")
    private LocalDateTime remindAt;

    private String status;
}

