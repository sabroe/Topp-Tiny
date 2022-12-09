package com.yelstream.topp.util.regex;

import lombok.experimental.UtilityClass;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * Runs the main matcher loop.
     * @param pattern Pattern to match.
     * @param format Format to match pattern within.
     * @param matcherToReplacement Mapping of a match to a replacement text.
     * @param literalReplacement Indicates, if the replacement text is to be taken literal.
     * @return Resulting formatting.
     */
    public static String runMatcherLoop(Pattern pattern,
                                        String format,
                                        Function<Matcher,String> matcherToReplacement,
                                        boolean literalReplacement) {
        Matcher matcher=pattern.matcher(format);
        return runMatcherLoop(matcher,matcherToReplacement,literalReplacement);
    }

    /**
     * Runs the main matcher loop.
     * <p>
     * Note that this runs the standard matching loop:
     * </p>
     * <pre>
     *   Matcher matcher=Pattern.compile(...).matcher(...);
     *   while (matcher.find()) {
     *     ...
     *     matcher.appendReplacement(...);
     *   }
     *   matcher.appendTail(...);
     * </pre>
     * @param matcher Matcher of pattern within format text.
     * @param matcherToReplacement Mapping of a match to a replacement text.
     * @param literalReplacement Indicates, if the replacement text is to be taken literal.
     * @return Resulting formatting.
     */
    public static String runMatcherLoop(Matcher matcher,
                                        Function<Matcher,String> matcherToReplacement,
                                        boolean literalReplacement) {
        StringBuilder formatted=new StringBuilder();
        while (matcher.find()) {
            String replacement=matcherToReplacement.apply(matcher);
            if (literalReplacement) {
                replacement=createLiteralReplacement(replacement);
            }
            matcher.appendReplacement(formatted,replacement);
        }
        matcher.appendTail(formatted);
        return formatted.toString();
    }

    /**
     * Create literal replacement for {@link Matcher#appendReplacement(StringBuilder, String)}.
     * @param replacement Replacement text to become literal.
     * @return Literal replacement text.
     */
    private static String createLiteralReplacement(String replacement) {
        return replacement.replace("$","\\$");
    }
}
