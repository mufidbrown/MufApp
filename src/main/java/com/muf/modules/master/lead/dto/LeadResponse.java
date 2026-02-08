package com.muf.modules.master.lead.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class LeadResponse {
    private Integer id;
    private String name;
    private String phone;
    private String email;
    private String sourceCode;
    private String sourceName;
    private String status;
    private Integer ownerId;
    private String ownerName;
    private Date createdAt;
}