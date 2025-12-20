package com.muf.modules.file.entity;


import com.muf.base.entity.BaseEntity;
import com.muf.modules.file.entity.domain.FileType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.Where;

import java.io.Serializable;

@SuppressWarnings("serial")
@MappedSuperclass
public class FileEntity extends BaseEntity implements Serializable {

    @ManyToOne
    @Where(clause = "is_deleted = " + BaseEntity.ENTITY_FLAG_NOT_DELETED)
    @JoinColumn(name = "file_type_id", referencedColumnName = "id")
    private FileType fileType;

    @Column(name = "uploaded_file_name", length = 1024, nullable = false)
    private String uploadedFileName;

    @Column(name = "file_name", length = 1024, nullable = false)
    private String fileName;

    @Column(name = "size", nullable = false)
    private Long size;

    @Column(name = "extension", length = 8, nullable = false)
    private String extension;

    @Column(name = "is_image", length = 1, nullable = false)
//    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isImage;

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public String getUploadedFileName() {
        return uploadedFileName;
    }

    public void setUploadedFileName(String uploadedFileName) {
        this.uploadedFileName = uploadedFileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Boolean getIsImage() {
        return isImage;
    }

    public void setIsImage(Boolean isImage) {
        this.isImage = isImage;
    }

}

