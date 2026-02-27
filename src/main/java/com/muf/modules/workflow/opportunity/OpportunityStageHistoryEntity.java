package com.muf.modules.workflow.opportunity;

import com.muf.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@MappedSuperclass
public class OpportunityStageHistoryEntity extends BaseEntity implements Serializable {

    @Column(name = "opportunity_id", nullable = false)
    private Integer opportunityId;

    @ManyToOne
    @JoinColumn(name = "opportunity_id", insertable = false, updatable = false)
    private Opportunity opportunity;

    @Column(name = "from_stage", length = 50)
    private String fromStage;

    @Column(name = "to_stage", length = 50)
    private String toStage;

    @Column(name = "changed_at")
    private LocalDateTime changedAt;

//    @PrePersist
//    protected void onCreate() {
//        changedAt = LocalDateTime.now();
//    }
}
