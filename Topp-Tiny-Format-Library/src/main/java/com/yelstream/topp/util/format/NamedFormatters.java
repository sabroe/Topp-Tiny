package com.yelstream.topp.util.format;

import lombok.experimental.UtilityClass;

import java.util.Locale;
import java.util.Map;

/**
 * Utilities addressing instances of {@link NamedFormatter}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-12-11
 */
@UtilityClass
public class NamedFormatters {
    /**
     * Formats a text with named arguments.
     * This is a usage of a default {@link NamedFormatter}.
     * @param format Format matching {@link java.util.Formatter}, but where all arguments are named instead of indexed.
     * @param arguments Arguments in the form of (name,value) pairs.
     * @return Formatted text.
     */
    public static String format(String format,
                                Map<String,Object> arguments) {
        NamedFormatter namedFormatter=NamedFormatter.builder().build();
        return namedFormatter.format(format,arguments);
    }

    /**
     * Formats a text with named arguments.
     * This is a usage of a default {@link NamedFormatter}.
     * @param locale Locale.
     * @param format Format matching {@link java.util.Formatter}, but where all arguments are named instead of indexed.
     * @param arguments Arguments in the form of (name,value) pairs.
     * @return Formatted text.
     */
    public static String format(Locale locale,
                                String format,
                                Map<String,Object> arguments) {
        NamedFormatter namedFormatter=NamedFormatter.builder().locale(locale).build();
        return namedFormatter.format(format,arguments);
    }
}
