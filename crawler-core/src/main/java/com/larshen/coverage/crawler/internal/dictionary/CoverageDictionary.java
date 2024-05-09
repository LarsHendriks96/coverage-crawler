package com.larshen.coverage.crawler.internal.dictionary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.Callable;

public final class CoverageDictionary {
    private static final Logger LOGGER = LoggerFactory.getLogger(CoverageDictionary.class);
    private final HashMap<Class<?>, Object> objectsMap = new HashMap<>();
    private final HashMap<Class<?>, Callable<?>> runnablesMap = new HashMap<>();

    public CoverageDictionary() {
        prepopulateObjectsMap();
        prepopulateRunnablesMap();
    }

    public <T> T get(Class<T> clazz) {
        Object found = objectsMap.get(clazz);

        if (found != null) {
            return clazz.cast(found);
        }

        Callable<?> foundInRunnables = runnablesMap.get(clazz);

        if (foundInRunnables != null) {
            try {
                return clazz.cast(foundInRunnables.call());
            } catch (Exception e) {
                LOGGER.debug("Exception found when trying to run callable of type {}", foundInRunnables.getClass(), e);
            }
        }
        return null;
    }

    private void prepopulateObjectsMap() {
        objectsMap.put(Integer.class, 11);
        objectsMap.put(Byte.class, (byte) 1);
        objectsMap.put(Float.class, 13.2F);
        objectsMap.put(Long.class, 13L);
        objectsMap.put(Double.class, 12.2D);
        objectsMap.put(String.class, "test-string");
        objectsMap.put(Boolean.class, Boolean.TRUE);
    }


    private void prepopulateRunnablesMap() {
        runnablesMap.put(UUID.class, UUID::randomUUID);
    }
}
