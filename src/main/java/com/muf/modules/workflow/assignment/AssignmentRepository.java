package com.muf.modules.workflow.assignment;

import com.muf.modules.master.user.entity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, AssignmentId> {

    @Query("SELECT a FROM Assignment a WHERE a.entityType = :entityType AND a.entityId = :entityId")
    List<Assignment> findByEntity(@Param("entityType") String entityType, @Param("entityId") Integer entityId);

    @Query("SELECT a FROM Assignment a WHERE a.userId = :userId")
    List<Assignment> findByUserId(@Param("userId") Integer userId);

    Long user(User user);
}