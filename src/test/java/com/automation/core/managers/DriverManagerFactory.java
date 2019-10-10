/**

 * 

 */
package com.automation.core.managers;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.automation.core.managers.desktopweb.DesktopBrowser;
import com.automation.core.managers.desktopweb.DesktopWebManager;
import com.automation.core.managers.impl.WebManager;
import com.automation.core.managers.platform.Platform;

public class DriverManagerFactory {

	static Platform platform;
	static WebDriver driver = null;
	
	public static IDriverManager createDriverManager(Map<String, String> params) throws Exception {

		DesiredCapabilities caps = null;
		platform = Platform.valueOf(params.get("platform_").toUpperCase());
		WebManager.printLog("INFO", "Script started on Platform : " + platform);
		DesktopBrowser browserType = DesktopBrowser.valueOf(params.get("browserType"));
		switch (platform) {
		case DESKTOP_WEB:
			
			switch (browserType) {
			case CHROME:
				caps = DesiredCapabilities.chrome();
				ChromeOptions options1 = new ChromeOptions();
				options1.addArguments("disable-notifications");
				options1.merge(caps);
				caps.setCapability("autoDismissAlerts", true);
				caps.setCapability("pageLoadStrategy", "normal");

				System.setProperty("webdriver.chrome.driver", params.get("driverPath"));
				try {
					driver = new ChromeDriver(options1);
				} catch (Exception e) {
					WebManager.printLog("ERROR", "Failed to Create Chrome Driver");
				}
				break;
			case IE:
				
				break;

			case FIREFOX:
				
				System.setProperty("webdriver.gecko.driver", params.get("driverPath"));
				caps=DesiredCapabilities.firefox();
				caps.setCapability("marionette", true);
				driver = new FirefoxDriver(caps);
				break;
				
			default:
				break;
			}
			
		}

		WebManager.printLog("INFO", "Successfully Created Web driver");

		switch (platform) {
		case DESKTOP_WEB:
			return new DesktopWebManager(driver);
		}
		WebManager.printLog("ERROR", "Failed to Create Agent");
		return null;
	}
	
	
}
