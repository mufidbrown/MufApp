package com.muf.modules.file.repository;

import com.muf.modules.file.entity.domain.FileLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileLocationRepository extends JpaRepository<FileLocation, Integer> {

    Optional<FileLocation> findByCodeAndIsDeleted(String code, Integer isDeleted);
}
