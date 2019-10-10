package com.testscripts;

import org.junit.Test;

public class HomeTownTest extends BasePageJUnit {
	
	@Test
	public void Challage_01_04() throws Exception {
		mainPage.gotToHomeTownURL()
		.clickOnElectronicMenu()
		.selectColorAsBlackFromFilter()
		.clickOnFirstProduct()
		.assertProductNameWithBlackText();
	}
	
}
