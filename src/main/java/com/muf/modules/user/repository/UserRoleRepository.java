package com.muf.modules.user.repository;

import com.muf.modules.user.entity.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    List<UserRole> findByUserId(Integer userId);

    List<UserRole> findByRoleId(Integer roleId);
    Optional<UserRole> findByUserIdAndRoleId(Integer userId, Integer roleId);
    List<UserRole> findByUserIdAndRoleCode(Integer userId, String roleCode);

}
