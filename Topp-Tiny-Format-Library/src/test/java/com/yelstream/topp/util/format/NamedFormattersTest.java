package com.yelstream.topp.util.format;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.Map;

/**
 * Test suite for {@code com.yelstream.topp.util.format.NamedFormatters}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-12-11
 */
class NamedFormattersTest {
    /**
     * Basic test of {@link NamedFormatters#format(String, Map)}.
     */
    @Test
    void namedFormat() {
        String formatted=NamedFormatters.format("%name$s",Map.of("name","someone"));
        Assertions.assertEquals("someone",formatted);
    }

    /**
     * Basic test of {@link NamedFormatters#format(Locale, String, Map)}.
     */
    @Test
    void namedFormatWithLocale() {
        Locale locale=Locale.ENGLISH;
        String formatted=NamedFormatters.format(locale,"%name$s",Map.of("name","someone"));
        Assertions.assertEquals("someone",formatted);
    }
}
