package com.yelstream.topp.lang;

import com.yelstream.topp.util.format.NamedFormatter;
import com.yelstream.topp.util.format.Replacer;
import lombok.experimental.UtilityClass;

import java.util.Locale;
import java.util.Map;

/**
 * Utility addressing instances of {@link String}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-12-09
 */
@UtilityClass
public class Strings {
    /**
     * Formats a text with named arguments.
     * This is a usage of a default {@link NamedFormatter}.
     * @param format Format matching {@link java.util.Formatter}, but where all arguments are named instead of indexed.
     * @param arguments Arguments in the form of (name,value) pairs.
     * @return Formatted text.
     */
    public static String namedFormat(String format,
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
    public static String namedFormat(Locale locale,
                                     String format,
                                     Map<String,Object> arguments) {
        NamedFormatter namedFormatter=NamedFormatter.builder().locale(locale).build();
        return namedFormatter.format(format,arguments);
    }

    /**
     * Replaces parameters within a template text.
     * This is a usage of a default {@link Replacer}.
     * @param template Template containing parameters in the form {@code ${...}}.
     * @param parameters Parameters in the form of (name,value) pairs.
     * @return Formatted text.
     */
    public static String namedReplace(String template,
                                      Map<String,Object> parameters) {
        Replacer replacer=Replacer.builder().build();
        return replacer.replace(template,parameters);
    }
}
