package com.muf.modules.file.entity.domain;

import com.muf.modules.file.entity.FileTypeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table(name = "file_type", uniqueConstraints = { @UniqueConstraint(columnNames = { "code", "is_deleted" }) })
public class FileType extends FileTypeEntity {

}
