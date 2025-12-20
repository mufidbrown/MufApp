package com.muf.modules.file.entity.domain;

import com.muf.modules.file.entity.FileLocationEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table(
        name = "file_locations",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "code")
        }
)
public class FileLocation extends FileLocationEntity {

}
