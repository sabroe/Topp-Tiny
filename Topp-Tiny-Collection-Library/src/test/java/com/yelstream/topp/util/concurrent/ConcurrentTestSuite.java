/*** FILE "ConcurrentTestSuite.java" ******************************************/ 

/******************************************************************************/
/**                                                                          **/
/**   Yelstream Software, Morten Sabroe Mortensen.                           **/
/**                                                                          **/
/******************************************************************************/

package com.yelstream.topp.util.concurrent;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/*** ConcurrentTestSuite: *****************************************************/

/**
 * Test suite for {@code com.yelstream.util.concurrent}. 
 * 
 * @author Morten Sabroe Mortensen
 */
@RunWith(Suite.class)
@SuiteClasses({TimeUnitConverterTest.class,TimeUnitUtilityTest.class})
public class ConcurrentTestSuite
{
  //Empty!
}

/******** "ConcurrentTestSuite.java" ******************************************/ 
