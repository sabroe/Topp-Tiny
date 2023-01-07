package com.yelstream.topp.util.regex;

import lombok.experimental.UtilityClass;

import java.util.regex.Matcher;

/**
 * Utility addressing instances of {@link Matcher}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-12-09
 */
@UtilityClass
public class Matchers {
    /**
     * Create literal replacement for {@link Matcher#appendReplacement(StringBuilder,String)}.
     * @param replacement Replacement text to become literal.
     * @return Literal replacement text.
     */
    public static String createLiteralReplacement(String replacement) {
        return replacement.replace("$","\\$");
    }
}
