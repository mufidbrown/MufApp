package com.muf.modules.attribute;


import com.muf.base.entity.BaseEntity;
import com.muf.common.converter.JpaConverterJson;
import com.muf.model.ListOfValue;
import com.muf.modules.formobject.FormObject;
import com.muf.modules.translation.TranslationKey;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
@MappedSuperclass
public class AttributeEntity extends BaseEntity implements Serializable {

    /* set translation */
    @Transient
    private String nameTranslated;

    @ManyToOne
    @Where(clause = "is_deleted = " + BaseEntity.ENTITY_FLAG_NOT_DELETED)
    @JoinColumn(name = "translation_key_id", referencedColumnName = "id", nullable = false)
    private TranslationKey translationKey;

    @ManyToOne
    @Where(clause = "is_deleted = " + BaseEntity.ENTITY_FLAG_NOT_DELETED)
    @JoinColumn(name = "form_object_id", referencedColumnName = "id", nullable = false)
    private FormObject formObject;

    @Column(name = "list_of_value", nullable = true)
    @Convert(converter= JpaConverterJson.class)
    private List<ListOfValue> listOfValue;

    @Column(name = "data_source",length = 128, nullable = true)
    private String dataSource;

    @Column(name = "group_name",length = 32, nullable = true)
    private String groupName;

    @Column(name = "description",length = 512, nullable = true)
    private String description;

    @Column(name = "is_score", nullable = false)
//    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isScore;

    @Column(name = "is_editable_mandatory", nullable = false)
//    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isEditableMandatory;

    @Column(name = "is_editable_approval", nullable = false)
//    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isEditableApproval;

    @Column(name = "class_name", length = 128)
    private String className;

    public String getNameTranslated() {
        return nameTranslated;
    }

    public void setNameTranslated(String nameTranslated) {
        this.nameTranslated = nameTranslated;
    }

    public TranslationKey getTranslationKey() {
        return translationKey;
    }

    public void setTranslationKey(TranslationKey translationKey) {
        this.translationKey = translationKey;
    }

    public FormObject getFormObject() {
        return formObject;
    }

    public void setFormObject(FormObject formObject) {
        this.formObject = formObject;
    }

    public List<ListOfValue> getListOfValue() {
        return listOfValue;
    }

    public void setListOfValue(List<ListOfValue> listOfValue) {
        this.listOfValue = listOfValue;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsScore() {
        return isScore;
    }

    public void setIsScore(Boolean isScore) {
        this.isScore = isScore;
    }

    public Boolean getIsEditableMandatory() {
        return isEditableMandatory;
    }

    public void setIsEditableMandatory(Boolean isEditableMandatory) {
        this.isEditableMandatory = isEditableMandatory;
    }

    public Boolean getIsEditableApproval() {
        return isEditableApproval;
    }

    public void setIsEditableApproval(Boolean isEditableApproval) {
        this.isEditableApproval = isEditableApproval;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}
