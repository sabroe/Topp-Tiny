package com.yelstream.topp.lang;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Test suite for {@code com.yelstream.topp.lang}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2013-10-21
 */
@Suite
@SelectClasses({StringBuildersTest.class,
                StringsTest.class})
public class LangTestSuite {
}
