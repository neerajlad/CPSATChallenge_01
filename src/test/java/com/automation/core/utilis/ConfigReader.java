package com.automation.core.utilis;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.ini4j.Ini;
import org.ini4j.Profile.Section;


public class ConfigReader {
	private Map<String, String> configParamters = new HashMap<String, String>();

	public Map<String, String> readConfig() throws Exception {
		
		
		String path = JavaUtils.getPath("configuration/Config.ini");
		Ini iniFile = new Ini(new File(path));
		
		for (String name : iniFile.keySet()) {
			Section section = iniFile.get(name);
			for (String key : section.keySet()) {
				getConfigParamters().put(key, section.get(key));
			}
		}
		return configParamters;
	}

	public void setConfigParamters(Map<String, String> configParamters) {
		this.configParamters = configParamters;
	}

	public Map<String, String> getConfigParamters() {
		return configParamters;
	}
}
