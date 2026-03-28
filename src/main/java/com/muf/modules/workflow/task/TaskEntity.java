package com.muf.modules.workflow.task;

import com.muf.base.entity.BaseEntity;
import com.muf.modules.master.user.entity.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class TaskEntity extends BaseEntity implements Serializable {

    @Column(name = "title", length = 150, nullable = false)
    private String title;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "priority", length = 30)
    private String priority;

    @Column(name = "status", length = 30)
    private String status;

    @Column(name = "assigned_to")
    private Integer assignedTo;

    @ManyToOne
    @JoinColumn(name = "assigned_to", insertable = false, updatable = false)
    private User assignedUser;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "assigned_to")
//    private User assignedUser;

}
