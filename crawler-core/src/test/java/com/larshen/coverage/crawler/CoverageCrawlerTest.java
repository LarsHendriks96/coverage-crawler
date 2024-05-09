package com.larshen.coverage.crawler;

import com.larshen.coverage.crawler.internal.exceptions.CrawlerConfigurationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CoverageCrawlerTest {
    @Test
    public void testWithSpecificException() {
        Assertions.assertThrows(CrawlerConfigurationException.class, () -> new CoverageCrawler().withSpecificThrowable("test", Double.class));
        Assertions.assertThrows(CrawlerConfigurationException.class, () -> new CoverageCrawler().withSpecificThrowable("test", Double.class));
        Assertions.assertDoesNotThrow(() -> new CoverageCrawler().withSpecificThrowable("test", Exception.class));
    }
}
