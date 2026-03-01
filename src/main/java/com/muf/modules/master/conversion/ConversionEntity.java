package com.muf.modules.master.conversion;

import com.muf.base.entity.BaseEntity;
import com.muf.modules.master.contact.Contact;
import com.muf.modules.master.customer.CustomerAccount;
import com.muf.modules.master.lead.entity.domain.Lead;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class ConversionEntity extends BaseEntity implements Serializable {

    @OneToOne
    @JoinColumn(name = "lead_id", referencedColumnName = "id")
    private Lead lead;

    @Column(name = "customer_account_id", nullable = false)
    private Integer customerAccountId;

    @ManyToOne
    @JoinColumn(name = "customer_account_id", insertable = false, updatable = false)
    private CustomerAccount customerAccount;

    @Column(name = "contact_id", nullable = false)
    private Integer contactId;

    @ManyToOne
    @JoinColumn(name = "contact_id", insertable = false, updatable = false)
    private Contact contact;

    @Column(name = "converted_at")
    private LocalDateTime convertedAt;
}

