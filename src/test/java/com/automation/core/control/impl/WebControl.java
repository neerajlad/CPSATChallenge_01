package com.automation.core.control.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.automation.core.managers.impl.WebManager;
import com.automation.core.page.IPage;

public class WebControl extends Control {

	public WebControl(IPage page, WebElement element) {
		super(page, element);
	}

	@Override
	public void click() throws IOException {
		getDriverManager().takeSnapShot();
		this.waitUntilVisible();
		this.waitUntilClickable();
		WebManager.printLog("INFO", "Trying to Click on" + this.getRawWebElement());
		this.getRawWebElement().click();
		WebManager.printLog("INFO", "Clicked Successfully on " + this.getRawWebElement());
	}

	@Override
	public void enterText(String text) {
		WebManager.printLog("INFO", "Trying to Enter Data : " + text);
		this.getRawWebElement().sendKeys(text);
		WebManager.printLog("INFO", "Successfully Entered " + text);
	}

	@Override
	public boolean verifyText(String text) {
		WebManager.printLog("INFO", "Verifing text value");
		String actualText = this.getRawWebElement().getText();
		WebManager.printLog("INFO", "Successfully verified the value");
		if(actualText.equals(text)) {
			return true;
		}else {
			return false;	
		}
	}

	@Override
	public boolean isDisplayed() {
		WebManager.printLog("INFO", "Checking object is isDisplayed or Not");
		boolean condition = false;
		try {
			condition = getRawWebElement().isDisplayed();
			WebManager.printLog("INFO", "isDisplay Status " + condition);
			return true;
		} catch (Exception e) {
			WebManager.printLog("INFO", "isDisplay Status " + condition);
			return false;
		}
		
	}

	@Override
	public boolean isClickable() {
		WebManager.printLog("INFO", "Checking object is isClickable or Not");
		boolean condition = getRawWebElement().isDisplayed();
		WebManager.printLog("INFO", "isClickable Status " + condition);
		return condition;
	}

	@Override
	public void scrollTo() {
	 // TODO : 
	}

	@Override
	public String getText() {
		return this.getRawWebElement().getText();
	}

	@Override
	public void enterText(Keys key) {
		WebManager.printLog("INFO", "Trying to Enter Key : " + key);
		this.getRawWebElement().sendKeys(key);
		WebManager.printLog("INFO", "Successfully key " + key + " Press/Enter");
	}

	@Override
	public void selectDropDownByValue(String value) {
		WebManager.printLog("INFO", "Trying to Select Dropdown value by : " + value);
		Select drpElement = new Select(this.getRawWebElement());
		drpElement.selectByValue(value);
		WebManager.printLog("INFO", "Successfully Selected Dropdown value by : " + value);
	}

	@Override
	public void waitUntilVisible() {
		this.getDriverManager().getWaiter().until(ExpectedConditions.visibilityOf(this.getRawWebElement()));
	}

	@Override
	public void waitUntilClickable() {
		this.getDriverManager().getWaiter().until(ExpectedConditions.elementToBeClickable(this.getRawWebElement()));
	}
	
	@Override
	public void waitUntilInvisible() {
		this.getDriverManager().getWaiter().until(ExpectedConditions.invisibilityOf(this.getRawWebElement()));
	}

	@Override
	public boolean isEnabled() {
		WebManager.printLog("INFO", "Checking object is isEnabled or Not");
		boolean condition = getRawWebElement().isEnabled();
		WebManager.printLog("INFO", "isEnabled Status " + condition);
		return condition;
	}

	@Override
	public String getAttribute(String attribute) {
		WebManager.printLog("INFO", "Reading Attribute value");
		String txt = this.getRawWebElement().getAttribute(attribute);
		WebManager.printLog("INFO", "Successfully Attribute the value");
		return txt;
	}

	@Override
	public List<String> getAllDropDownVisibleText() {
		WebManager.printLog("INFO", "Trying to Read all Visible Text");
		Select drpElement = new Select(this.getRawWebElement());
		ArrayList<String> dropDownValues = new ArrayList<String>();
		
		List<WebElement> lstElements = drpElement.getOptions();
		for(WebElement e: lstElements) {
			
			dropDownValues.add(e.getText());
		}
		WebManager.printLog("INFO", "Finised Reading all Visible Text : " + dropDownValues.toString());
		return dropDownValues;
	}

	@Override
	public void selectDropDownByVisibleText(String VisibleText) {
		WebManager.printLog("INFO", "Trying to Select Dropdown value by Visible Text : " + VisibleText);
		Select drpElement = new Select(this.getRawWebElement());
		drpElement.selectByVisibleText(VisibleText);
		WebManager.printLog("INFO", "Successfully Selected Dropdown value by Visible Text : " + VisibleText);
	}

	@Override
	public void selectDropDownByIndex(int index) {
		WebManager.printLog("INFO", "Trying to Select Dropdown value by Index : " + index);
		Select drpElement = new Select(this.getRawWebElement());
		drpElement.selectByIndex(index);
		WebManager.printLog("INFO", "Successfully Selected Dropdown value by Index : " + index);
	}

	@Override
	public void clear() {
		WebManager.printLog("INFO", "Clearing the value");
		this.getRawWebElement().clear();
		WebManager.printLog("INFO", "Cleared the value");
	}

	@Override
	public void hover() throws Exception {
		Actions action = new Actions(getDriverManager().getWebDriver());
        action.moveToElement(this.getRawWebElement()).build().perform();
	}

	@Override
	public int getWidth() throws Exception {
		WebManager.printLog("INFO", "Trying to getWidth");
		int w =  this.getRawWebElement().getSize().getWidth();
		WebManager.printLog("INFO", "Successfully got Width");
		return w;
	}

	@Override
	public int getHight() throws Exception {
		WebManager.printLog("INFO", "Trying to getHight");
		int h =  this.getRawWebElement().getSize().getHeight();
		WebManager.printLog("INFO", "Successfully got Hight");
		return h;
	}

	@Override
	public void rightClickOnElement() throws Exception {
		WebManager.printLog("INFO", "Trying to right Click On Element");
		Actions action = new Actions(getDriverManager().getWebDriver());
        action.contextClick(this.getRawWebElement()).perform(); 
        action.sendKeys("t").build().perform();
     //   action.sendKeys(Keys.ARROW_DOWN).perform();
       // action.sendKeys(Keys.RETURN).perform();
		WebManager.printLog("INFO", "Successfully displayed Context Menu");
	}
}
