package com.muf.modules.workflow.reminder;

import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

@Registered
public interface ReminderRepository extends JpaRepository<Reminder, Integer> {

    @Query("SELECT r FROM Reminder r WHERE r.entityType = :entityType AND r.entityId = :entityId ORDER BY r.remindAt ASC")
    List<Reminder> findByEntity(@Param("entityType") String entityType, @Param("entityId") Integer entityId);

    @Query("SELECT r FROM Reminder r WHERE r.status = 'PENDING' AND r.remindAt <= :now")
    List<Reminder> findPendingReminders(@Param("now")LocalDateTime now);

}
