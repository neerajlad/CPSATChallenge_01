package com.automation.core.managers;

import java.io.IOException;

public interface IWebActions {
	void takeSnapShot() throws IOException;

	void goTo(String url) throws Exception;

	void goBack() throws IOException;

	void switchToNewWindow() throws IOException;

	void switchToMainWindow() throws IOException;

	void acceptAlert() throws IOException;

	void scrollUp() throws IOException;

	void scrollUp(int height) throws IOException;

	void scrollDown() throws IOException, Exception;

	void scrollDown(int height) throws IOException, Exception;
	
	String getTitle() throws Exception;
}
