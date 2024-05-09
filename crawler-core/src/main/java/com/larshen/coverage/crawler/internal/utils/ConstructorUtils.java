package com.larshen.coverage.crawler.internal.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import java.util.List;

public class ConstructorUtils extends AbstractUtils {

    public static List<Constructor<?>> getAllConstructors(Class<?> clazz) {
        List<Constructor<?>> constructors = asList(clazz.getDeclaredConstructors());
        constructors.forEach(constructor -> constructor.setAccessible(true));
        return constructors;
    }

    public static <T> List<Object> invokeAllConstructors(Class<T> clazz) {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        Object[] instances = new Object[constructors.length];

        for (int i = 0; i < constructors.length; i++) {
            Constructor<?> constructor = constructors[i];
            Object[] params = ParameterUtils.getParameters(constructor.getParameterTypes());
            instances[i] = invokeConstructor(constructor, params);
        }

        return asList(instances);
    }

    public static <T> Object invokeConstructor(Class<T> clazz) {
        try {
            Constructor<?> constructor = ConstructorUtils.getAllConstructors(clazz).stream().findFirst().orElse(null);
            if (constructor != null) {
                return constructor.newInstance(ParameterUtils.getParameters(constructor.getParameterTypes()));
            }
            return null;
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> T invokeConstructor(Constructor<T> constructor, Object... args) {
        try {
            constructor.setAccessible(true);
            return constructor.newInstance(args);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e.getCause());
        }
    }
}
