package com.muf.modules.module.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.muf.base.entity.BaseEntity;
import com.muf.modules.module.entity.domain.ModuleDetail;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
@MappedSuperclass
public class ModuleGroupEntity extends BaseEntity implements Serializable {

    @JsonIgnore
    @OneToMany(mappedBy = "moduleGroup", fetch= FetchType.EAGER, cascade = CascadeType.REMOVE)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ModuleDetail> moduleDetailList;

    @Column(name = "name", length = 32, nullable = false )
    private String name;

    @Column(name = "is_active", nullable = false)
//    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isActive;

    @PrePersist
    protected void prePersist() {
        isActive = isActive == null ? false : isActive;
    }

    public List<ModuleDetail> getModuleDetailList() {
        return moduleDetailList;
    }

    public void setModuleDetailList(List<ModuleDetail> moduleDetailList) {
        this.moduleDetailList = moduleDetailList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

}

