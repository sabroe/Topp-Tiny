package com.yelstream.topp.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test suite for {@code com.yelstream.topp.lang.StringBuilders}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-11-26
 */
class StringBuildersTest {
    @Test
    void replace() {
        {
            StringBuilder buffer = new StringBuilder("aaa-bbb-ccc-bbb");
            int index= StringBuilders.replace(buffer, "bbb", "xxx");
            Assertions.assertEquals(4, index);
            Assertions.assertEquals("aaa-xxx-ccc-bbb", buffer.toString());
        }
        {
            StringBuilder buffer = new StringBuilder("aaa-bbb-ccc-bbb");
            int index=StringBuilders.replace(buffer, "ddd", "yyy");
            Assertions.assertEquals(-1, index);
            Assertions.assertEquals("aaa-bbb-ccc-bbb", buffer.toString());
        }
    }

    @Test
    void replaceFrom() {
        {
            StringBuilder buffer = new StringBuilder("aaa-aaa");
            int index=StringBuilders.replaceFrom(buffer, 0, "aaa", "xxx");
            Assertions.assertEquals(0, index);
            Assertions.assertEquals("xxx-aaa", buffer.toString());
        }
        {
            StringBuilder buffer = new StringBuilder("aaa-aaa");
            int index=StringBuilders.replaceFrom(buffer, 0+4, "aaa", "xxx");
            Assertions.assertEquals(4, index);
            Assertions.assertEquals("aaa-xxx", buffer.toString());
        }
        {
            StringBuilder buffer = new StringBuilder("aaa-aaa");
            int index=StringBuilders.replaceFrom(buffer, 0+4+4, "aaa", "xxx");
            Assertions.assertEquals(-1, index);
            Assertions.assertEquals("aaa-aaa", buffer.toString());
        }
        {
            StringBuilder buffer = new StringBuilder("aaa-aaa");
            int index=StringBuilders.replaceFrom(buffer, 0, "ddd", "yyy");
            Assertions.assertEquals(-1, index);
            Assertions.assertEquals("aaa-aaa", buffer.toString());
        }
    }

    @Test
    void replaceLast() {
        {
            StringBuilder buffer = new StringBuilder("aaa-bbb-ccc-bbb");
            int index=StringBuilders.replaceLast(buffer, "bbb", "xxx");
            Assertions.assertEquals(12, index);
            Assertions.assertEquals("aaa-bbb-ccc-xxx", buffer.toString());
        }
        {
            StringBuilder buffer = new StringBuilder("aaa-bbb-ccc-bbb");
            int index=StringBuilders.replaceLast(buffer, "ddd", "yyy");
            Assertions.assertEquals(-1, index);
            Assertions.assertEquals("aaa-bbb-ccc-bbb", buffer.toString());
        }
    }

    @Test
    void replaceLastFrom() {
        {
            StringBuilder buffer = new StringBuilder("aaa-aaa");
            int index=StringBuilders.replaceLastFrom(buffer, 0, "aaa", "xxx");
            Assertions.assertEquals(0, index);
            Assertions.assertEquals("xxx-aaa", buffer.toString());
        }
        {
            StringBuilder buffer = new StringBuilder("aaa-aaa");
            int index=StringBuilders.replaceLastFrom(buffer, 0+4, "aaa", "xxx");
            Assertions.assertEquals(4, index);
            Assertions.assertEquals("aaa-xxx", buffer.toString());
        }
        {
            StringBuilder buffer = new StringBuilder("aaa-aaa");
            int index=StringBuilders.replaceLastFrom(buffer, 0+4+4, "aaa", "xxx");
            Assertions.assertEquals(4, index);
            Assertions.assertEquals("aaa-xxx", buffer.toString());
        }
        {
            StringBuilder buffer = new StringBuilder("aaa-aaa");
            int index=StringBuilders.replaceLastFrom(buffer, 0, "ddd", "yyy");
            Assertions.assertEquals(-1, index);
            Assertions.assertEquals("aaa-aaa", buffer.toString());
        }
    }

    @Test
    void replaceAll() {
        {
            StringBuilder buffer = new StringBuilder("aaa-bbb-ccc-bbb");
            int count=StringBuilders.replaceAll(buffer, "bbb", "xxx");
            Assertions.assertEquals(2, count);
            Assertions.assertEquals("aaa-xxx-ccc-xxx", buffer.toString());
        }
        {
            StringBuilder buffer = new StringBuilder("aaa-bbb-ccc-bbb");
            int count=StringBuilders.replaceAll(buffer, "ddd", "yyy");
            Assertions.assertEquals(0, count);
            Assertions.assertEquals("aaa-bbb-ccc-bbb", buffer.toString());
        }
    }

    @Test
    void appendTokenIfBuilderIsNonEmpty() {
        {
            StringBuilder builder=new StringBuilder();
            StringBuilders.appendTokenIfBuilderIsNonEmpty(builder, "-");
            Assertions.assertEquals("", builder.toString());
        }
        {
            StringBuilder builder=new StringBuilder("aaa");
            StringBuilders.appendTokenIfBuilderIsNonEmpty(builder, "-");
            Assertions.assertEquals("aaa-", builder.toString());
        }
    }

    @Test
    void appendDelimiterIfNotOnToken() {
        {
            StringBuilder builder=new StringBuilder("xxx");
            StringBuilders.appendDelimiterIfNotOnToken(builder,".","bin");
            Assertions.assertEquals("xxx.", builder.toString());
        }
        {
            StringBuilder builder=new StringBuilder("xxx");
            StringBuilders.appendDelimiterIfNotOnToken(builder,".",".bin");
            Assertions.assertEquals("xxx", builder.toString());
        }
    }

    @Test
    void appendDelimiterAndToken() {
        {
            StringBuilder builder=new StringBuilder("xxx");
            StringBuilders.appendDelimiterAndToken(builder,".","bin");
            Assertions.assertEquals("xxx.bin", builder.toString());
        }
        {
            StringBuilder builder=new StringBuilder("xxx");
            StringBuilders.appendDelimiterAndToken(builder,".",".bin");
            Assertions.assertEquals("xxx.bin", builder.toString());
        }
    }
}
