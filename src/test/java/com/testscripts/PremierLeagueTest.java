package com.testscripts;

import org.junit.Test;

public class PremierLeagueTest extends BasePageJUnit {
	
	@Test
	public void Challage_01_03() throws Exception {
		mainPage.gotoPremierLeagueUrl()
		.clickOnTablesLink()
		.contextClickOnArsenal()
		.gotoToNewTab()
		.findOfficialURLFromNewTab()
		.printThePageTitle()
		.goBackToMainTab()
		.printThePageTitle();
	}
	
}
