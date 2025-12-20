package com.muf.modules.file.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.muf.base.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;

@SuppressWarnings("serial")
@MappedSuperclass
public class FileLocationEntity extends BaseEntity implements Serializable {

    @JsonIgnore
    @Column(name = "path", length = 128)
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}

