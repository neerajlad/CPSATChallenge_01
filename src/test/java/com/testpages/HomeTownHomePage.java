package com.testpages;

import java.util.Map;

import org.testng.Assert;

import com.automation.core.managers.IDriverManager;
import com.automation.core.page.impl.Page;

public class HomeTownHomePage extends Page {
	IDriverManager driverManager = null;
	Map<String, String> params;

	public HomeTownHomePage(Map<String, String> params, IDriverManager driverManager)
			throws Exception {
		super(params, driverManager);
		this.driverManager = driverManager;
		this.params = params;
	}

	// Could not find Electronics under More menu
	public HomeTownHomePage clickOnElectronicMenu() throws Exception {
		getControl("btnNoThanks").waitUntilVisible();
		getControl("btnNoThanks").click();
		getControl("mnuElectronics").click();
		return new HomeTownHomePage(params, driverManager);
	}
	
	
	public HomeTownHomePage selectColorAsBlackFromFilter() throws Exception {
		getControl("drpColorFilter").waitUntilVisible();
		getControl("drpColorFilter").hover();
		getControl("drpValueAsBlack").click();
		getControl("lblBlackFilter").waitUntilVisible();
		return new HomeTownHomePage(params, driverManager);
	}
	
	public HomeTownHomePage clickOnFirstProduct() throws Exception {
		getControl("imgFirstProduct").hover();
		getControl("imgFirstProductQuickView").waitUntilClickable();
		getControl("imgFirstProductQuickView").waitUntilVisible();
		getControl("imgFirstProductQuickView").click();
		return new HomeTownHomePage(params, driverManager);
	}
	
	public HomeTownHomePage assertProductNameWithBlackText() throws Exception {
		String actualtext = getControl("lblProductTitle").getText();
		boolean isBlackFound = false;
		if(actualtext.contains("Black")) {
			isBlackFound = true;
		}
		Assert.assertEquals(isBlackFound, true);
		return new HomeTownHomePage(params, driverManager);
	}
	
	
	

}
