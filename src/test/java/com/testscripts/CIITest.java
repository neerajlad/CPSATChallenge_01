package com.testscripts;

import org.junit.Test;

public class CIITest extends BasePageJUnit {
	
	@Test
	public void Challage_01_02() throws Exception {
		mainPage.gotoCIIULR()
		.select3Attendee()
		.verifyAttendeeRows()
		.selectTitle()
		.printAllTitles();
	}
	
}
