package com.muf.modules.analytics.salesperformancereport;

import com.muf.base.entity.BaseEntity;
import com.muf.modules.master.user.entity.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@MappedSuperclass
public class SalesPerformanceEntity extends BaseEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "total_deal")
    private Integer totalDeal;

    @Column(name = "total_revenue")
    private BigDecimal totalRevenue;

    @Column(name = "win_rate")
    private Double winRate;

}



