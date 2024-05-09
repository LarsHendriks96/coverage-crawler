package com.larshen.coverage.crawler.internal.patterns.creational;

import com.larshen.coverage.crawler.internal.common.CrawlableConfig;
import com.larshen.coverage.crawler.internal.common.Crawlable;
import com.larshen.coverage.crawler.internal.patterns.AbstractPattern;
import com.larshen.coverage.crawler.internal.utils.ConstructorUtils;
import com.larshen.coverage.crawler.internal.utils.FieldUtils;
import com.larshen.coverage.crawler.internal.utils.ParameterUtils;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static com.larshen.coverage.crawler.internal.utils.MethodUtils.getAllMethodsExceptGetters;

public class ClassPattern extends AbstractPattern implements Crawlable {

    @Override
    public void crawl(CrawlableConfig crawlableConfig) {

        for (Class<?> cls : crawlableConfig.getClasses()) {
            List<Object> instances = ConstructorUtils.invokeAllConstructors(cls);

            for (Object instance : instances) {
                Assertions.assertNotNull(instance);
                Assertions.assertDoesNotThrow(() -> cls.cast(instance));

                List<Field> fields = FieldUtils.getAllFields(cls);
                verifyGetters(instance, fields);
                verifyToString(instance, fields);

                List<Method> methodsExceptGetters = getAllMethodsExceptGetters(cls, fields);
                for (Method method : methodsExceptGetters) {
                    try {
                        Object o = method.invoke(instance, ParameterUtils.getMethodParameters(method, crawlableConfig.getSpecificMethodValues()));
                        //Assertions.assertNotNull(method.getReturnType().cast(o));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
