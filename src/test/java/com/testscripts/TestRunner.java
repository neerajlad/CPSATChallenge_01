package com.testscripts;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

	
	public static void main() {
	
		Result result = JUnitCore.runClasses(AllJUnitScriptSuite.class);

	      for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
			
	      System.out.println(result.wasSuccessful());
	}
}
