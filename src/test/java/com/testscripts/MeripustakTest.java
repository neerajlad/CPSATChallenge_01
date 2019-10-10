package com.testscripts;

import org.testng.annotations.Test;

public class MeripustakTest extends BasePageTestNG {

	
	@Test
	public void Challage_01_01() throws Exception {
		 mainPage.gotoMeripitakURL()
		.verifyLogiWidthAndHight()
		.printingHrefOfTwitter()
		.clickOnCartIfItemIsZero()
		.verifyEmptyCartMesssage()
		.enterSearchTextAndSearch()
		.clickOnFirstSearchResultItem()
		.clickOnAddToCart()
		.verifyEmptyCartMesssageShouldNotDisplay();
	}
	
}
