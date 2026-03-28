package com.muf.modules.master.contact;

import com.muf.base.entity.BaseEntity;
import com.muf.modules.master.customeraccount.CustomerAccount;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public class ContactEntity extends BaseEntity implements Serializable {

    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;

    @Column(name = "phone", length = 30)
    private String phone;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "position", length = 100)
    private String position;

    @Column(name = "customer_account_id")
    private Integer customerAccountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_account_id", insertable = false, updatable = false)
    private CustomerAccount customerAccount;

}
