package com.muf.modules.formobject;


import com.muf.base.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.Type;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class FormObjectEntity extends BaseEntity {

    @Column(name = "name", length = 32, nullable = false)
    private String name;

    @Column(name = "description", length = 512, nullable = true)
    private String description;

    @Column(name = "component", length = 32, nullable = true)
    private String component;

    @Column(name = "directive", length = 32, nullable = true)
    private String directive;

    @Column(name = "size", length = 32, nullable = false)
    private String size;

    @Column(name = "is_value", nullable = true)
//    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isValue;

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

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getDirective() {
        return directive;
    }

    public void setDirective(String directive) {
        this.directive = directive;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Boolean getIsValue() {
        return isValue;
    }

    public void setIsValue(Boolean isValue) {
        this.isValue = isValue;
    }

}

