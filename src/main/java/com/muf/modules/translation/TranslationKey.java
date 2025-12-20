package com.muf.modules.translation;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table(name = "translation_key", uniqueConstraints = { @UniqueConstraint(columnNames = { "key", "is_deleted", "module_id" }) })
public class TranslationKey extends TranslationKeyEntity {

}

