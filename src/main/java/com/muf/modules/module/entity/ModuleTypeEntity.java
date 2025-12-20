package com.muf.modules.module.entity;


import com.muf.base.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;

@SuppressWarnings("serial")
@MappedSuperclass
public class ModuleTypeEntity extends BaseEntity implements Serializable {

    @Column(name = "name", length = 32, nullable = false)
    private String name;

    @Column(name = "description", length = 512, nullable = true)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
