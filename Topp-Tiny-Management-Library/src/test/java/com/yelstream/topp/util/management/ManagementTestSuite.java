package com.yelstream.topp.util.management;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Test suite for {@code com.yelstream.topp.util.management}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-12-03
 */
@Suite
@SelectClasses({DefaultPathGeneratorTest.class,
                HeapUtilityTest.class})
public class ManagementTestSuite {
}
