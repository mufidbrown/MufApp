package com.muf.modules.module.entity.domain;

import com.muf.modules.module.entity.ModuleTypeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "module_type", uniqueConstraints = { @UniqueConstraint(columnNames = { "code", "is_deleted" }) })
public class ModuleType extends ModuleTypeEntity {

}
