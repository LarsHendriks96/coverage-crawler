package com.larshen.coverage.crawler.internal.common;

import java.lang.reflect.Parameter;

public class SpecificValue<T> {
    private final Class<?> owner;
    private final Class<?> clazz;
    private final T value;
    private final String name;

    public SpecificValue(Class<?> owner, String name, T value) {
        this.owner = owner;
        this.name = name;
        this.value = value;
        this.clazz = value.getClass();
    }

    public Class<?> getOwner() {
        return owner;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public T getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public boolean match(Parameter parameter) {
        return parameter.getName().equals(this.name) && parameter.getType().equals(this.clazz);
    }
}
