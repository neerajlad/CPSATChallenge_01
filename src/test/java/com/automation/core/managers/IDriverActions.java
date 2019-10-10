package com.automation.core.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public interface IDriverActions {

	WebDriverWait getWaiter();
	
	WebDriver getWebDriver() throws Exception;
	
	void waitForLoad();
	
}
