package com.muf.modules.module.repository;

import com.muf.modules.module.entity.domain.Module;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Integer> {


    @Query("""
        SELECT m
        FROM Module m
        WHERE m.code = :code
          AND m.isDeleted = 0
    """)
    Module getByDefault(String code);
}
