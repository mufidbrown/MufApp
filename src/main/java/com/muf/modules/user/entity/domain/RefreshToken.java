package com.muf.modules.user.entity.domain;

import com.muf.modules.user.entity.RefreshTokenEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table(name = "tokens", uniqueConstraints = { @UniqueConstraint(columnNames = { "path", "is_deleted" }) })
public class RefreshToken extends RefreshTokenEntity {
}


