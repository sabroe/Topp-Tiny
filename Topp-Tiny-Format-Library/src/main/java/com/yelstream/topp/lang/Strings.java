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
    public static String namedFormat(String format,
                                     Map<String,Object> parameters) {
        NamedFormatter namedFormatter=NamedFormatter.builder().build();
        return namedFormatter.format(format,parameters);
    }

    public static String namedFormat(Locale locale,
                                     String format,
                                     Map<String,Object> parameters) {
        NamedFormatter namedFormatter=NamedFormatter.builder().locale(locale).build();
        return namedFormatter.format(format,parameters);
    }

    public static String namedReplace(String template,
                                      Map<String,Object> parameters) {
        Replacer replacer=Replacer.builder().build();
        return replacer.replace(template,parameters);
    }
}
