package com.muf.modules.workflow.note;

import com.muf.base.entity.BaseEntity;
import com.muf.modules.master.user.entity.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public class NoteEntity extends BaseEntity implements Serializable {

    @Column(name = "entity_type", length = 30)
    private String entityType;

    @Column(name = "entity_id")
    private Integer entityId;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

//    @ManyToOne
//    @JoinColumn(name = "created_by", insertable = false, updatable = false)
//    private User creator;

}
