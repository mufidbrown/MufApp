package com.muf.modules.workflow.opportunity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Integer> {

    @Query("SELECT o FROM Opportunity o LEFT JOIN FETCH o.lead WHERE o.id = :id")
    Optional<Opportunity> findByIdWithLead(@Param("id") Integer id);

    @Query("SELECT o FROM Opportunity o WHERE o.stage = :stage")
    List<Opportunity> findByStage(@Param("stage") String stage);

    @Query("SELECT o FROM Opportunity o WHERE o.leadId = :leadId")
    List<Opportunity> findByLeadId(@Param("leadId") Integer leadId);

    @Query("SELECT COUNT(o) > 0 FROM Opportunity o WHERE o.leadId = :leadId")
    boolean existsByLeadId(@Param("leadId") Integer leadId);

}
