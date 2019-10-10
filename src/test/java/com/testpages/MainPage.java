package com.testpages;

import java.util.Map;

import com.automation.core.managers.IDriverManager;
import com.automation.core.page.impl.Page;

public class MainPage extends Page {
	IDriverManager driverManager = null;
	Map<String, String> params;

	public MainPage(Map<String, String> params, IDriverManager driverManager)
			throws Exception {
		super(params, driverManager);
		this.driverManager = driverManager;
		this.params = params;
	}

	public MeripustakHomePage gotoMeripitakURL() throws Exception {
		getDriverManager().goTo("https://www.meripustak.com/");
		return new MeripustakHomePage(params, driverManager);
	}

	
	public CIIHomePage gotoCIIULR() throws Exception {
		getDriverManager().goTo("https://www.cii.in/OnlineRegistration.aspx");
		return new CIIHomePage(params, driverManager);
	}

	public PremierLeagueHomePage gotoPremierLeagueUrl() throws Exception {
		getDriverManager().goTo("https://www.premierleague.com");
		return new PremierLeagueHomePage(params, driverManager);
	}
	
	public HomeTownHomePage gotToHomeTownURL() throws Exception {
		getDriverManager().goTo("https://www.hometown.in/");
		return new HomeTownHomePage(params, driverManager);
	}
	
	public WoodlandHomePage gotoWoodlandURL() throws Exception {
		getDriverManager().goTo(" https://www.woodlandworldwide.com/");
		return new WoodlandHomePage(params, driverManager);
	}
	

}
