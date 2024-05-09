package com.larshen.coverage.crawler.internal.exceptions;

public class CrawlerConfigurationException extends RuntimeException {

    public CrawlerConfigurationException(String message, Object... args) {
        super(String.format(message, args));
    }
}
