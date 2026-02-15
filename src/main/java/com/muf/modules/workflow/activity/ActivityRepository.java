package com.muf.modules.workflow.activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {

    @Query("SELECT a FROM Activity a WHERE a.relatedType = :relatedType AND a.relatedId = :relatedId ORDER BY a.createdAt DESC")
    List<Activity> findByRelatedEntity(@Param("relatedType") String relatedType, @Param("relatedId") Integer relatedId);

    @Query("SELECT a FROM Activity a WHERE a.status = :status ORDER BY a.dueDate ASC")
    List<Activity> findByStatus(@Param("status") String status);

}
