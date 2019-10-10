package com.testpages;

import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.automation.core.control.IControl;
import com.automation.core.managers.IDriverManager;
import com.automation.core.page.impl.Page;

public class WoodlandHomePage extends Page {
	IDriverManager driverManager = null;
	Map<String, String> params;

	public WoodlandHomePage(Map<String, String> params, IDriverManager driverManager)
			throws Exception {
		super(params, driverManager);
		this.driverManager = driverManager;
		this.params = params;
	}

	public WoodlandHomePage clickOnSearchIcon() throws Exception {
		getControl("icoSearch").waitUntilVisible();
		getControl("icoSearch").click();
		return new WoodlandHomePage(params, driverManager);
	}
	
	public WoodlandHomePage enterSearchValue(String searchText) throws Exception {
		getControl("txtSearch").enterText(searchText);
		return new WoodlandHomePage(params, driverManager);
	}
	
	public WoodlandHomePage clickOnGo() throws Exception {
		getControl("btnGo").click();
		return new WoodlandHomePage(params, driverManager);
	}

	
	public WoodlandHomePage clickOnHighToLow() throws Exception {
		getControl("rdoHighToLow").waitUntilVisible();
		getControl("rdoHighToLow").click();
		getControl("rdoHighToLowActive").waitUntilVisible();
		return new WoodlandHomePage(params, driverManager);
	}

	public WoodlandHomePage searchItemAndCheckPriceOrderAsDecending(Map<String, String> testdata) throws Exception {

		for (Map.Entry<String, String> entry : testdata.entrySet()) {
//            System.out.println("Key = " + entry.getKey() + 
//                             ", Value = " + entry.getValue()); 
			clickOnSearchIcon();
			enterSearchValue(entry.getValue());
			clickOnGo();
			clickOnHighToLow();
			AssertPriceDecendingOrder();
		}

		return new WoodlandHomePage(params, driverManager);
	}

	public WoodlandHomePage AssertPriceDecendingOrder() throws Exception {
		
		int[] price = new int[8];
		
		int n = 8;
		
		List<IControl> lstPriceValues = getControls("lstPriceValues");
		int max = 0;
		for(IControl p : lstPriceValues) {
			if(max >= 8) {
				break;
			}
			String newPrice = p.getText().replace("R", "").trim();
			price[max] = Integer.parseInt(newPrice.trim().split(" ")[0].trim());
			max++;
		}
		
		Assert.assertEquals(true, isPriceDecendingOrder(price, n));
		
		return new WoodlandHomePage(params, driverManager);
	}
	
	
	
	
	private int priceValues(int price[], int n) {
		if(n == 1 || n == 0) {
			return 1;
		}
		
		if(price[n-1] > price[n-2]) {
			return 0; // not descending order
		}
		
		return priceValues(price, n-1);
	}
	
	private boolean isPriceDecendingOrder(int price[], int n) {
		if(priceValues(price, n) != 0) {
			return true;
		}else {
			return false;
		}
	}
	


}
