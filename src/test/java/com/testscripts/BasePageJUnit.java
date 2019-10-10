package com.testscripts;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;

import com.automation.core.managers.DriverManagerFactory;
import com.automation.core.managers.IDriverManager;
import com.automation.core.managers.impl.WebManager;
import com.automation.core.utilis.JavaUtils;
import com.google.common.collect.Multimap;
import com.testpages.MainPage;

public class BasePageJUnit {
	private static Map<String, String> params;
	public MainPage mainPage;
	IDriverManager driverManager;
	private String testName;
	private static String testDataFilePath;
	static Map<String, Multimap<String, String>> testDataContent = new HashMap<String, Multimap<String, String>>();
	private static Map<String, String> testDataBlock = new HashMap<String, String>();
	public static String env;
	private String projectName;
	private String browserType;
	private String url;
	

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Rule
	public TestName name = new TestName();

	public Map<String, Multimap<String, String>> gettestDataContent() {
		return testDataContent;
	}
	
	

	@BeforeClass
	public static void dataReader() throws Exception {
		params = new HashMap<String, String>();

		params.put("platform_", "DESKTOP_WEB"); // DESKTOP_WEB
		params.put("environment_", "STAGE"); // PROD || Stage
		params.put("driverPath", "/Users/neeraj.kumar/Desktop/geckodriver");
		params.put("browserType", "FIREFOX");

		env = getTestContextParams().get("environment_");

		testDataFilePath = JavaUtils.getPath(String.format("testData//%s//test-data.ini", params.get("environment_")));
		testDataContent = JavaUtils.readTestData(testDataFilePath);

	}
	
	
	@Before()
	public void setUp() throws Exception {
		try {
			testName = name.getMethodName();
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

	@After
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBrowserType() {
		return browserType;
	}

	public void setBrowserType(String browserType) {
		this.browserType = browserType;
	}
}
