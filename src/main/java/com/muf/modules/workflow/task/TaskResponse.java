package com.muf.modules.workflow.task;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class TaskResponse {
    private Integer id;
    private String title;
    private Date dueDate;
    private String priority;
    private String status;
    private Integer assignedTo;
    private String assignedToName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

