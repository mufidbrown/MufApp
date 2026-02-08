package com.muf.modules.master.lead.entity;

import com.muf.base.entity.BaseEntity;
import com.muf.modules.master.lead.entity.domain.LeadSource;
import com.muf.modules.master.user.entity.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class LeadEntity extends BaseEntity implements Serializable {

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "phone", length = 30, nullable = false, unique = true)
    private String phone;

    @Column(name = "email", length = 100)
    private String email;

//    @Column(name = "source_code", length = 50)
//    private String sourceCode;

    @ManyToOne
//    @JoinColumn(name = "source_code", referencedColumnName = "code", insertable = false, updatable = false)
    private LeadSource leadSource;

    @Column(name = "status", length = 30, nullable = false)
    private String status;

    @Column(name = "owner_id")
    private Integer ownerId;

    @ManyToOne
    @JoinColumn(name = "owner_id", insertable = false, updatable = false)
    private User owner;

}
