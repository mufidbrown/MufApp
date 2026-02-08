package com.muf.modules.master.lead.entity;

import com.muf.base.entity.BaseEntity;
import com.muf.modules.master.lead.entity.domain.Lead;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class LeadHistoryEntity extends BaseEntity implements Serializable {

    @Column(name = "lead_id", nullable = false)
    private Integer leadId;

    @ManyToOne
    @JoinColumn(name = "lead_id", insertable = false, updatable = false)
    private Lead lead;

    @Column(name = "from_status", length = 30)
    private String fromStatus;

    @Column(name = "to_status", length = 30)
    private String toStatus;

    @Column(name = "changed_at")
    private LocalDateTime changedAt;

//    @PrePersist
//    protected void onCreate() {
//        changedAt = LocalDateTime.now();
//    }

//    @PrePersist
//    protected void onCreate() {
//        changedAt = new Date();
//    }

}
