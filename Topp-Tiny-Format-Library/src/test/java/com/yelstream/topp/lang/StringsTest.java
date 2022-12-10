package com.yelstream.topp.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.Map;

/**
 * Test suite for {@code com.yelstream.topp.lang.Strings}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-11-26
 */
class StringsTest {
    /**
     * Basic test of {@link Strings#namedFormat(String, Map)}.
     */
    @Test
    void namedFormat() {
        String formatted=Strings.namedFormat("%name$s",Map.of("name","someone"));
        Assertions.assertEquals("someone",formatted);
    }

    /**
     * Basic test of {@link Strings#namedFormat(Locale, String, Map)}.
     */
    @Test
    void namedFormatWithLocale() {
        Locale locale=Locale.ENGLISH;
        String formatted=Strings.namedFormat(locale,"%name$s",Map.of("name","someone"));
        Assertions.assertEquals("someone",formatted);
    }

    /**
     * Basic test of {@link Strings#namedReplace(String, Map)}.
     */
    @Test
    void namedReplace() {
        String formatted=Strings.namedReplace("${name}",Map.of("name","someone"));
        Assertions.assertEquals("someone",formatted);
    }
}
