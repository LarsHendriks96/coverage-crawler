package com.larshen.coverage.crawler.internal.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class MethodUtils extends AbstractUtils {

    public static Object invokeMethod(Method method, Object instance) {
        try {
            if (method != null) {
                return method.invoke(instance);
            } else {
                return null;
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean hasOwnMethod(Class<?> clazz, String name) {
        try {
            Method method = clazz.getMethod(name);
            return method.getDeclaringClass().equals(clazz);
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    public static Method getMethod(Class<?> clazz, String name) {
        try {
            return clazz.getMethod(name);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static Method getGetter(Class<?> clazz, Field field) {
        return asList(clazz.getMethods()).stream()
                .filter(method ->
                        method.getName().startsWith("get") ||
                                method.getName().startsWith("is"))
                .filter(method -> methodMatchesFieldTypeAndName(method, field))
                .findFirst().orElse(null);
    }

    public static List<Method> getAllMethodsExceptGetters(Class<?> clazz, List<Field> fields) {
        return asList(clazz.getMethods()).stream()
                .filter(method -> method.getDeclaringClass().equals(clazz))
                .filter(method -> !method.getName().startsWith("get") ||
                        !method.getName().startsWith("is"))
                .filter(method -> !methodMatchesFieldTypeAndName(method, fields))
                .collect(Collectors.toList());
    }

    private static Boolean methodMatchesFieldTypeAndName(Method method, List<Field> fields) {
        return fields.stream().anyMatch((Field field) -> method.getReturnType().equals(field.getType()) &&
                method.getName().toLowerCase(Locale.ROOT).contains(field.getName()));
    }

    private static Boolean methodMatchesFieldTypeAndName(Method method, Field field) {
        return method.getReturnType().equals(field.getType()) &&
                method.getName().toLowerCase(Locale.ROOT).contains(field.getName());
    }
}