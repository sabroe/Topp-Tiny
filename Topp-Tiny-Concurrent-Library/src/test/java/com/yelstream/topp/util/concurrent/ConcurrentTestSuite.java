/*** FILE "ConcurrentTestSuite.java" ******************************************/ 

/******************************************************************************/
/**                                                                          **/
/**   Yelstream Software, Morten Sabroe Mortensen.                           **/
/**                                                                          **/
/******************************************************************************/

package com.yelstream.topp.util.concurrent;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/*** ConcurrentTestSuite: *****************************************************/

/**
 * Test suite for {@code com.yelstream.util.concurrent}. 
 *
 * @author Morten Sabroe Mortenen
 * @version 1.0
 * @since 2013-02-28
 */
@Suite
@SelectClasses({TimeUnitConverterTest.class,TimeUnitUtilityTest.class})
public class ConcurrentTestSuite
{
  //Empty!
}

/******** "ConcurrentTestSuite.java" ******************************************/ 
