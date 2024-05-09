package com.larshen.coverage.crawler.internal.utils;

import java.lang.reflect.Field;
import java.util.List;

public class FieldUtils extends AbstractUtils {

    public static <T> List<Field> getAllFields(Class<T> clazz){
        List<Field> fields = asList(clazz.getDeclaredFields());
        fields.forEach(field -> field.setAccessible(true));
        return fields;
    }
}
