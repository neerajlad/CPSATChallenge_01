package com.testscripts;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.automation.core.managers.DriverManagerFactory;
import com.automation.core.managers.IDriverManager;
import com.automation.core.managers.impl.WebManager;
import com.automation.core.utilis.JavaUtils;
import com.google.common.collect.Multimap;
import com.testpages.MainPage;
import com.testpages.MeripustakHomePage;

public class BasePageTestNG {
	private static Map<String, String> params;
	public MainPage mainPage;
	IDriverManager driverManager;
	private String testName;
	private static String testDataFilePath;
	static Map<String, Multimap<String, String>> testDataContent = new HashMap<String, Multimap<String, String>>();
	private static Map<String, String> testDataBlock = new HashMap<String, String>();
	public static String env;
	
	  @Parameters({"browserType"})
	  @BeforeMethod 
	  public void beforeTest(String browserType,ITestContext context, Method method) throws Exception {
		  
		  params = new HashMap<String, String>();

			params.put("platform_", "DESKTOP_WEB"); // DESKTOP_WEB
			params.put("environment_", "STAGE"); // PROD || Stage
			params.put("driverPath", "/Users/neeraj.kumar/Desktop/chromedriver");
			params.put("browserType",browserType);
			
			env = getTestContextParams().get("environment_");

//			testDataFilePath = JavaUtils.getPath(String.format("testData//%s//test-data.xlsx", params.get("environment_")));
//			testDataContent = JavaUtils.readExcelTestData(testDataFilePath,testName);
			
//			testDataFilePath = JavaUtils.getPath(String.format("testData//%s//test-data.ini", params.get("environment_")));
//			testDataContent = JavaUtils.readTestData(testDataFilePath);
			
			try {
				testName = method.getName();
				
				testDataFilePath = JavaUtils.getPath(String.format("testData//%s//test-data.xlsx", params.get("environment_")));
				testDataContent = JavaUtils.readExcelTestData(testDataFilePath,testName);
				
				System.out.println(String.format("Test Method Name :: %s ", testName));
				Multimap<String, String> multimap = gettestDataContent().get(testName);
				for (String key : multimap.keySet()) {
					String value = multimap.get(key).toString();
					value = value.substring(1, value.length() - 1);
					testDataBlock.put(key, value);
				}
			} catch (Exception e) {
				WebManager.printLog("Error",
						String.format("Requested test data is not available;Check with TestData file"));
				throw new Exception("Requested test data is not available;Check with TestData file");
			}

			driverManager = DriverManagerFactory.createDriverManager(getTestContextParams());
			mainPage = new MainPage(params, driverManager);
	  }

	public Map<String, Multimap<String, String>> gettestDataContent() {
		return testDataContent;
	}

	@AfterMethod
	public void tearDown() throws Exception {
		if (driverManager.getWebDriver() != null) {
			driverManager.getWebDriver().quit();
		}
	}

	public static Map<String, String> getTestContextParams() {
		return params;
	}

	public static Map<String, String> getTestDataBlock() {
		return testDataBlock;
	}

}
