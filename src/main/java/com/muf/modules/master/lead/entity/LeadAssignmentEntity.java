package com.muf.modules.master.lead.entity;

import com.muf.base.entity.BaseEntity;
import com.muf.modules.master.lead.entity.domain.Lead;
import com.muf.modules.master.user.entity.domain.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class LeadAssignmentEntity extends BaseEntity implements Serializable {

    @Column(name = "lead_id", nullable = false)
    private Integer leadId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lead_id", insertable = false, updatable = false)
    private Lead lead;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "assigned_at")
    private Date assignedAt;

//    @PrePersist
//    protected void onCreate() {
//        assignedAt = new Date();
//    }


//    @Id
//    @Column(name = "lead_id")
//    private Integer leadId;
//
//    @Id
//    @Column(name = "user_id")
//    private Integer userId;
//
//    @ManyToOne
//    @JoinColumn(name = "lead_id", insertable = false, updatable = false)
//    private Lead lead;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", insertable = false, updatable = false)
//    private User user;
//
//    @Column(name = "assigned_at")
//    private LocalDateTime assignedAt;
//
//    @PrePersist
//    protected void onCreate() {
//        assignedAt = LocalDateTime.now();
//    }
}
