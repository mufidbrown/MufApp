package com.muf.modules.workflow.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query("SELECT t FROM Task t LEFT JOIN FETCH t.assignedUser WHERE t.id = :id")
    Optional<Task> findByIdWithUser(@Param("id") Integer id);

    @Query("SELECT t FROM Task t WHERE t.assignedTo = :userId ORDER BY t.dueDate ASC")
    List<Task> findByAssignedTo(@Param("userId") Integer userId);

    @Query("SELECT t FROM Task t WHERE t.status = :status ORDER BY t.dueDate ASC")
    List<Task> findByStatus(@Param("status") String status);

    @Query("SELECT t FROM Task t WHERE t.priority = :priority ORDER BY t.dueDate ASC")
    List<Task> findByPriority(@Param("priority") String priority);

    @Query("SELECT t FROM Task t WHERE t.dueDate < :now AND t.status != 'COMPLETED' AND t.status != 'CANCELLED' ORDER BY t.dueDate ASC")
    List<Task> findOverdueTasks(@Param("now") LocalDateTime now);

}
