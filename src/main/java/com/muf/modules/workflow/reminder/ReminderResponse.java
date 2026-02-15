package com.muf.modules.workflow.reminder;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ReminderResponse {
    private Integer id;
    private String entityType;
    private Integer entityId;
    private String entityName;
    private LocalDateTime remindAt;
    private String status;
}
