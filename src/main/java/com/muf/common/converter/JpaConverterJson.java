package com.muf.common.converter;

import com.google.gson.Gson;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Converter
public class JpaConverterJson implements AttributeConverter<Object, String> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final Gson gson = new Gson();

    @Override
    public String convertToDatabaseColumn(Object attribute) {
        return attribute != null ? gson.toJson(attribute) : null;
    }

    @Override
    public Object convertToEntityAttribute(String dbData) {
        return dbData != null ? gson.fromJson(dbData, Object.class) : null;
    }
}
