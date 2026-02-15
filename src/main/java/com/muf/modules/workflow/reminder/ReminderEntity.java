package com.muf.modules.workflow.reminder;

import com.muf.base.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@MappedSuperclass
public class ReminderEntity extends BaseEntity implements Serializable {

    @Column(name = "entity_type", length = 30)
    private String entityType;

    @Column(name = "entity_id")
    private Integer entityId;

    @Column(name = "remind_at")
    private LocalDateTime remindAt;

    @Column(name = "status", length = 30)
    private String status;
}
