package com.muf.modules.translation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.muf.base.entity.BaseEntity;
import com.muf.modules.language.Lang;
import jakarta.persistence.*;

import java.io.Serializable;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class TranslationEntity extends BaseEntity implements Serializable {

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "translation_key_id", referencedColumnName = "id", nullable = false)
    private TranslationKey translationKey;

    @OneToOne
    @JoinColumn(name = "lang_id", referencedColumnName = "id", nullable = false)
    private Lang lang;

    @Column(name = "value", length = 500, nullable = false)
    private String value;

    public TranslationKey getTranslationKey() {
        return translationKey;
    }

    public void setTranslationKey(TranslationKey translationKey) {
        this.translationKey = translationKey;
    }

    public Lang getLang() {
        return lang;
    }

    public void setLang(Lang lang) {
        this.lang = lang;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
