package com.muf.modules.master.lead.repository;

import com.muf.modules.master.lead.entity.LeadAssignmentId;
import com.muf.modules.master.lead.entity.domain.Lead;
import com.muf.modules.master.lead.entity.domain.LeadAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeadAssignmentRepository extends JpaRepository<LeadAssignment, LeadAssignmentId> {

    @Query("SELECT la FROM LeadAssignment la WHERE la.leadId = :leadId")
    List<LeadAssignment> findByLeadId(@Param("leadId") Integer leadId);
}
