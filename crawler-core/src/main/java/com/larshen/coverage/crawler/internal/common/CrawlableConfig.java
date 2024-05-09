package com.larshen.coverage.crawler.internal.common;

import com.larshen.coverage.crawler.internal.utils.ClassUtils;

import java.util.ArrayList;
import java.util.List;

public class CrawlableConfig {
    private final List<SpecificValue<?>> specificMethodValues = new ArrayList<>();
    private final List<SpecificValue<?>> specificFieldValues = new ArrayList<>();
    private final List<SpecificThrowable> throwables = new ArrayList<>();
    private List<Class<?>> classes = new ArrayList<>();
    private Class<?> clazz;

    public CrawlableConfig() {
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
        this.classes = ClassUtils.getAllClasses(clazz);
    }

    public List<Class<?>> getClasses() {
        return classes;
    }

    public List<SpecificValue<?>> getSpecificMethodValues() {
        return specificMethodValues;
    }

    public void addMethodValue(SpecificValue<?> value) {
        this.specificMethodValues.add(value);
    }

    public List<SpecificThrowable> getThrowables() {
        return throwables;
    }

    public void addThrowable(SpecificThrowable throwable) {
        this.throwables.add(throwable);
    }

    public List<SpecificValue<?>> getSpecificFieldValues() {
        return specificFieldValues;
    }

    public void addFieldValue(SpecificValue<?> value) {
        this.specificFieldValues.add(value);
    }
}
