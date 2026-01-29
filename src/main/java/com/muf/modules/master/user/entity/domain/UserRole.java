package com.muf.modules.master.user.entity.domain;

import com.muf.modules.master.user.entity.UserRoleEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table(name = "user_roles", uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id", "role_id", "is_deleted" }) })
public class UserRole extends UserRoleEntity {


}

