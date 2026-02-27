package com.muf.modules.workflow.opportunity;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class UpdateOpportunityRequest {
    @DecimalMin(value = "0.0", inclusive = false, message = "Value must be greater than 0")
    private BigDecimal value;

    @Min(value = 0, message = "Probability must be between 0 and 100")
    @Max(value = 100, message = "Probability must be between 0 and 100")
    private Integer probability;

    private LocalDate expectedCloseDate;
}
