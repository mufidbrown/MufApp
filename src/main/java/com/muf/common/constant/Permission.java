package com.muf.common.constant;

public enum Permission {
    // Lead permissions
    LEAD_VIEW_OWN,
    LEAD_VIEW_TEAM,
    LEAD_VIEW_ALL,
    LEAD_CREATE,
    LEAD_UPDATE_OWN,
    LEAD_UPDATE_TEAM,
    LEAD_UPDATE_ALL,
    LEAD_DELETE,

    // Opportunity permissions
    OPPORTUNITY_VIEW_OWN,
    OPPORTUNITY_VIEW_TEAM,
    OPPORTUNITY_VIEW_ALL,
    OPPORTUNITY_CREATE,
    OPPORTUNITY_UPDATE_OWN,
    OPPORTUNITY_UPDATE_TEAM,
    OPPORTUNITY_UPDATE_ALL,

    // Activity permissions
    ACTIVITY_VIEW_OWN,
    ACTIVITY_VIEW_TEAM,
    ACTIVITY_VIEW_ALL,
    ACTIVITY_CREATE,
    ACTIVITY_UPDATE,

    // Task permissions
    TASK_VIEW_OWN,
    TASK_VIEW_TEAM,
    TASK_VIEW_ALL,
    TASK_CREATE,
    TASK_UPDATE,
    TASK_ASSIGN,

    // Admin permissions
    USER_MANAGE,
    ROLE_MANAGE,
    SYSTEM_SETTINGS,

    // Field-level permissions
    VIEW_SENSITIVE_DATA
}
