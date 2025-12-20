package com.muf.modules.file.entity;


import com.muf.base.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class FileExtensionEntity extends BaseEntity {

    @Column(name = "name", length = 32, nullable = false)
    private String name;

    @Column(name = "description", length = 512)
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

