package com.larshen.coverage.crawler;


import com.larshen.coverage.crawler.internal.dictionary.CoverageDictionary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class CoverageDictionaryTest {
    static class doesNotExist{}

    @Test
    public void testCoverageDictionary(){
        CoverageDictionary coverageDictionary = new CoverageDictionary();
        Assertions.assertEquals(coverageDictionary.get(Integer.class), 11);
        Assertions.assertEquals(coverageDictionary.get(Byte.class), (byte) 1);
        Assertions.assertEquals(coverageDictionary.get(Float.class), 13.2F);
        Assertions.assertEquals(coverageDictionary.get(Long.class), 13L);
        Assertions.assertEquals(coverageDictionary.get(Double.class), 12.2D);
        Assertions.assertEquals(coverageDictionary.get(String.class), "test-string");

        Assertions.assertNotNull(coverageDictionary.get(UUID.class));
        Assertions.assertNull(coverageDictionary.get(doesNotExist.class));
    }
}
