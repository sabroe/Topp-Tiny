package com.yelstream.topp.util.format;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Test suite for {@code com.yelstream.topp.util.format}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-12-08
 */
@Suite
@SelectClasses({NamedFormatterTest.class,
                NamedFormattersTest.class,
                ReplacersTest.class,
                ReplacerTest.class})
public class FormatTestSuite {
}
