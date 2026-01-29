package com.muf.modules.master.lead.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LeadAssignmentResponse {
    private Integer leadId;
    private String leadName;
    private Integer userId;
    private String userName;
    private LocalDateTime assignedAt;
}
