package com.muf.modules.master.lead.entity.domain;

import com.muf.modules.master.lead.entity.LeadEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Table(name = "leads")
public class Lead extends LeadEntity {
}
