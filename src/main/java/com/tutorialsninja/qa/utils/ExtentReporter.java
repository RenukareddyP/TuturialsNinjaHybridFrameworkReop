package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	public static ExtentReports generateExtentReport() {
		File configfile = new File(System.getProperty("user.dir")
				+ "//src//main//java//com//tutorialsninja//qa//config//config.properties");
		Properties configprop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(configfile);
			configprop.load(fis);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		ExtentReports extentreport = new ExtentReports();
		File file = new File(System.getProperty("user.dir") + "//test-output//extentreports//extentreport.html");

		ExtentSparkReporter sparkreporter = new ExtentSparkReporter(file);
		sparkreporter.config().setTheme(Theme.DARK);
		sparkreporter.config().setReportName("TutorialsNinja Automation Test Report");
		sparkreporter.config().setDocumentTitle("TutorialsNinja Results");
		sparkreporter.config().setTimeStampFormat("dd/MM/YYYY hh:mm:ss");
		extentreport.attachReporter(sparkreporter);

		extentreport.setSystemInfo("Appication url" , configprop.getProperty("url"));
		extentreport.setSystemInfo("Browser Name", configprop.getProperty("BrowserName"));
		extentreport.setSystemInfo("Email Address", configprop.getProperty("validEmail"));
		extentreport.setSystemInfo("Password", configprop.getProperty("validPassword"));
		extentreport.setSystemInfo("Java Version",System.getProperty("java.specification.version"));
		extentreport.setSystemInfo("Operating System",System.getProperty("os.name"));
		extentreport.setSystemInfo("User Name",System.getProperty("user.name"));

		return extentreport;
		

		
	}

}
