package com.muf.modules.master.customer;

import com.muf.base.entity.BaseEntity;
import com.muf.modules.master.user.entity.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public class CustomerAccountEntity extends BaseEntity implements Serializable {

    @Column(name = "company_name", length = 150, nullable = false)
    private String companyName;

    @Column(name = "industry", length = 100)
    private String industry;

    @Column(name = "size", length = 50)
    private String size;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @Column(name = "owner_id")
    private Integer ownerId;

    @ManyToOne
    @JoinColumn(name = "owner_id", insertable = false, updatable = false)
    private User owner;

}
