package com.muf.modules.workflow.note;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class NoteResponse {
    private Integer id;
    private String entityType;
    private Integer entityId;
    private String entityName; // Name of entity
    private String content;
    private String createdBy;
    private String createdByName;
    private Date createdAt;
}
