package com.larshen.coverage.crawler.internal.utils;

import com.larshen.coverage.crawler.internal.dictionary.CoverageDictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractUtils {
    protected static final CoverageDictionary dictionary = new CoverageDictionary();

    @SafeVarargs
    protected static <T> List<T> asList(T... t){
        return new ArrayList<>(Arrays.asList(t));
    }
}
