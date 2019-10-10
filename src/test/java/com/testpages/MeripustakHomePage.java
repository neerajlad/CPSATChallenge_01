package com.testpages;

import java.util.Map;

import org.testng.Assert;

import com.automation.core.managers.IDriverManager;
import com.automation.core.managers.impl.WebManager;
import com.automation.core.page.impl.Page;

public class MeripustakHomePage extends Page {
	IDriverManager driverManager = null;
	Map<String, String> params;

	public MeripustakHomePage(Map<String, String> params, IDriverManager driverManager)
			throws Exception {
		super(params, driverManager);
		this.driverManager = driverManager;
		this.params = params;
	}

	public MeripustakHomePage verifyLogiWidthAndHight() throws Exception {
		WebManager.printLog("INFO", "Retriving Logo Widht and Hight");
		WebManager.printLog("INFO", "Hight : " + getControl("imglogo").getHight());
		WebManager.printLog("INFO", "Width : " + getControl("imglogo").getWidth());
		WebManager.printLog("INFO", "Successfully retrived Logo Widht and Hight");
		return new MeripustakHomePage(params, driverManager);
	}

	public MeripustakHomePage printingHrefOfTwitter() throws Exception {
		WebManager.printLog("INFO", "Printing Href link of twitter");
		WebManager.printLog("INFO", "HREF : " + getControl("icoTwitter").getAttribute("scr"));
		WebManager.printLog("INFO", "Successfully Printed Href link of twitter");
		return new MeripustakHomePage(params, driverManager);
	}

	public MeripustakCartPage clickOnCartIfItemIsZero() throws Exception {

		String cartValue = getControl("lblShoppintItem").getText();
		String[] itemsString = cartValue.split(" ");
		int cartDigit = Integer.parseInt(itemsString[0]);
		if (cartDigit == 0) {
			getControl("lblShoppintItem").click();
		} else {
			WebManager.printLog("INFO", "More cart value found");
		}

		return new MeripustakCartPage(params, driverManager);
	}

	public MeripustakHomePage enterSearchTextAndSearch() throws Exception {
		getControl("txtSearchItemBox").enterText("Java Book");
		getControl("btnsearch").click();
		return new MeripustakHomePage(params, driverManager);
	}
	
	public MeripustakHomePage clickOnFirstSearchResultItem() throws Exception {
		getControl("firstResultItem").click();
		return new MeripustakHomePage(params, driverManager);
	}
	
	public MeripustakCartPage clickOnAddToCart() throws Exception {
		getControl("btnAddToCart").click();
		return new MeripustakCartPage(params, driverManager);
	}
	
	public MeripustakHomePage verifyMeripustakMainPage() throws Exception {
		Assert.assertEquals(getDriverManager().getWebDriver().getCurrentUrl(), "https://www.meripustak.com/");
		;
		return new MeripustakHomePage(params, driverManager);
	}

}
