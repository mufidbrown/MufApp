package com.muf.modules.module.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.muf.base.entity.BaseEntity;
import com.muf.modules.file.entity.domain.File;
import com.muf.modules.module.entity.domain.ModuleType;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
@MappedSuperclass
public class ModuleEntity extends BaseEntity implements Serializable {

    @Transient
    private String inputDependency;

    @Transient
    private String outputDependency;

    @Transient
    private String formatNumberGenerator;

    @OneToOne
    @Where(clause = "is_deleted = " + BaseEntity.ENTITY_FLAG_NOT_DELETED)
    @JoinColumn(name = "doc_file_id", referencedColumnName = "id")
    private File file;

    @ManyToOne
    @Where(clause = "is_deleted = " + BaseEntity.ENTITY_FLAG_NOT_DELETED)
    @JoinColumn(name = "module_type_id", referencedColumnName = "id")
    private ModuleType moduleType;

//    @JsonIgnore
//    @OneToMany(mappedBy = "module", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
//    @Fetch(value = FetchMode.SUBSELECT)
//    private List<ModuleDetail> moduleDetailList;

    @Column(name = "name", length = 64, nullable = false)
    private String name;

    @Column(name = "is_active", nullable = false)
//    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isActive;

    @Column(name = "description", length = 512)
    private String description;

    @Column(name = "is_stage")
//    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isStage;

    @Column(name = "is_starter")
//    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isStarter;

    @Column(name = "is_number_generator")
//    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isNumberGenerator;

    @Column(name = "is_schedule")
//    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isSchedule;

    @Column(name = "is_import")
//    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isImport;

    @Column(name = "path", length = 255, nullable = true)
    private String path;

    @Column(name = "object_name", length = 128, nullable = true)
    private String objectName;

    @Column(name = "service_name", length = 128, nullable = true)
    private String serviceName;

    @Column(name = "module_group_code", length = 128, nullable = false)
    private String moduleGroupCode;

    @Column(name = "approval_path", length = 255, nullable = true)
    private String approvalPath;

    @Column(name = "is_start_date")
//    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isStartDate;

    @Column(name = "is_end_date")
//    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isEndDate;

    @Column(name = "is_location")
//    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isLocation;

    @Column(name = "is_approve")
//    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isApprove;

    @Column(name = "is_reject")
//    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isReject;

    @Column(name = "is_hold")
//    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isHold;

    @Column(name = "is_revision")
//    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isRevision;

    @Column(name = "hierarchy")
    private Integer hierarchy;

    @PrePersist
    protected void prePersist() {
        hierarchy = hierarchy == null ? 0 : hierarchy;
    }

    public String getInputDependency() {
        return inputDependency;
    }

    public void setInputDependency(String inputDependency) {
        this.inputDependency = inputDependency;
    }

    public String getOutputDependency() {
        return outputDependency;
    }

    public void setOutputDependency(String outputDependency) {
        this.outputDependency = outputDependency;
    }

    public String getFormatNumberGenerator() {
        return formatNumberGenerator;
    }

    public void setFormatNumberGenerator(String formatNumberGenerator) {
        this.formatNumberGenerator = formatNumberGenerator;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public ModuleType getModuleType() {
        return moduleType;
    }

    public void setModuleType(ModuleType moduleType) {
        this.moduleType = moduleType;
    }

//    public List<ModuleDetail> getModuleDetailList() {
//        return moduleDetailList;
//    }
//
//    public void setModuleDetailList(List<ModuleDetail> moduleDetailList) {
//        this.moduleDetailList = moduleDetailList;
//    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsStage() {
        return isStage;
    }

    public void setIsStage(Boolean isStage) {
        this.isStage = isStage;
    }

    public Boolean getIsStarter() {
        return isStarter;
    }

    public void setIsStarter(Boolean isStarter) {
        this.isStarter = isStarter;
    }

    public Boolean getIsNumberGenerator() {
        return isNumberGenerator;
    }

    public void setIsNumberGenerator(Boolean isNumberGenerator) {
        this.isNumberGenerator = isNumberGenerator;
    }

    public Boolean getIsSchedule() {
        return isSchedule;
    }

    public void setIsSchedule(Boolean isSchedule) {
        this.isSchedule = isSchedule;
    }

    public Boolean getIsImport() {
        return isImport;
    }

    public void setIsImport(Boolean isImport) {
        this.isImport = isImport;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getModuleGroupCode() {
        return moduleGroupCode;
    }

    public void setModuleGroupCode(String moduleGroupCode) {
        this.moduleGroupCode = moduleGroupCode;
    }

    public String getApprovalPath() {
        return approvalPath;
    }

    public void setApprovalPath(String approvalPath) {
        this.approvalPath = approvalPath;
    }

    public Boolean getIsStartDate() {
        return isStartDate;
    }

    public void setIsStartDate(Boolean isStartDate) {
        this.isStartDate = isStartDate;
    }

    public Boolean getIsEndDate() {
        return isEndDate;
    }

    public void setIsEndDate(Boolean isEndDate) {
        this.isEndDate = isEndDate;
    }

    public Boolean getIsLocation() {
        return isLocation;
    }

    public void setIsLocation(Boolean isLocation) {
        this.isLocation = isLocation;
    }

    public Boolean getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(Boolean isApprove) {
        this.isApprove = isApprove;
    }

    public Boolean getIsReject() {
        return isReject;
    }

    public void setIsReject(Boolean isReject) {
        this.isReject = isReject;
    }

    public Boolean getIsHold() {
        return isHold;
    }

    public void setIsHold(Boolean isHold) {
        this.isHold = isHold;
    }

    public Boolean getIsRevision() {
        return isRevision;
    }

    public void setIsRevision(Boolean isRevision) {
        this.isRevision = isRevision;
    }

    public Integer getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(Integer hierarchy) {
        this.hierarchy = hierarchy;
    }

}