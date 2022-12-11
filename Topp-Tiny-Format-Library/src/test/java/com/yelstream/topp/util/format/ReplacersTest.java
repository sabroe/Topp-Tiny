package com.yelstream.topp.util.format;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * Test suite for {@code com.yelstream.topp.util.format.Replacers}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-12-11
 */
class ReplacersTest {
    /**
     * Basic test of {@link Replacers#replace(String, Map)}.
     */
    @Test
    void namedReplace() {
        String formatted=Replacers.replace("${name}",Map.of("name","someone"));
        Assertions.assertEquals("someone",formatted);
    }
}
