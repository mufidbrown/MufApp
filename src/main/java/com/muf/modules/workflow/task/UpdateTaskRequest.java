package com.muf.modules.workflow.task;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateTaskRequest {
    @Size(max = 150, message = "Title must not exceed 150 characters")
    private String title;

    private LocalDateTime dueDate;

    @Pattern(regexp = "LOW|MEDIUM|HIGH|URGENT", message = "Priority must be LOW, MEDIUM, HIGH, or URGENT")
    private String priority;

    @Pattern(regexp = "TODO|IN_PROGRESS|COMPLETED|CANCELLED", message = "Status must be TODO, IN_PROGRESS, COMPLETED, or CANCELLED")
    private String status;

    private Integer assignedTo;
}
