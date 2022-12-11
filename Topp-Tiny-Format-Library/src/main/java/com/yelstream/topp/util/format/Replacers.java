package com.yelstream.topp.util.format;

import lombok.experimental.UtilityClass;

import java.util.Map;

/**
 * Utilities addressing instances of {@link Replacer}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-12-11
 */
@UtilityClass
public class Replacers {
    /**
     * Replaces parameters within a template text.
     * This is a usage of a default {@link Replacer}.
     * @param template Template containing parameters in the form {@code ${...}}.
     * @param parameters Parameters in the form of (name,value) pairs.
     * @return Formatted text.
     */
    public static String replace(String template,
                                 Map<String,Object> parameters) {
        Replacer replacer=Replacer.builder().build();
        return replacer.replace(template,parameters);
    }
}
