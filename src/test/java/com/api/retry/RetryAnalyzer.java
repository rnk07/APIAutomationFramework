package com.api.retry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	private static final Logger LOGGER = LogManager.getLogger(RetryAnalyzer.class);
	private static final int MAX_ATEEMPTS =2;
	private int count =1;
	
	@Override
	public boolean retry(ITestResult result) {
		LOGGER.info("Checking if test {} can be reexecuted",result.getName());
		if(count <=MAX_ATEEMPTS) {
			LOGGER.warn("Executing the {} test, Current Attempt Number {}/{}, REASON {}", result.getName(), count,MAX_ATEEMPTS, result.getThrowable().getMessage());
			count++;
			return true;
		}
		
		return false;
	}

}
