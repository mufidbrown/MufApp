package com.muf.modules.module.entity.domain;

import com.muf.modules.module.entity.ModuleGroupEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table(name="module_group", uniqueConstraints = { @UniqueConstraint(columnNames = { "code", "is_deleted" }) })
public class ModuleGroup extends ModuleGroupEntity {

}
