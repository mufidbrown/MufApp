package com.muf.modules.language;


import com.muf.base.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import java.io.Serializable;

@SuppressWarnings("serial")
@MappedSuperclass
public class LangEntity extends BaseEntity implements Serializable {

//    @OneToOne
//    @Where(clause = "is_deleted = " + BaseEntity.ENTITY_FLAG_NOT_DELETED)
//    @JoinColumn(name = "icon_resource_id", referencedColumnName = "id")
//    private Resource resource;

    @Column(name = "name", length = 32, nullable = false)
    private String name;

    @Column(name = "is_read_only")
//    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isReadOnly;

    @Column(name = "is_active")
//    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isActive;

    @Column(name = "is_default")
//    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isDefault;

//    public Resource getResource() {
//        return resource;
//    }

//    public void setResource(Resource resource) {
//        this.resource = resource;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsReadOnly() {
        return isReadOnly;
    }

    public void setIsReadOnly(Boolean isReadOnly) {
        this.isReadOnly = isReadOnly;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

}