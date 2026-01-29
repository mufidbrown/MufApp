package com.muf.modules.master.lead.repository;

import com.muf.modules.master.lead.entity.domain.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer> {

    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM Lead l WHERE l.phone = :phone")
    boolean existByPhone(@Param("phone") String phone);

    @Query("SELECT l FROM Lead l LEFT JOIN FETCH l.leadSource LEFT JOIN FETCH l.owner WHERE l.id = :id")
    Optional<Lead> findByIdWithDetails(@Param("id") Integer id);

}
