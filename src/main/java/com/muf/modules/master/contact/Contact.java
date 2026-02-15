package com.muf.modules.master.contact;

import com.muf.modules.master.customer.CustomerAccountEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "contacts")
public class Contact extends ContactEntity {

}
