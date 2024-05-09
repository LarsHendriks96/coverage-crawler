package com.larshen.coverage.crawler;

import com.larshen.coverage.crawler.internal.common.CrawlableConfig;
import com.larshen.coverage.crawler.internal.common.SpecificThrowable;
import com.larshen.coverage.crawler.internal.common.SpecificValue;
import com.larshen.coverage.crawler.internal.common.Crawlable;
import com.larshen.coverage.crawler.internal.exceptions.CrawlerConfigurationException;
import com.larshen.coverage.crawler.internal.patterns.creational.BuilderPattern;
import com.larshen.coverage.crawler.internal.dictionary.Pattern;
import com.larshen.coverage.crawler.internal.patterns.creational.ClassPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class CoverageCrawler {
    private static final ClassPattern classPattern = new ClassPattern();
    private static final Map<Pattern, Crawlable> patternMap = Map.of(
            Pattern.BUILDER, new BuilderPattern()
    );
    private final List<Pattern> patterns = new ArrayList<>(Pattern.length());
    private CrawlableConfig crawlableConfig = new CrawlableConfig();

    public CoverageCrawler() {
    }

    public CoverageCrawler forClass(Class<?> clazz) {
        crawlableConfig.setClazz(clazz);
        return this;
    }

    public CoverageCrawler hasPattern(Pattern pattern) {
        patterns.add(pattern);
        return this;
    }

    public CoverageCrawler withSpecificThrowable(String fieldName, Class<?> throwable) {
        return withSpecificThrowable(crawlableConfig.getClazz(), fieldName, throwable);
    }

    public CoverageCrawler withSpecificThrowable(Class<?> owner, String methodName, Class<?> throwable) {
        if (!Throwable.class.isAssignableFrom(throwable)) {
            throw new CrawlerConfigurationException("Found exception while setting an SpecificThrowable, " +
                    "Class {} is not an Throwable", throwable);
        }
        crawlableConfig.addThrowable(new SpecificThrowable(owner, methodName, throwable));
        return this;
    }

    public <V> CoverageCrawler withSpecificMethodParameter(String methodName, V value) {
        return withSpecificMethodParameter(crawlableConfig.getClazz(), methodName, value);
    }

    public <V> CoverageCrawler withSpecificMethodParameter(Class<?> owner, String methodName, V value) {
        crawlableConfig.addMethodValue(new SpecificValue<>(owner, methodName, value));
        return this;
    }

    public <V> CoverageCrawler withSpecificFieldParameter(String fieldName, V value) {
        return withSpecificFieldParameter(crawlableConfig.getClazz(), fieldName, value);
    }

    public <V> CoverageCrawler withSpecificFieldParameter(Class<?> owner, String fieldName, V value) {
        crawlableConfig.addFieldValue(new SpecificValue<>(owner, fieldName, value));
        return this;
    }

    public void crawl() {
        classPattern.crawl(crawlableConfig);
        for (Pattern pattern : patterns) {
            Crawlable crawlable = patternMap.get(pattern);
            crawlable.crawl(crawlableConfig);
        }
    }

    public void reset() {
        crawlableConfig = new CrawlableConfig();
        patterns.clear();
    }
}
