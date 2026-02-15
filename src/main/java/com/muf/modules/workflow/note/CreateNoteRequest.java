package com.muf.modules.workflow.note;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateNoteRequest {
    @NotBlank(message = "Entity type is required")
    @Pattern(regexp = "LEAD|ACCOUNT|CONTACT|OPPORTUNITY|TASK", message = "Entity type must be LEAD, ACCOUNT, CONTACT, OPPORTUNITY, or TASK")
    private String entityType;

    @NotNull(message = "Entity ID is required")
    private Integer entityId;

    @NotBlank(message = "Content is required")
    @Size(max = 10000, message = "Content must not exceed 10000 characters")
    private String content;

    private Integer createdBy; // User ID
}
