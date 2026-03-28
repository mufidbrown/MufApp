package com.muf.modules.master.conversion;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConversionResponse {

    private Integer leadId;
    private String leadName;
    private String leadStatus;

    private Integer accountId;
    private String companyName;

    private Integer contactId;
    private String contactFullName;

    private Integer opportunityId;
    private String opportunityStage;

    private LocalDateTime convertedAt;
}
