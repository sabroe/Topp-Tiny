package com.yelstream.topp.util.collection;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Test suite for {@code com.yelstream.topp.util.collection}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-12-17
 */
@Suite
@SelectClasses({MapsTest.class,
                ReferenceTest.class})
public class CollectionTestSuite {
}
