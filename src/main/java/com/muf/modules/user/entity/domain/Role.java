package com.muf.modules.user.entity.domain;

import com.muf.modules.user.entity.RoleEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table(name = "roles", uniqueConstraints = { @UniqueConstraint(columnNames = { "code", "is_deleted" }) })
public class Role extends RoleEntity {

    @Column(name = "reference_code", length = 64)
    private String referenceCode;

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }


}

