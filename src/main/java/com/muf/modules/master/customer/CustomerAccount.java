package com.muf.modules.master.customer;

import com.muf.modules.workflow.activity.ActivityEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "customers")
public class CustomerAccount extends CustomerAccountEntity{
}

