package com.muf.modules.workflow.task;

import java.util.List;

public interface TaskService {
    TaskResponse createTask(CreateTaskRequest request);
    TaskResponse updateTask(Integer id, UpdateTaskRequest request);
    TaskResponse updateTaskStatus(Integer id, UpdateTaskStatusRequest request);
    TaskResponse getTaskById(Integer id);
    List<TaskResponse> getTaskByAssignedUser(Integer id);
}
