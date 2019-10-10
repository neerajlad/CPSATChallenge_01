package com.testpages;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import com.automation.core.control.IControl;
import com.automation.core.managers.IDriverManager;
import com.automation.core.page.impl.Page;

public class CIIHomePage extends Page {
	IDriverManager driverManager = null;
	Map<String, String> params;

	public CIIHomePage(Map<String, String> params, IDriverManager driverManager)
			throws Exception {
		super(params, driverManager);
		this.driverManager = driverManager;
		this.params = params;
	}

	public CIIHomePage select3Attendee() throws Exception {
		selectDropdpwnByName(getControl("drpNoOfAttendee"),"3");
		
		return new CIIHomePage(params, driverManager);
	}
	
	public CIIHomePage verifyAttendeeRows() throws Exception {
		int rows = getControls("tabAttendeeRows").size();
		assertEquals(rows, 3);
		return new CIIHomePage(params, driverManager);
	}
	
	public CIIHomePage selectTitle() throws Exception {
		List<IControl> rows = getControls("tabAttendeeRows");
		
		selectDropdpwnByName(rows.get(0), "Admiral");
		selectDropdpwnByName(rows.get(1), "CA");
		selectDropdpwnByName(rows.get(2), "CS");
		
		return new CIIHomePage(params, driverManager);
	}
	
	public CIIHomePage printAllTitles() throws Exception {
		List<IControl> rows = getControls("tabAttendeeRows");
		List<String> lstDropDownValues = rows.get(0).getAllDropDownVisibleText();
		
		System.out.println("Title Values");
		for(String s: lstDropDownValues) {
			System.out.println(s);
		}
		return new CIIHomePage(params, driverManager);
	}

	private void selectDropdpwnByName(IControl dropdpwn, String visibleText) throws Exception {
		dropdpwn.selectDropDownByVisibleText(visibleText);
	}


}
