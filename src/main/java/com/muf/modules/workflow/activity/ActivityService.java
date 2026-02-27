package com.muf.modules.workflow.activity;

public interface ActivityService {
    ActivityResponse createActivity(CreateActivityRequest request);
    ActivityResponse updateActivity(Integer id, UpdateActivityRequest request);
/*    ActivityResponse getActivityById(Integer id);
    ActivityResponse getActivitiesByEntity(String entityType, Integer entityId);
    ActivityResponse getActivitiesByStatus(String status);*/
}
