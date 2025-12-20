package com.muf.modules.file.entity.domain;

import com.muf.modules.file.entity.FileExtensionEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table(name = "file_extension", uniqueConstraints = { @UniqueConstraint(columnNames = { "code", "is_deleted" }) })
public class FileExtension extends FileExtensionEntity {

}