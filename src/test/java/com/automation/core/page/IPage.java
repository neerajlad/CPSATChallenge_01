package com.automation.core.page;

import java.util.List;

import com.automation.core.control.IControl;
import com.automation.core.loader.Identifier;
import com.automation.core.managers.IDriverManager;
import com.automation.core.managers.IWebActions;
import com.automation.core.managers.platform.Platform;


public interface IPage extends IWebActions{
	IControl getControl(String name) throws Exception;
	List<IControl> getControls(String name) throws Exception;
	IDriverManager getDriverManager();
	Platform getPlatform();
	Identifier getIdentifier(String name);
}
