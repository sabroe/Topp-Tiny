package com.yelstream.topp.util.nio;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Test suite for {@code com.yelstream.topp.util.nio}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-12-11
 */
@Suite
@SelectClasses({PathDeleterTest.class,
                PathUtilityTest.class})
public class PathTestSuite {
}
