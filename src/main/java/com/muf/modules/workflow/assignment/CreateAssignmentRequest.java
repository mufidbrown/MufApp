package com.muf.modules.workflow.assignment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreateAssignmentRequest {
    @NotBlank(message = "Entity type is required")
    @Pattern(regexp = "LEAD|ACCOUNT|CONTACT|OPPORTUNITY|TASK", message = "Entity type must be LEAD, ACCOUNT, CONTACT, OPPORTUNITY, or TASK")
    private String entityType;

    @NotNull(message = "Entity ID is required")
    private Integer entityId;

    @NotNull(message = "User ID is required")
    private Integer userId;
}
