package com.muf.modules.master.lead.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class LeadAssignmentId implements Serializable {
    private Integer leadId;
    private Integer userId;
}
