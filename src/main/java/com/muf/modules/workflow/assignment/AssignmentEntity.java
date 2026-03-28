package com.muf.modules.workflow.assignment;

import com.muf.base.entity.BaseEntity;
import com.muf.modules.master.user.entity.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public class AssignmentEntity implements Serializable {

    @Id
    @Column(name = "entity_type", length = 30)
    private String entityType;

    @Id
    @Column(name = "entity_id")
    private Integer entityId;

    @Id
    @Column(name = "user_id")
    private Integer userId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

}
