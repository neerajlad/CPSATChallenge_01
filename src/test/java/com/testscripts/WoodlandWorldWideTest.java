package com.testscripts;

import org.testng.annotations.Test;

public class WoodlandWorldWideTest extends BasePageTestNG {

	
	@Test
	public void Challage_01_06() throws Exception {
		 mainPage.gotoWoodlandURL()
		.searchItemAndCheckPriceOrderAsDecending(getTestDataBlock());
		
	}
	

	
}
