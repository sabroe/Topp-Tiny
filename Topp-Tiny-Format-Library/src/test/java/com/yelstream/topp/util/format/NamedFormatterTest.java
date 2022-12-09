package com.yelstream.topp.util.format;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Test suite for {@code com.yelstream.topp.util.format.NamedFormatterTest}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-12-08
 */
class NamedFormatterTest {
    @Test
    void format1() {
        List<Match> matched=new ArrayList<>();
        List<Replace> replaced=new ArrayList<>();

        NamedFormatter.Builder builder=NamedFormatter.builder();
        builder.onMatchConsumer(matched::add);
        builder.onReplaceConsumer(replaced::add);
        NamedFormatter formatter=builder.build();

        String format = "%time$s-yyy-%space$s-zzz-%science$s-aaa-%%nomatch$s-bbb";

        String formatted = formatter.format(format, Map.of("time", 100, "space", 200));
        Assertions.assertEquals("100-yyy-200-zzz-%science$s-aaa-%nomatch$s-bbb",formatted);
        Assertions.assertEquals(List.of("%time$","%space$","%science$"),matched.stream().map(Match::getPattern).toList());
        Assertions.assertEquals(List.of("time","space","science"),matched.stream().map(Match::getKey).toList());
    }

    @Test
    void format2() {
        List<Match> matched=new ArrayList<>();
        List<Replace> replaced=new ArrayList<>();

        NamedFormatter.Builder builder=NamedFormatter.builder();
        builder.onMatchConsumer(matched::add);
        builder.onReplaceConsumer(replaced::add);
        NamedFormatter formatter=builder.build();

        String format = "%time$s-%space$s-%time$s-%space$s";

        String formatted = formatter.format(format, Map.of("time", 100, "space", 200));
        Assertions.assertEquals("100-200-100-200",formatted);
        Assertions.assertEquals(List.of("%time$","%space$","%time$","%space$"),matched.stream().map(Match::getPattern).toList());
        Assertions.assertEquals(List.of("time","space","time","space"),matched.stream().map(Match::getKey).toList());
    }
}
