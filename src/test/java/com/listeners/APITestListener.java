package com.listeners;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class APITestListener implements ITestListener {

	private static final Logger LOGGER = LogManager.getLogger(APITestListener.class);
	
	
	@Override
	public void onTestStart(ITestResult result) {
		LOGGER.info("****************************************************");
		LOGGER.info("==== Starting the test {} ======",result.getName());
		LOGGER.info("==== Test Class {} ====", result.getMethod().getTestClass());
		LOGGER.info("==== Description {} ====", result.getMethod().getDescription());
		LOGGER.info("==== Groups {} ====", Arrays.toString(result.getMethod().getGroups()));
		LOGGER.info("****************************************************");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		long startTime = result.getStartMillis();
		long endTime = result.getEndMillis();
		long totalDuration = endTime-startTime;
		
		
		LOGGER.info("==== {} Test Passed. ======",result.getName());
		LOGGER.info("==== Total Duration {} ====", totalDuration);
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		LOGGER.error("{} Test Failed!!",result.getName());
		LOGGER.error("Error Message: ",result.getThrowable().getMessage());
		LOGGER.error(result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		LOGGER.info("{} Test Skipped!!",result.getName());
		LOGGER.info(result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		
		LOGGER.info("*************** Starting API Framework ***************");
	}

	@Override
	public void onFinish(ITestContext context) {
		LOGGER.info("*************** FINISH ***************");
	
	}
	

}
