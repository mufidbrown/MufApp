package com.muf.modules.language;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table(name = "langs", uniqueConstraints = { @UniqueConstraint(columnNames = { "code", "is_deleted" }) })
public class Lang extends LangEntity {


}