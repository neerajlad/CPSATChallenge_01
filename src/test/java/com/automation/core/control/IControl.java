package com.automation.core.control;

import java.util.List;

import org.openqa.selenium.Keys;

public interface IControl {
	void click() throws Exception;

	void enterText(String text);

	void enterText(Keys text);
	
	void clear();

	String getText();

	boolean verifyText(String text);

	boolean isDisplayed();
	
	boolean isEnabled();
	
	String getAttribute(String attribute);

	boolean isClickable();

	void scrollTo();

	void selectDropDownByValue(String value);
	
	void selectDropDownByVisibleText(String visibleText);
	
	void selectDropDownByIndex(int index);
	
	List<String> getAllDropDownVisibleText();

	void waitUntilVisible();

	void waitUntilClickable();
	
	void waitUntilInvisible();
	
	IControl getControl(String name) throws Exception;
	
	List<IControl> getControls(String name) throws Exception ;
	
	void hover() throws Exception;
	
	int getWidth() throws Exception;
	
	int getHight() throws Exception;
	
	void rightClickOnElement() throws Exception;
}
