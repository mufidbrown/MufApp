package com.muf.modules.master.lead.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class LeadHistoryResponse {
    private Integer id;
    private Integer leadId;
    private String fromStatus;
    private String toStatus;
    private LocalDateTime changedAt;
}
