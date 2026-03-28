package com.muf.modules.master.conversion;

import com.muf.modules.master.contact.ContactSummary;
import com.muf.modules.workflow.opportunity.OpportunitySummary;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConversionDetailResponse {
    private Integer leadId;
    private LeadSummary lead;
    private AccountSummary account;
    private ContactSummary contact;
    private OpportunitySummary opportunity;
    private LocalDateTime convertedAt;
}
