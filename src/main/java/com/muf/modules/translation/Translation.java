package com.muf.modules.translation;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table(name = "translation", uniqueConstraints = { @UniqueConstraint(columnNames = { "translation_key_id", "lang_id" }) })
public class Translation extends TranslationEntity {

}
