package com.muf.modules.workflow.assignment;

import lombok.Data;

@Data
public class AssignmentResponse {
    private String entityType;
    private Integer entityId;
    private String entityName;
    private Integer userId;
    private String userName;
}
