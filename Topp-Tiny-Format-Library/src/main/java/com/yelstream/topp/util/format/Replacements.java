package com.yelstream.topp.util.format;

import lombok.experimental.UtilityClass;

import java.util.Map;

/**
 * Utility addressing formatting and replacement within strings.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-12-09
 */
@UtilityClass
public class Replacements {
    public static String getReplacement(Map<String,Object> parameters,
                                        String key,
                                        String pattern,
                                        String defaultReplacement,
                                        boolean literalReplacement) {
        String replacement;
        boolean applyLiteralReplacement=false;
        if (!parameters.containsKey(key)) {
            if (defaultReplacement==null) {
                replacement=pattern;
                applyLiteralReplacement=true;
            } else {
                replacement=defaultReplacement;
            }
        } else {
            replacement=parameters.get(key).toString();
            applyLiteralReplacement=literalReplacement;
        }
        replacement=applyLiteralReplacement?createLiteralReplacement(replacement):replacement;
        return replacement;
    }

    public static String createLiteralReplacement(String replacement) {
        return replacement.replace("$","\\$");
    }
}
