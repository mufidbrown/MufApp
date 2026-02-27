package com.muf.modules.workflow.opportunity;

import com.muf.base.entity.BaseEntity;
import com.muf.modules.master.lead.entity.domain.Lead;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
public class OpportunityEntity extends BaseEntity implements Serializable {

    @Column(name = "lead_id", nullable = false)
    private Integer leadId;

    @ManyToOne
    @JoinColumn(name = "lead_id", insertable = false, updatable = false)
    private Lead lead;

    @Column(name = "stage", length = 50, nullable = false)
    private String stage;

    @Column(name = "value", precision = 18, scale = 2)
    private BigDecimal value;

    @Column(name = "probability")
    private Integer probability;

    @Column(name = "expected_close_date")
    private LocalDate expectedCloseDate;
}
