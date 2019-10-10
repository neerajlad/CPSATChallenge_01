package com.automation.core.managers.platform;

public enum Platform{
	DESKTOP_WEB;
	
	public static boolean isMobilePlatform(Platform platform) {
		return platform != Platform.DESKTOP_WEB;
	}
}
