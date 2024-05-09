package com.larshen.coverage.crawler.internal.common;

public class SpecificThrowable {
    private final Class<?> owner;
    private final String methodName;
    private final Class<?> throwable;

    public SpecificThrowable(Class<?> owner, String methodName, Class<?> throwable) {
        this.owner = owner;
        this.methodName = methodName;
        this.throwable = throwable;
    }
}
