package com.muf.modules.master.lead.entity.domain;

import com.muf.modules.master.lead.entity.LeadEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Getter
@Setter
@Table(name = "leads", uniqueConstraints = { @UniqueConstraint(columnNames = { "path", "is_deleted" }) })
public class Lead extends LeadEntity {
}
