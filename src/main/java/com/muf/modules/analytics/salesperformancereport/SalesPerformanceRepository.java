package com.muf.modules.analytics.salesperformancereport;

import com.muf.modules.master.user.entity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SalesPerformanceRepository extends JpaRepository<User, Integer> {

    /**
     * Sales Performance Report
     * Returns sales metrics per user
     */
    @Query(value = """
        SELECT 
            u.id as userId,
            u.fullName as fullName,
            COUNT(o.id) as totalDeals,
            COALESCE(SUM(CASE WHEN o.stage = 'WON' THEN o.value ELSE 0 END), 0) as totalRevenue,
            COALESCE(
                CAST(SUM(CASE WHEN o.stage = 'WON' THEN 1 ELSE 0 END) AS FLOAT) / 
                NULLIF(COUNT(o.id), 0) * 100, 0
            ) as winRate,
            SUM(CASE WHEN o.stage = 'WON' THEN 1 ELSE 0 END) as wonDeals,
            SUM(CASE WHEN o.stage = 'LOST' THEN 1 ELSE 0 END) as lostDeals,
            COALESCE(AVG(CASE WHEN o.stage = 'WON' THEN o.value END), 0) as averageDealSize
        FROM users u
        LEFT JOIN leads l ON l.owner_id = u.id AND l.is_deleted = 0
        LEFT JOIN opportunities o ON o.lead_id = l.id
        WHERE u.is_deleted = 0
        GROUP BY u.id, u.fullName
        HAVING COUNT(o.id) > 0
        ORDER BY totalRevenue DESC
        """, nativeQuery = true)
    List<Map<String, Object>> getSalesPerformanceReport();

    /**
     * Pipeline Summary Report
     * Returns opportunity metrics per stage
     */
    @Query(value = """
        SELECT 
            o.stage,
            COUNT(o.id) as dealCount,
            COALESCE(SUM(o.value), 0) as totalValue,
            COALESCE(AVG(o.value), 0) as averageValue,
            COALESCE(AVG(o.probability), 0) as averageProbability
        FROM opportunities o
        WHERE o.stage IN ('PROSPECTING', 'QUALIFICATION', 'PROPOSAL', 'NEGOTIATION', 'WON', 'LOST')
        GROUP BY o.stage
        ORDER BY 
            CASE o.stage
                WHEN 'PROSPECTING' THEN 1
                WHEN 'QUALIFICATION' THEN 2
                WHEN 'PROPOSAL' THEN 3
                WHEN 'NEGOTIATION' THEN 4
                WHEN 'WON' THEN 5
                WHEN 'LOST' THEN 6
            END
        """, nativeQuery = true)
    List<Map<String, Object>> getPipelineSummaryReport();

    /**
     * Lead Conversion Report
     */
    @Query(value = """
        SELECT 
            COUNT(*) as totalLeads,
            SUM(CASE WHEN status = 'CONVERTED' THEN 1 ELSE 0 END) as convertedLeads,
            SUM(CASE WHEN status = 'QUALIFIED' THEN 1 ELSE 0 END) as qualifiedLeads,
            SUM(CASE WHEN status = 'LOST' THEN 1 ELSE 0 END) as lostLeads,
            COALESCE(
                CAST(SUM(CASE WHEN status = 'CONVERTED' THEN 1 ELSE 0 END) AS FLOAT) / 
                NULLIF(COUNT(*), 0) * 100, 0
            ) as conversionRate,
            COALESCE(
                CAST(SUM(CASE WHEN status = 'QUALIFIED' OR status = 'CONVERTED' THEN 1 ELSE 0 END) AS FLOAT) / 
                NULLIF(COUNT(*), 0) * 100, 0
            ) as qualificationRate
        FROM leads
        WHERE is_deleted = 0
        """, nativeQuery = true)
    Map<String, Object> getLeadConversionReport();

    /**
     * Activity Report by User
     */
    @Query(value = """
        SELECT 
            COUNT(*) as totalActivities,
            SUM(CASE WHEN type = 'CALL' THEN 1 ELSE 0 END) as calls,
            SUM(CASE WHEN type = 'EMAIL' THEN 1 ELSE 0 END) as emails,
            SUM(CASE WHEN type = 'MEETING' THEN 1 ELSE 0 END) as meetings,
            SUM(CASE WHEN type = 'TASK' THEN 1 ELSE 0 END) as tasks,
            SUM(CASE WHEN status = 'PENDING' THEN 1 ELSE 0 END) as pending,
            SUM(CASE WHEN status = 'COMPLETED' THEN 1 ELSE 0 END) as completed,
            SUM(CASE WHEN status = 'CANCELLED' THEN 1 ELSE 0 END) as cancelled
        FROM activities
        WHERE 1=1
        """, nativeQuery = true)
    Map<String, Object> getActivityReport();

    /**
     * Dashboard Summary
     */
    @Query(value = """
        SELECT 
            (SELECT COUNT(*) FROM leads WHERE is_deleted = 0) as totalLeads,
            (SELECT COUNT(*) FROM leads WHERE status = 'QUALIFIED' AND is_deleted = 0) as qualifiedLeads,
            (SELECT COUNT(*) FROM leads WHERE status = 'CONVERTED' AND is_deleted = 0) as convertedLeads,
            (SELECT COUNT(*) FROM opportunities) as totalOpportunities,
            (SELECT COUNT(*) FROM opportunities WHERE stage = 'WON') as wonOpportunities,
            (SELECT COUNT(*) FROM opportunities WHERE stage = 'LOST') as lostOpportunities,
            (SELECT COALESCE(SUM(value), 0) FROM opportunities WHERE stage = 'WON') as totalRevenue,
            (SELECT COALESCE(SUM(value), 0) FROM opportunities WHERE stage NOT IN ('WON', 'LOST')) as pipelineValue,
            (SELECT COUNT(*) FROM activities) as totalActivities,
            (SELECT COUNT(*) FROM activities WHERE status = 'COMPLETED') as completedActivities,
            (SELECT COUNT(*) FROM activities WHERE status = 'PENDING') as pendingActivities,
            (SELECT COUNT(*) FROM tasks) as totalTasks,
            (SELECT COUNT(*) FROM tasks WHERE status = 'COMPLETED') as completedTasks,
            (SELECT COUNT(*) FROM tasks WHERE status = 'TODO' AND due_date < CURRENT_TIMESTAMP) as overdueTasks
        """, nativeQuery = true)
    Map<String, Object> getDashboardSummary();

}
