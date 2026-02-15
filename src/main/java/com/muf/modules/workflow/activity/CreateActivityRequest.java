package com.muf.modules.workflow.activity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateActivityRequest {

    @NotBlank(message = "Type is required")
    @Pattern(regexp = "CALL|EMAIL|MEETING|TASK", message = "Type must be CALL, EMAIL, MEETING, or TASK")
    private String type;

    @NotBlank(message = "Subject is required")
    @Size(max = 150, message = "Subject must not exceed 150 characters")
    private String subject;

    @Size(max = 5000, message = "Note must not exceed 5000 characters")
    private String note;

    @NotBlank(message = "Related type is required")
    @Pattern(regexp = "LEAD|ACCOUNT|CONTACT|OPPORTUNITY", message = "Related type must be LEAD, ACCOUNT, CONTACT, or OPPORTUNITY")
    private String relatedType;

    @NotNull(message = "Related ID is required")
    private Integer relatedId;

    private LocalDateTime dueDate;

    private String status;

}
