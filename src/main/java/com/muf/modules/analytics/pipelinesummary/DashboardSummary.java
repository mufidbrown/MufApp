package com.muf.modules.analytics.pipelinesummary;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DashboardSummary {
    // Lead metrics
    private Integer totalLeads;
    private Integer qualifiedLeads;
    private Integer convertedLeads;

    // Opportunity metrics
    private Integer totalOpportunities;
    private Integer wonOpportunities;
    private Integer lostOpportunities;
    private BigDecimal totalRevenue;
    private BigDecimal pipelineValue;

    // Activity metrics
    private Integer totalActivities;
    private Integer completedActivities;
    private Integer pendingActivities;

    // Task metrics
    private Integer totalTasks;
    private Integer completedTasks;
    private Integer overdueTasks;

    // Conversion metrics
    private Double conversionRate;
    private Double winRate;
}
