package com.muf.modules.master.lead.repository;

import com.muf.modules.master.lead.entity.domain.LeadSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadSourceRepository extends JpaRepository<LeadSource, String>{
}
