package com.muf.modules.master.lead.entity.domain;

import com.muf.base.entity.BaseEntity;
import com.muf.modules.master.lead.entity.LeadSourceEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Getter
@Setter
@Table(name = "leads_source", uniqueConstraints = { @UniqueConstraint(columnNames = { "path", "is_deleted" }) })
public class LeadSource extends LeadSourceEntity {
}
