package com.muf.modules.workflow.task.mapper;

import com.muf.modules.workflow.task.Task;
import com.muf.modules.workflow.task.TaskResponse;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskResponse toTaskResponse(Task task) {
        TaskResponse response = new TaskResponse();
        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDueDate(task.getDueDate());
        response.setPriority(task.getPriority());
        response.setStatus(task.getStatus());
        response.setAssignedTo(task.getAssignedTo());

        // Handle lazy-loaded user
        if (task.getAssignedUser() != null) {
            response.setAssignedToName(task.getAssignedUser().getFullName());
        }


        return response;
    }
}
