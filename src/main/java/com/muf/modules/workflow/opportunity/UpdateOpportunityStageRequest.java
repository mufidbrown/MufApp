package com.muf.modules.workflow.opportunity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateOpportunityStageRequest {
    @NotBlank(message = "Stage is required")
    @Pattern(regexp = "PROSPECTING|QUALIFICATION|PROPOSAL|NEGOTIATION|WON|LOST",
            message = "Stage must be PROSPECTING, QUALIFICATION, PROPOSAL, NEGOTIATION, WON, or LOST")
    private String stage;
}
