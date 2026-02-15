package com.muf.modules.workflow.activity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ActivityResponse {
    private Integer id;
    private String type;
    private String subject;
    private String note;
    private String relatedType;
    private Integer relatedId;
    private String relatedName; // Name of related entity
    private LocalDateTime dueDate;
    private String status;
    private Date createdAt;
}
