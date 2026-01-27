package com.muf.modules.user.entity;


import com.muf.base.entity.BaseEntity;
import com.muf.modules.language.Lang;
import com.muf.modules.user.entity.domain.Role;
import com.muf.modules.user.entity.domain.UserRole;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class UserEntity extends BaseEntity implements Serializable {


    @Column(name = "fullname", length = 255, nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

//    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Transient
    private String status;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "is_locked", nullable = false)
    private Boolean isLocked = false;

    @Column(name = "login_attempt", nullable = false)
    private Integer loginAttempt = 0;

    @Column(name = "login_attempt_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginAttemptDate;

    @Column(name = "log_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logDate;

    @Column(name = "used", nullable = false)
    private boolean used = false;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getLocked() {
        return isLocked;
    }

    public void setLocked(Boolean locked) {
        isLocked = locked;
    }

    public Integer getLoginAttempt() {
        return loginAttempt;
    }

    public void setLoginAttempt(Integer loginAttempt) {
        this.loginAttempt = loginAttempt;
    }

    public Date getLoginAttemptDate() {
        return loginAttemptDate;
    }

    public void setLoginAttemptDate(Date loginAttemptDate) {
        this.loginAttemptDate = loginAttemptDate;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }


    /*  @ManyToOne
    @Where(clause = "is_deleted = " + BaseEntity.ENTITY_FLAG_NOT_DELETED)
    @JoinColumn(name = "lang_id", referencedColumnName = "id", nullable = false)
    private Lang lang;*/


}
