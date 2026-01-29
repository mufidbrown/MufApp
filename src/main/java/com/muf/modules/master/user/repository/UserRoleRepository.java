package com.muf.modules.master.user.repository;

import com.muf.modules.master.user.entity.domain.User;
import com.muf.modules.master.user.entity.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    @Query("SELECT ur FROM UserRole ur WHERE ur.user = :user AND ur.isDeleted = 0")
    List<UserRole> findByUser(@Param("user") User user);
}

