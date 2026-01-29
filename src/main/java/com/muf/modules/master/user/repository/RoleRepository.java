package com.muf.modules.master.user.repository;

import com.muf.modules.master.user.entity.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("SELECT r FROM Role r WHERE r.name = :name AND r.isDeleted = 0")
    Optional<Role> findByName(@Param("name") String name);
}