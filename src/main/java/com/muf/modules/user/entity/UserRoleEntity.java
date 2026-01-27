package com.muf.modules.user.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.muf.base.entity.BaseEntity;
import com.muf.modules.user.entity.domain.Role;
import com.muf.modules.user.entity.domain.User;
import jakarta.persistence.*;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class UserRoleEntity extends BaseEntity implements Serializable {

    @Transient
    private Integer crudOperation;

    @JsonIgnore
    @ManyToOne
    @Where(clause = "is_deleted = " + BaseEntity.ENTITY_FLAG_NOT_DELETED)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne
    @Where(clause = "is_deleted = " + BaseEntity.ENTITY_FLAG_NOT_DELETED)
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;

    @Column(name = "start_date", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date startDate;

    @Column(name = "end_date", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date endDate;

    @Column(name = "is_default")
    private Boolean isDefault = false;

    @PrePersist
    protected void prePersist() {
        isDefault = isDefault == null ? false : isDefault;
    }

    public Integer getCrudOperation() {
        return crudOperation;
    }

    public void setCrudOperation(Integer crudOperation) {
        this.crudOperation = crudOperation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }
}

