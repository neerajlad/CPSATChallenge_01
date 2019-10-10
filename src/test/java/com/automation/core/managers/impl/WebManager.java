package com.automation.core.managers.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.core.managers.IDriverManager;
import com.automation.core.managers.platform.Platform;
import com.automation.core.utilis.JavaUtils;

public abstract class WebManager implements IDriverManager {
	private WebDriver driver;
	private WebDriverWait wait;
	private Map<String, String> configParamters = new HashMap<String, String>();
	private boolean alwaysTakeSnapshot = false;
	private Platform platform = null;
	public static final Logger logger = Logger.getLogger("");

	public WebManager(WebDriver driver, Platform platform) throws Exception {

		this.configParamters = JavaUtils.readConfig();
		this.driver = driver;
		this.platform = platform;
		driver.manage().timeouts().implicitlyWait(Integer.valueOf(configParamters.get("IMPLICIT_WAIT_TIME_IN_SEC")),
				TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 30);
		String captureScreenshot = configParamters.get("ALWAYS_CAPTURE_SCREENSHOT");
		if (captureScreenshot.toUpperCase().equals("TRUE")) {
			alwaysTakeSnapshot = true;
		}

		String log4jConfigFile = JavaUtils.getPath("configuration/log4j.properties");
		PropertyConfigurator.configure(log4jConfigFile);
		printLog("INFO", "Config.in Parameter Data :: " + configParamters);
	}

	public static void printLog(String type, String message) {
		switch (type) {
		case "INFO":
			logger.info(message);
			break;
		case "ERROR":
			logger.error(message);
			break;
		case "DEBUG":
			logger.debug(message);
			break;
		default:
			logger.info(message);
			break;
		}
	}

	public WebManager(WebDriver driver) throws Exception {
		this(driver, Platform.DESKTOP_WEB);
	}

	protected Platform getPlatform() {
		return this.platform;
	}

	protected boolean shouldAlwaysTakeSnapShot() {
		return this.alwaysTakeSnapshot;
	}

	public WebDriver getWebDriver() throws Exception {
		return driver;
	}

	public WebDriverWait getWaiter() {
		return wait;
	}

	public Map<String, String> getConfigParamters() {
		return configParamters;
	}

	@Override
	public void takeSnapShot() throws IOException {
		try {
			TakesScreenshot scrShot = ((TakesScreenshot) driver);
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
			File DestFile = new File(System.getProperty("user.dir") + getConfigParamters().get("SCREENSHOT_PATH")
					+ JavaUtils.datetime(getConfigParamters().get("SCREENSHOT_TIMEFORMAT")) + ".jpg");
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (Exception e) {
			printLog("ERROR", "Issue while taking screenshot and the reason is  " + e.getMessage());
		}
	}

	@Override
	public void goTo(String url) throws Exception {
		try {
			printLog("INFO", "Tryng to open URL " + url);
			driver.get(url);
		} catch (Exception e) {
			printLog("ERROR", "Invalid Url : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public void goBack() throws IOException {
		try {
			printLog("INFO", "Tryng to goBack");
			driver.navigate().back();
		} catch (Exception e) {
			printLog("ERROR", "goBack : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public void switchToNewWindow() throws IOException {
		try {
			printLog("INFO", "Trying to switchToNewWindow ");
			Set<String> s = driver.getWindowHandles();
			Iterator<String> itr = s.iterator();
			itr.next();
			String w2 = (String) itr.next();
			driver.switchTo().window(w2);
		} catch (Exception e) {
			printLog("ERROR", "switchToNewWindow : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public void switchToMainWindow() throws IOException {
		try {
			printLog("INFO", "Trying to switchToMainWindow ");
			List<String> windows = new ArrayList<String>(driver.getWindowHandles());
			for (int i = 1; i < windows.size(); i++) {
				driver.switchTo().window(windows.get(i));
				driver.close();
			}
			driver.switchTo().window(windows.get(0));
		} catch (Exception e) {
			printLog("ERROR", "switchToMainWindow : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public void acceptAlert() throws IOException {
		try {
			printLog("INFO", "Attempt to acceptAlert ");
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			printLog("ERROR", "acceptAlert : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public void scrollUp() throws IOException {
		scrollUp(100);
	}

	@Override
	public void scrollUp(int height) throws IOException {
		try {
			printLog("INFO", "Attempt to scrollUp ");
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(" + height + ",0)", "");
		} catch (Exception e) {
			printLog("ERROR", "scrollUp : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public void scrollDown() throws IOException, Exception {
		scrollDown(100);
	}

	@Override
	public void scrollDown(int height) throws IOException, Exception {
		try {
			printLog("INFO", "Attempt to scrollDown ");
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0," + height + ")", "");
		} catch (Exception e) {
			printLog("ERROR", "scrollDown : " + e.getMessage());
			throw e;
		}
		if (this.shouldAlwaysTakeSnapShot()) {
			this.takeSnapShot();
		}
	}

	@Override
	public void waitForLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		getWaiter().until(pageLoadCondition);
	}
	
	@Override
	public String getTitle() {
		printLog("INFO", "Attempt to getTitle ");
		return driver.getTitle();
	}
	
}
