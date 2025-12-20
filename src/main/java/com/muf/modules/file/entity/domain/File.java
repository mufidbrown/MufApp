package com.muf.modules.file.entity.domain;

import com.muf.modules.file.entity.FileEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "files")
public class File extends FileEntity {

}
