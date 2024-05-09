package com.larshen.coverage.crawler.internal.common;

import java.lang.reflect.InvocationTargetException;

public interface Crawlable {

    void crawl(CrawlableConfig crawlableConfig);
}
