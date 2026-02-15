package com.muf.modules.workflow.note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

    @Query("SELECT n FROM Note n WHERE n.entityType = :entityType AND n.entityId = :entityId ORDER BY n.createdAt DESC")
    List<Note> findByEntity(@Param("entityType") String entityType, @Param("entityId") Integer entityId);
}
