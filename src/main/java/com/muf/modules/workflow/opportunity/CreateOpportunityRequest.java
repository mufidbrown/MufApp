package com.muf.modules.workflow.opportunity;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreateOpportunityRequest {
    @NotNull(message = "Lead ID is required")
    private Integer leadId;

    @DecimalMin(value = "0.0", inclusive = false, message = "Value must be greater than 0")
    private BigDecimal value;

    @Min(value = 0, message = "Probability must be between 0 and 100")
    @Max(value = 100, message = "Probability must be between 0 and 100")
    private Integer probability;

    @Future(message = "Expected close date must be in the future")
    private LocalDate expectedCloseDate;
}

