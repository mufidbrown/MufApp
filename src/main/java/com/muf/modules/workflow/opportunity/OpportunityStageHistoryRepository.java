package com.muf.modules.workflow.opportunity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpportunityStageHistoryRepository extends JpaRepository<OpportunityStageHistory, Integer> {

    @Query("SELECT osh FROM OpportunityStageHistory osh WHERE osh.opportunityId = :opportunityId ORDER BY osh.changedAt DESC")
    List<OpportunityStageHistory> findByOpportunityIdOrderByChangedAtDesc(@Param("opportunityId") Integer opportunityId);
}
