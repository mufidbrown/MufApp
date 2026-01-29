package com.muf.modules.master.lead.entity;

import com.muf.base.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@MappedSuperclass
public class LeadEntity extends BaseEntity implements Serializable {
}
