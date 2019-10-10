package com.automation.core.utilis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Profile.Section;
import org.json.JSONObject;

import com.automation.core.managers.DriverManagerFactory;
import com.automation.core.managers.platform.Platform;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class JavaUtils {

	public static String getPath(String relPath) throws Exception {
		return (new File(DriverManagerFactory.class.getClassLoader().getResource(relPath).toURI())).getAbsolutePath();
	}

	// make static all methods
	public static String getPropertyOf(String key) {
		String propValue = null;
		try {
			FileInputStream input = new FileInputStream("./config.properties");
			Properties prop = new Properties();
			prop.load(input);
			propValue = prop.getProperty(key);
		} catch (IOException e) {
			throw new NullPointerException("Unable to get the property of : " + key);
		}
		return propValue;
	}

	public static String datetime(String dateFormat) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(cal.getTime());
	}

	public static void setConfigValueByKeyAndTitle(String title, String Key, String parameterName) throws Exception {

		String path = JavaUtils.getPath("configuration/Config.ini");
		Ini ini = new Ini(new File(path));

		// Ini ini = new Ini(new File(System.getProperty("user.dir") +
		// "/src/test/resources/Configuration/Config.ini"));
//		Ini ini = getPath("Configuration/Config.ini");
		ini.put(title, Key, parameterName);
		ini.store();
	}

	public static String getConfigValueByKey(String getTitle, String getKey) throws Exception {
		// Ini ini = new Ini(new File(System.getProperty("user.dir") +
		// "/src/test/resources/Configuration/Config.ini"));
		String path = JavaUtils.getPath("configuration/Config.ini");
		Ini ini = new Ini(new File(path));

		String value = ini.get(getTitle, getKey);
		if (value == null) {
			System.err.println("Enter Proper Title or Key!");
		}
		return value;
	}

	public static Map<String, Multimap<String, String>> readIni(Platform platform, String path)
			throws InvalidFileFormatException, IOException {
		String[] temp;
		Map<String, Multimap<String, String>> fileContent = new HashMap<String, Multimap<String, String>>();
		String strPlatform = platform.toString();
		Multimap<String, String> locators = null;
		Ini iniFile = new Ini(new File(path));
		for (String name : iniFile.keySet()) {
			Section section = iniFile.get(name);
			for (String key : section.keySet()) {
				locators = ArrayListMultimap.create();
				temp = key.split("~");
				if (temp[0].equalsIgnoreCase(strPlatform)) {
					locators.put(temp[1], section.get(key));
					fileContent.put(name, locators);
				}
			}
		}
		return fileContent;
	}

	public static Map<String, Multimap<String, String>> jsonReader(Platform Platform, String Path) {
		String jsonFile = readFile(Path);
		JSONObject jsonData = new JSONObject(jsonFile);
		Map<String, Multimap<String, String>> fileContent = new HashMap<String, Multimap<String, String>>();
		String strPlatform = Platform.toString();
		if (strPlatform.contains("MWEB")) {
			strPlatform = "MWEB";
		}
		String[] value;
		Multimap<String, String> locators = null;
		for (String name : jsonData.keySet()) {
			locators = ArrayListMultimap.create();
			JSONObject section = jsonData.getJSONObject(name);
			String string = section.getString(strPlatform);
			if (string.isEmpty()) {
			} else {
				value = string.split("=", 2);
				locators.put(value[0].trim(), value[1].trim());
				fileContent.put(name.trim(), locators);
			}
		}

		return fileContent;
	}

	public static String readFile(String filename) {
		String result = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
			result = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static Map<String, String> readConfig() throws Exception {

		Map<String, String> configParamters = new HashMap<String, String>();
		// Ini iniFile = new Ini(new File(System.getProperty("user.dir") +
		// "/src/test/resources/configuration/Config.ini"));

		String path = JavaUtils.getPath("configuration/Config.ini");
		Ini iniFile = new Ini(new File(path));

		for (String name : iniFile.keySet()) {
			Section section = iniFile.get(name);
			for (String key : section.keySet()) {
				configParamters.put(key, section.get(key));
			}
		}
		return configParamters;
	}

	public static Map<String, Multimap<String, String>> readTestData(String Path)
			throws InvalidFileFormatException, IOException {
		Map<String, Multimap<String, String>> testDataContent = new HashMap<String, Multimap<String, String>>();
		Multimap<String, String> locators = null;
		Ini iniFile = new Ini(new File(Path));
		for (String name : iniFile.keySet()) {
			locators = ArrayListMultimap.create();
			Section section = iniFile.get(name);
			for (String key : section.keySet()) {
				String value = section.get(key);
				locators.put(key, value);
			}
			testDataContent.put(name, locators);
		}
		return testDataContent;
	}

	public static Map<String, Multimap<String, String>> readExcelTestData(String Path,String sheetName)
			throws InvalidFileFormatException, IOException, InvalidFormatException {
		Map<String, Multimap<String, String>> testDataContent = new HashMap<String, Multimap<String, String>>();
		Multimap<String, String> locators = null;
		OPCPackage fis = OPCPackage.open(new File(Path));
//		FileInputStream fis = new FileInputStream(Path);

		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		XSSFSheet sheet = workbook.getSheet(sheetName);
		locators = ArrayListMultimap.create();
		int lastRow = sheet.getLastRowNum();
		// Looping over entire row
		for (int i = 1; i <= lastRow; i++) {

			XSSFRow row = sheet.getRow(i);

			Cell valueCell = row.getCell(1);
			Cell keyCell = row.getCell(0);

			
			String value = valueCell.getStringCellValue().trim();
			String key = keyCell.getStringCellValue().trim();
			
			locators.put(key, value);

			testDataContent.put(sheet.getSheetName(), locators);
		}

		return testDataContent;
	}

}
