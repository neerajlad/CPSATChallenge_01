package com.testscripts;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CIITest.class, HomeTownTest.class, PremierLeagueTest.class })
public class AllTests {

}
