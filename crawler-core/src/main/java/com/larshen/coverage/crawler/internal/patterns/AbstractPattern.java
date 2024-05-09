package com.larshen.coverage.crawler.internal.patterns;

import com.larshen.coverage.crawler.internal.utils.FieldUtils;
import com.larshen.coverage.crawler.internal.utils.MethodUtils;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AbstractPattern {

    protected void hashcodeAndEqualsVerify(Class<?> clazz) {
        if (MethodUtils.hasOwnMethod(clazz, "hashCode") || MethodUtils.hasOwnMethod(clazz, "equals")) {

        }
    }

    protected void verifyToString(Object instance, List<Field> fields) {

        Class<?> clazz = instance.getClass();
        if (MethodUtils.hasOwnMethod(clazz, "toString")) {
            Method method = MethodUtils.getMethod(clazz, "toString");
            try {
                Object val = MethodUtils.invokeMethod(method, instance);
                if (val instanceof String) {
                    String text = (String) val;
                    for (Field field : fields) {
                        Assertions.assertTrue(text.contains(field.getName()));
                        Object o = field.get(instance);
                        Assertions.assertTrue(text.contains(String.valueOf(o)));
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
                //TODO Move invocation to MethodUtils
            }
        }
    }

    protected void verifyGetters(Object instance, List<Field> fields) {
        for (Field field : fields) {
            Method method = MethodUtils.getGetter(instance.getClass(), field);
            if (method != null) {
                Object o = MethodUtils.invokeMethod(method, instance);
                try {
                    Assertions.assertEquals(o, field.get(instance));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
