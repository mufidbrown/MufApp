package com.muf.modules.file.repository;

import com.muf.modules.file.entity.domain.FileType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileTypeRepository extends JpaRepository<FileType, Integer> {

    Optional<FileType> findByCodeAndIsDeleted(String code, Integer isDeleted);
}
