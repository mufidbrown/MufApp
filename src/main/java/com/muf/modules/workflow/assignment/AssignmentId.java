package com.muf.modules.workflow.assignment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class AssignmentId implements Serializable {
    private String entityType;
    private Integer entityId;
    private Integer userId;

    // WAJIB: constructor kosong untuk JPA
    public AssignmentId() {
    }

    public AssignmentId(String entityType, Integer entityId, Integer userId) {
        this.entityType = entityType;
        this.entityId = entityId;
        this.userId = userId;
    }
}
