package com.muf.model;

import com.muf.modules.file.entity.domain.File;

public class FileObject {

    private Boolean isUploaded = false;
    private Boolean isChange = false;
    private Boolean isNew = false;
    private Boolean isDeleted = false;
    private File file;
    private String urlIconImg;
    public Boolean getIsUploaded() {
        return isUploaded;
    }
    public void setIsUploaded(Boolean isUploaded) {
        this.isUploaded = isUploaded;
    }
    public Boolean getIsChange() {
        return isChange;
    }
    public void setIsChange(Boolean isChange) {
        this.isChange = isChange;
    }
    public Boolean getIsNew() {
        return isNew;
    }
    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }
    public Boolean getIsDeleted() {
        return isDeleted;
    }
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
    }
    public String getUrlIconImg() {
        return urlIconImg;
    }
    public void setUrlIconImg(String urlIconImg) {
        this.urlIconImg = urlIconImg;
    }

}
