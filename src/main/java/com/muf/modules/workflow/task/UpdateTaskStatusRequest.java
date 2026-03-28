package com.muf.modules.workflow.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateTaskStatusRequest {
    @NotBlank(message = "Status is required")
    @Pattern(regexp = "TODO|IN_PROGRESS|COMPLETED|CANCELLED", message = "Status must be TODO, IN_PROGRESS, COMPLETED, or CANCELLED")
    private String status;
}
