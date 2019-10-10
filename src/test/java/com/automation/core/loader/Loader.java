package com.automation.core.loader;

import java.util.HashMap;
import java.util.Map;

import com.automation.core.managers.platform.Platform;
import com.automation.core.utilis.JavaUtils;
import com.google.common.collect.Multimap;

public class Loader {
	Identifier identifier = null;
	String key;
	String value;
	Map<String,Multimap<String,String>> fileContent = new HashMap<String, Multimap<String, String>>();
	
	public Loader(Platform platform,String path) throws Exception {
		fileContent =  JavaUtils.jsonReader(platform,path);
	}

	public Map<String, Multimap<String, String>> getFileContent() {
		return fileContent;
	}

	
	public Identifier getIdentifierByName(String name){
		Multimap<String, String> objectName = fileContent.get(name);
		for(String k : objectName.keySet())
		{
			key = k;
			value = objectName.get(k).toString();
			int len = value.length();
			value = value.substring(1, len-1);
		}
		return new Identifier(key ,value);
		
	}
	
}


