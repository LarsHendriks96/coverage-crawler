package com.larshen.coverage.crawler.internal.utils;

import java.util.List;

public class ClassUtils extends AbstractUtils {

    public static List<Class<?>> getAllClasses(Class<?> clazz) {
        List<Class<?>> classes = asList(clazz.getDeclaredClasses());
        classes.add(clazz);
        return classes;
    }
}
