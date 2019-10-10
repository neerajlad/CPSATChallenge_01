package com.automation.core.control.impl;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import com.automation.core.control.IControl;
import com.automation.core.loader.Identifier;
import com.automation.core.managers.IDriverManager;
import com.automation.core.managers.impl.WebManager;
import com.automation.core.managers.platform.Platform;
import com.automation.core.page.IPage;

public abstract class Control implements IControl {
	private IPage page = null;
	private IDriverManager driverManager = null;
	private Platform platform = null;
	private WebElement webElement = null;

	public Control(IPage page, WebElement element) {
		this.setPage(page);
		this.setDriverManager(page.getDriverManager());
		this.setPlatform(page.getPlatform());
		this.setRawWebElement(element);
	}

	protected WebElement getRawWebElement() {
		return webElement;
	}

	private void setRawWebElement(WebElement wrappedElement) {
		this.webElement = wrappedElement;
	}

	protected IDriverManager getDriverManager() {
		return driverManager;
	}

	private void setDriverManager(IDriverManager driverManager) {
		this.driverManager = driverManager;
	}
	
	public static IControl createControl(IPage page, String name, Identifier by) throws Exception {
		try {
			WebManager.printLog("INFO", "Trying to createControl on Page " + page.getClass().getSimpleName()
					+ ", Object Name : " + name + ", LOCATOR : " + by.getIdType() + ",  VALUE : " + by.getValue());
			return createWebElement(page, name, by);

		} catch (Exception e) {
			WebManager.printLog("ERROR",
					"Failed to createControl on Page " + page.getClass().getSimpleName() + ", Object Name : " + name
							+ ", LOCATOR : " + by.getIdType() + ",  VALUE : " + by.getValue() + "  - Log Trace :"
							+ e.getMessage());
			throw new Exception("Failed to createControl on Page " + page.getClass().getSimpleName() + " Object Name : "
					+ name + ",  LOCATOR : " + by.getIdType() + ", VALUE : " + by.getValue());
		}
	}

	private static IControl createWebElement(IPage page, String name, Identifier by) throws Exception {
		return new WebControl(page, (WebElement) identify(page.getDriverManager().getWebDriver(), by).get(0));
	}

	public IControl getControl(String name) throws Exception {
		Identifier id = this.getPage().getIdentifier(name);
		try {
			WebManager.printLog("INFO", "Trying to getControl Name " + name + ", LOCATOR : " + id.getIdType()
					+ ", VALUE : " + id.getValue());
			return this.findWebElement(name, id);
		} catch (Exception e) {
			WebManager.printLog("ERROR", "Failed to getControl Name : " + name + ", LOCATOR : " + id.getIdType()
					+ ",  VALUE : " + id.getValue() + "  - Log Trace :" + e.getMessage());
			throw new Exception("Failed to getControl Name : " + name + ",  LOCATOR : " + id.getIdType() + ", VALUE : "
					+ id.getValue());
		}
	}

	private IControl findWebElement(String name, Identifier by) throws Exception {
		return new WebControl(page, (WebElement) identify(this.getRawWebElement(), by).get(0));
	}

	public static List<IControl> createControls(IPage page, String name, Identifier by) throws Exception {
		try {
			WebManager.printLog("INFO", "Trying to createControls on Page " + page.getClass().getSimpleName()
					+ " Object Name : " + name + ", LOCATOR : " + by.getIdType() + ", VALUE : " + by.getValue());
			return createWebElements(page, name, by);
		} catch (Exception e) {
			WebManager.printLog("ERROR",
					"Failed to createControls on Page " + page.getClass().getSimpleName() + ", Object Name : " + name
							+ ", LOCATOR : " + by.getIdType() + ",  VALUE : " + by.getValue() + "  - Log Trace :"
							+ e.getMessage());
			throw new Exception("Failed to createControls on Page " + page.getClass().getSimpleName()
					+ " Object Name : " + name + ",  LOCATOR : " + by.getIdType() + ", VALUE : " + by.getValue());
		}
	}

	@SuppressWarnings("unchecked")
	private static List<IControl> createWebElements(IPage page, String name, Identifier by) throws Exception {
		List<IControl> outList = new ArrayList<IControl>();
		for (WebElement element : (List<WebElement>) identify(page.getDriverManager().getWebDriver(), by)) {
			outList.add(new WebControl(page, element));
		}
		return outList;
	}

	public List<IControl> getControls(String name) throws Exception {
		Identifier id = this.getPage().getIdentifier(name);
		try {
			WebManager.printLog("INFO", "Trying to getControls Name : " + name + ", LOCATOR : " + id.getIdType()
					+ ", VALUE : " + id.getValue());
			return this.findWebElements(name, id);
		} catch (Exception e) {
			WebManager.printLog("ERROR", "Failed to getControls Name : " + name + ", LOCATOR : " + id.getIdType()
					+ ",  VALUE : " + id.getValue() + "  - Log Trace :" + e.getStackTrace());
			throw new Exception("Failed to getControls Name : " + name + ",  LOCATOR : " + id.getIdType() + ", VALUE : "
					+ id.getValue());
		}
	}

	@SuppressWarnings("unchecked")
	private List<IControl> findWebElements(String name, Identifier by) throws Exception {
		List<IControl> outList = new ArrayList<IControl>();
		for (WebElement element : (List<WebElement>) identify(this.getRawWebElement(), by)) {
			outList.add(new WebControl(page, element));
		}
		return outList;
	}

	private static List<?> identify(SearchContext finder, Identifier by) throws Exception {
		List<?> elements = null;
		switch (by.getIdType()) {
		case ID:
			elements = finder.findElements(By.id(by.getValue()));
			break;
		case XPATH:
			elements = finder.findElements(By.xpath(by.getValue()));
			break;
		case NAME:
			elements = finder.findElements(By.name(by.getValue()));
			break;
		case LINKTEXT:
			elements = finder.findElements(By.linkText(by.getValue()));
			break;
		case CLASSNAME:
			elements = finder.findElements(By.className(by.getValue()));
			break;
		case CSS:
			elements = finder.findElements(By.cssSelector(by.getValue()));
			break;
		case PARTIALLINKTEXT:
			elements = finder.findElements(By.partialLinkText(by.getValue()));
			break;
		case TAGNAME:
			elements = finder.findElements(By.tagName(by.getValue()));
			break;
		default:
			WebManager.printLog("ERROR", "Wrong identifier passed. Identifier Type : " + by.getIdType());
			throw new Exception("Wrong identifier passed. Identifier Type : " + by.getIdType());
		}
		WebManager.printLog("INFO", "Found Element By LOCATOR : " + by.getIdType() + ", VALUE : " + by.getValue());
		return elements;
	}

	public IPage getPage() {
		return page;
	}

	public void setPage(IPage page) {
		this.page = page;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	

}
