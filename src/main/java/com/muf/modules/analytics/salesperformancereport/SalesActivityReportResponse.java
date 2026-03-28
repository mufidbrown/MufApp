package com.muf.modules.analytics.salesperformancereport;

import lombok.Data;

import java.util.Map;

@Data
public class SalesActivityReportResponse {
    private Integer totalActivities;
    private Map<String, Integer> activitiesByType;
    private Map<String, Integer> activitiesByStatus;
    private Integer userId;
    private String userName;
}
