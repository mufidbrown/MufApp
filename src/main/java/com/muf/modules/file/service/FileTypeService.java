package com.muf.modules.file.service;


import com.muf.base.entity.BaseEntity;
import com.muf.modules.file.entity.domain.FileType;
import com.muf.modules.file.repository.FileTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class FileTypeService {

    private FileTypeRepository fileTypeRepository;

    public FileType getByCode(String code) {
        return fileTypeRepository
                .findByCodeAndIsDeleted(code, BaseEntity.ENTITY_FLAG_NOT_DELETED)
                .orElseThrow(() -> new RuntimeException("FileType not found"));
    }
}
