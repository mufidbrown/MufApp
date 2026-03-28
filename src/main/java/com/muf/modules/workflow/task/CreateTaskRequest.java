package com.muf.modules.workflow.task;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class CreateTaskRequest {
    @NotBlank(message = "Title is required")
    @Size(max = 150, message = "Title must not exceed 150 characters")
    private String title;

    @Future(message = "Due date must be in the future")
    private Date dueDate;

    @Pattern(regexp = "LOW|MEDIUM|HIGH|URGENT", message = "Priority must be LOW, MEDIUM, HIGH, or URGENT")
    private String priority;

    @NotNull(message = "Assigned user is required")
    private Integer assignedTo;
}
