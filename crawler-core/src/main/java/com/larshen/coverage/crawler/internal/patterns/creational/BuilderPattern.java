package com.larshen.coverage.crawler.internal.patterns.creational;

import com.larshen.coverage.crawler.internal.common.CrawlableConfig;
import com.larshen.coverage.crawler.internal.patterns.AbstractPattern;
import com.larshen.coverage.crawler.internal.common.Crawlable;
import com.larshen.coverage.crawler.internal.utils.ClassUtils;
import com.larshen.coverage.crawler.internal.utils.ConstructorUtils;
import com.larshen.coverage.crawler.internal.utils.FieldUtils;
import com.larshen.coverage.crawler.internal.utils.MethodUtils;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class BuilderPattern extends AbstractPattern implements Crawlable {

    public BuilderPattern() {
    }

    @Override
    public void crawl(CrawlableConfig crawlableConfig) {

    }
}