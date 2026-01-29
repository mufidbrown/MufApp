package com.muf.modules.master.lead.repository;

import com.muf.modules.master.lead.entity.domain.LeadHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeadHistoryRepository extends JpaRepository<LeadHistory, Integer> {

    @Query("SELECT lh FROM LeadHistory lh WHERE lh.leadId = :leadId ORDER BY lh.changedAt DESC")
    List<LeadHistory> findByLeadIdOrderByChangedAtDesc(@Param("leadId") Integer leadId);
}
