package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;
import com.tutorialsninja.qa.utils.Utilities;

public class MyTestListener implements ITestListener{
	
	public ExtentReports extentReports;
	public ExtentTest extenttest;
	

	@Override
	public void onTestStart(ITestResult result) {
		
		extenttest = extentReports.createTest(result.getName());
		extenttest.log(Status.INFO, result.getName()+ " got successfully started.");
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		extenttest.log(Status.PASS, result.getName()+ " got successed." );
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		String destinationpath = Utilities.captureScreenshot(driver, result.getName());
		extenttest.addScreenCaptureFromPath(destinationpath);
		extenttest.log(Status.FAIL, result.getThrowable());
		extenttest.log(Status.FAIL, result.getName()+ " got faild.");
	}										

	@Override
	public void onTestSkipped(ITestResult result){
		
		extenttest.log(Status.SKIP, result.getName()+ " got skipped.");
		System.out.println(result.getThrowable());
	}

	@Override
	public void onStart(ITestContext context) {
		 extentReports = ExtentReporter.generateExtentReport();
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReports.flush();
		
		File extentreportpath = new File(System.getProperty("user.dir")+"//test-output//extentreports//extentreport.html");
		try {
			Desktop.getDesktop().browse(extentreportpath.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
