package com.muf.modules.user.entity.domain;

import com.muf.base.entity.BaseEntity;
import com.muf.modules.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
@Entity
@Getter
@Setter
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "path", "is_deleted" }) })
public class User extends UserEntity {
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @Where(clause = "is_deleted = " + BaseEntity.ENTITY_FLAG_NOT_DELETED)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<UserRole> userRoleList = new ArrayList<>();

    public void addRole(UserRole userRole) {
        userRoleList.add(userRole);
        userRole.setUser(this);
    }
}

