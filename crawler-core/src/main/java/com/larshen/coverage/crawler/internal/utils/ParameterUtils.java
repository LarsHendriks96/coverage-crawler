package com.larshen.coverage.crawler.internal.utils;


import com.larshen.coverage.crawler.internal.common.SpecificValue;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class ParameterUtils extends AbstractUtils {

    public static Object[] getParameters(Class<?>[] parameterTypes) {
        Object[] parameters = new Object[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            Object param = dictionary.get(parameterTypes[i]);

            if (param == null) {
                param = ConstructorUtils.invokeConstructor(parameterTypes[i]);
            }

            parameters[i] = param;
        }
        return parameters;
    }

    public static Object[] getMethodParameters(Method method, List<SpecificValue<?>> values) {
        List<Parameter> parameters = asList(method.getParameters());
        Object[] params = new Object[parameters.size()];

        for (int i = 0; i < parameters.size(); i++) {
            Parameter parameter = parameters.get(i);
            Object param = values.stream().filter(value -> value.match(parameter)).findFirst().orElse(null);

            if (param == null) {
                param = dictionary.get(parameter.getType());
            }
            if (param == null) {
                //TODO: support multiple argument constructors
                param = ConstructorUtils.invokeConstructor(parameter.getType());
            }

            params[i] = param;
        }

        return params;
    }

}
