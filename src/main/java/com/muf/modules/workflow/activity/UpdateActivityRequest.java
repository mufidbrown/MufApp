package com.muf.modules.workflow.activity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateActivityRequest {
    private String subject;
    private String note;
    private LocalDateTime dueDate;
    private String status;
}
