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
    public static String runMatcherLoop(Pattern pattern,
                                        String format,
                                        Function<Matcher,String> matcherToReplacement,
                                        boolean literalReplacement) {
        Matcher matcher=pattern.matcher(format);
        return runMatcherLoop(matcher,matcherToReplacement,literalReplacement);
    }

    /**
     *
     *
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
     *
     * @param matcher
     * @param matcherToReplacement
     * @return
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

    private static String createLiteralReplacement(String replacement) {
        return replacement.replace("$","\\$");
    }
}
