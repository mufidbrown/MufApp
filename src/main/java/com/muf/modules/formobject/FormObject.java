package com.muf.modules.formobject;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table(name = "t1_form_object", uniqueConstraints = { @UniqueConstraint(columnNames = { "code", "is_deleted" }) })
public class FormObject extends FormObjectEntity{
}
