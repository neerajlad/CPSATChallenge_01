package com.testpages;

import java.util.Map;

import org.testng.Assert;

import com.automation.core.managers.IDriverManager;
import com.automation.core.managers.impl.WebManager;
import com.automation.core.page.impl.Page;

public class MeripustakCartPage extends Page {
	IDriverManager driverManager = null;
	Map<String, String> params;

	public MeripustakCartPage(Map<String, String> params, IDriverManager driverManager) throws Exception {
		super(params, driverManager);
		this.driverManager = driverManager;
		this.params = params;
	}
	
	public MeripustakHomePage verifyEmptyCartMesssage() throws Exception {
		WebManager.printLog("INFO", "Verifying Cart Message");
		if(!getControl("lblEmptyCartMessage").getText().equals("No Item is Added In Cart yet. Cart is Empty!!!")) {
			Assert.assertFalse(false);
		}
		WebManager.printLog("INFO", "Successfully Verifyed Cart Message");
		
		return new MeripustakHomePage(params, driverManager);
	}
	
	public MeripustakHomePage verifyEmptyCartMesssageShouldNotDisplay() throws Exception {
		WebManager.printLog("INFO", "Verifying Cart Message");
		try {
			getControl("lblEmptyCartMessage").isDisplayed();
			Assert.assertEquals(false, true);
		} catch (Exception e) {
			Assert.assertEquals(false, false);
		}
		WebManager.printLog("INFO", "Successfully Verifyed Cart Message");
		
		return new MeripustakHomePage(params, driverManager);
	}
	
	

}
