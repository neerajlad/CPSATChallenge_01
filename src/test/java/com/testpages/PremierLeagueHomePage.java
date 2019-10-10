package com.testpages;

import java.util.Map;

import org.openqa.selenium.Keys;

import com.automation.core.managers.IDriverManager;
import com.automation.core.page.impl.Page;

public class PremierLeagueHomePage extends Page {
	IDriverManager driverManager = null;
	Map<String, String> params;

	public PremierLeagueHomePage(Map<String, String> params, IDriverManager driverManager)
			throws Exception {
		super(params, driverManager);
		this.driverManager = driverManager;
		this.params = params;
	}

	public PremierLeagueHomePage clickOnTablesLink() throws Exception {
		getControl("acceptCookies").waitUntilVisible();
		getControl("acceptCookies").click();
		getControl("mnuTable").click();
		return new PremierLeagueHomePage(params, driverManager);
	}
	
	
	public PremierLeagueHomePage contextClickOnArsenal() throws Exception {
		scrollDown(500);
	//	Thread.sleep(3000);
	//	getControl("lnkArsenal").hover();
		getControl("lnkArsenal").rightClickOnElement();
	//	Thread.sleep(3000);
		return new PremierLeagueHomePage(params, driverManager);
	}
	
	public PremierLeagueHomePage findOfficialURLFromNewTab() throws Exception {
		System.out.println("New Tab URL: " + getDriverManager().getWebDriver().getCurrentUrl());
		System.out.println("Offical URL from Logo : " + getControl("imgLogo").getAttribute("href"));
		return new PremierLeagueHomePage(params, driverManager);
	}
	
	public PremierLeagueHomePage printThePageTitle() throws Exception {
		System.out.println("New Tab URL: " + getDriverManager().getTitle());
		return new PremierLeagueHomePage(params, driverManager);
	}

	public PremierLeagueHomePage goBackToMainTab() throws Exception {
		switchToMainWindow();
		return new PremierLeagueHomePage(params, driverManager);
	}
	
	public PremierLeagueHomePage gotoToNewTab() throws Exception {
		switchToNewWindow();
		return new PremierLeagueHomePage(params, driverManager);
	}

}
