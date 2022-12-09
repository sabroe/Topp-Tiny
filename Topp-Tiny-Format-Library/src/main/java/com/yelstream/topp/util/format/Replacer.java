package com.yelstream.topp.util.format;

import com.yelstream.topp.util.regex.Matchers;
import com.yelstream.topp.util.regex.Patterns;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.regex.Pattern;

/**
 * Replaces named variables within a string.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-12-07
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName="Builder",toBuilder=true)
public class Replacer {
    /**
     * Pattern for matching parameters.
     */
    @lombok.Builder.Default
    private final Pattern parameterPattern=Patterns.createVariableParameterPattern();

    @lombok.Builder.Default
    private final String defaultReplacement=null;

    @lombok.Builder.Default
    private final boolean literalReplacement=true;

    @Singular
    private List<Consumer<Match>> onMatchConsumers;

    @Singular
    private List<Consumer<Replace>> onReplaceConsumers;

    /**
     *
     *
     * @param format
     * @param parameters
     * @return
     */
    public String replace(String format, Map<String,Object> parameters) {
        return Matchers.runMatcherLoop(parameterPattern,format,matcher -> {
            String pattern=matcher.group(Patterns.PATTERN_GROUP_NAME);
            String key=matcher.group(Patterns.KEY_GROUP_NAME);
            Match.registerMatch(onMatchConsumers,pattern,key);
            String replacement=getReplacement(parameters,key,pattern,defaultReplacement,literalReplacement);
            Replace.registerReplace(onReplaceConsumers,pattern,key,replacement);
            return replacement;
        },true);
    }

    public static String getReplacement(Map<String,Object> parameters,
                                        String key,
                                        String pattern,
                                        String defaultReplacement,
                                        boolean literalReplacement) {
        String replacement;
//        boolean applyLiteralReplacement=false;
        if (!parameters.containsKey(key)) {
            if (defaultReplacement==null) {
                replacement=pattern;
//                applyLiteralReplacement=true;
            } else {
                replacement=defaultReplacement;
            }
        } else {
            replacement=parameters.get(key).toString();
//            applyLiteralReplacement=literalReplacement;
        }
//        replacement=applyLiteralReplacement?createLiteralReplacement(replacement):replacement;
        return replacement;
    }

/*
    public static String createLiteralReplacement(String replacement) {
        return replacement.replace("%","%%");
    }
*/
}
