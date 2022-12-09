package com.yelstream.topp.util.format;

import com.yelstream.topp.util.regex.Matchers;
import com.yelstream.topp.util.regex.Patterns;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
public class NamedFormatter {

    @lombok.Builder.Default
    private final Locale locale=null;

    /**
     * Pattern for matching parameters.
     */
    @lombok.Builder.Default
    private final Pattern parameterPattern=Patterns.createFormatterParameterPattern();

    @lombok.Builder.Default
    private final Object defaultReplacement=null;

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
    public String format(String format, Map<String,Object> parameters) {
        List<Object> used=new ArrayList<>();

        String preformatted=
            Matchers.runMatcherLoop(parameterPattern,format,matcher -> {
                String pattern=matcher.group(Patterns.PATTERN_GROUP_NAME);
                String key=matcher.group(Patterns.KEY_GROUP_NAME);
                Match.registerMatch(onMatchConsumers,pattern,key);
                Object replacement=getReplacement(parameters,key,pattern,defaultReplacement,literalReplacement);
                if (replacement==null) {
                    return createLiteralReplacement(pattern);
                } else {
                    used.add(replacement);
                    String r = "%" + used.size() + "$";
                    Replace.registerReplace(onReplaceConsumers, pattern, key, r);
                    return r;
                }
            },true);
        return String.format(locale,preformatted,used.toArray());
    }

    public static Object getReplacement(Map<String,Object> parameters,  //TODO: Nameing -> getFormatArguments()?
                                        String key,
                                        String pattern,
                                        Object defaultReplacement,
                                        boolean literalReplacement) {
        Object replacement;
//        boolean applyLiteralReplacement=false;
        if (!parameters.containsKey(key)) {
            if (defaultReplacement==null) {
//                replacement=pattern;
                replacement=null;
//                applyLiteralReplacement=true;
            } else {
                replacement=defaultReplacement;
            }
        } else {
            replacement=parameters.get(key);
//            applyLiteralReplacement=literalReplacement;
        }
//        replacement=applyLiteralReplacement?createLiteralReplacement(replacement):replacement;
        return replacement;
    }

    public static String createLiteralReplacement(String replacement) {
        return replacement.replace("%","%%");
    }
}
