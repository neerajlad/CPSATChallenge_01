package com.automation.core.page.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.automation.core.control.IControl;
import com.automation.core.control.impl.Control;
import com.automation.core.loader.Identifier;
import com.automation.core.loader.Loader;
import com.automation.core.managers.IDriverManager;
import com.automation.core.managers.platform.Platform;
import com.automation.core.page.IPage;
import com.automation.core.utilis.JavaUtils;

public class Page implements IPage {
	IDriverManager driverManager = null;
	Loader loader = null;
	String path = null;
	Platform platform = null;

	public Page(Map<String, String> params, IDriverManager driverManager) throws Exception {
		String path = JavaUtils.getPath("objectrepository") + "/" + this.getClass().getSimpleName() + ".json";

		Platform platform = Platform.valueOf(params.get("platform_").toUpperCase());
		this.path = path;
		this.platform = platform;
		loader = new Loader(platform, path);
		this.driverManager = driverManager;
	}

	public IControl getControl(String name) throws Exception {
		return Control.createControl(this, name, getIdentifier(name));
	}

	public List<IControl> getControls(String name) throws Exception {
		return Control.createControls(this, name, getIdentifier(name));
	}

	@Override
	public void takeSnapShot() throws IOException {
		this.driverManager.takeSnapShot();
	}


	@Override
	public void goTo(String url) throws Exception {
		this.driverManager.goTo(url);
	}

	@Override
	public void goBack() throws IOException {
		this.driverManager.goBack();
	}

	@Override
	public void switchToNewWindow() throws IOException {
		this.driverManager.switchToNewWindow();
	}

	@Override
	public void switchToMainWindow() throws IOException {
		this.driverManager.switchToMainWindow();
	}

	@Override
	public void acceptAlert() throws IOException {
		this.driverManager.acceptAlert();
	}

	@Override
	public void scrollUp() throws IOException {
		this.driverManager.scrollUp();
	}

	@Override
	public void scrollUp(int height) throws IOException {
		this.driverManager.scrollUp(height);
	}

	@Override
	public void scrollDown() throws Exception {
		this.driverManager.scrollDown();
	}

	@Override
	public void scrollDown(int height) throws Exception {
		this.driverManager.scrollDown(height);
	}

	@Override
	public IDriverManager getDriverManager() {
		return this.driverManager;
	}

	@Override
	public Platform getPlatform() {
		return this.platform;
	}

	@Override
	public Identifier getIdentifier(String name) {
		return this.loader.getIdentifierByName(name);
	}

	@Override
	public String getTitle() throws Exception {
		return this.driverManager.getTitle();
		
	}

	
}
