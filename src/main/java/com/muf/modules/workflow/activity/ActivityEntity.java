package com.muf.modules.workflow.activity;

import com.muf.base.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class ActivityEntity extends BaseEntity implements Serializable {

    @Column(name = "type", length = 30, nullable = false)
    private String type;

    @Column(name = "subject", length = 150)
    private String subject;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    // Polymorphic relation
    @Column(name = "related_type", length = 30)
    private String relatedType;

    @Column(name = "related_id")
    private Long relatedId;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "status", length = 30)
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
