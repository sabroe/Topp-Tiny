package com.yelstream.topp.util.uuid.test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Test suite for {@code com.yelstream.util.concurrent}. 
 * 
 * @author Morten Sabroe Mortensen
 */
@Suite
@SelectClasses({SequentialSpeedUUIDFactoryTest.class,
                ConcurrentSpeedUUIDFactoryTest.class,
                SequentialCorrectnessUUIDFactoryTest.class,
                ConcurrentCorrectnessUUIDFactoryTest.class})
public class UUIDFactoryTestSuite {
    //Empty!
}
