package com.muf.modules.master.lead.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AssignLeadRequest {
    @NotNull(message = "User ID is required")
    private Integer userId;
}
