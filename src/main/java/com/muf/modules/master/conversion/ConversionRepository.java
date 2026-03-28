package com.muf.modules.master.conversion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConversionRepository extends JpaRepository<Conversion, Integer> {

    @Query("SELECT c FROM Conversion c WHERE c.lead.id = :leadId")
    Optional<Conversion> findByLeadId(@Param("leadId") Integer leadId);

    @Query("SELECT COUNT(c) > 0 FROM Conversion c WHERE c.lead.id = :leadId")
    boolean existsByLeadId(@Param("leadId") Integer leadId);

    @Query("SELECT c FROM Conversion c LEFT JOIN FETCH c.lead LEFT JOIN FETCH c.customerAccount LEFT JOIN FETCH c.contact WHERE c.lead.id = :leadId")
    Optional<Conversion> findByLeadIdWithDetails(@Param("leadId") Integer leadId);


    //ini cadangan
/*    @Query("SELECT c FROM Conversion c LEFT JOIN FETCH c.lead LEFT JOIN FETCH c.customerAccount LEFT JOIN FETCH c.contact WHERE c.lead.id = :leadId")
    Optional<Conversion> findByLeadId(@Param("leadId") Integer leadId);

    @Query("SELECT COUNT(c) > 0 FROM Conversion c WHERE c.lead.id = :leadId")
    boolean existsByLeadId(@Param("leadId") Integer leadId);*/

//    @Query("SELECT c FROM Conversion c LEFT JOIN FETCH c.lead LEFT JOIN FETCH c.customerAccount LEFT JOIN FETCH c.contact WHERE c.leadId = :leadId")
//    Optional<Conversion> findByLeadIdWithDetails(@Param("leadId") Integer leadId);
//
//    // New method for finding conversion by customer account
//    @Query("SELECT c FROM Conversion c WHERE c.customerAccountId = :customerAccountId")
//    Optional<Conversion> findByAccountId(@Param("customerAccountId") Integer customerAccountId);
}
