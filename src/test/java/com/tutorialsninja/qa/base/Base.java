package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsninja.qa.utils.Utilities;

public class Base {

	WebDriver driver;
	public Properties prop;
	public Properties propdata;

	public Base() {
		prop = new Properties();
		propdata = new Properties();
		File file = new File(System.getProperty("user.dir")
				+ "//src//main//java//com//tutorialsninja//qa//config//config.properties");
		try {
			FileInputStream fis = new FileInputStream(file);
			prop.load(fis);

		} catch (IOException e) {

			e.printStackTrace();
		}
		
		
		File filedata = new File(System.getProperty("user.dir")
				+ "//src//main//java//com//tutorialsninja//qa//testdata//testdata.properties");
		try {
			FileInputStream fisdata = new FileInputStream(filedata);
			propdata.load(fisdata);

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public WebDriver initializeBrowser(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("headless")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			driver = new ChromeDriver(options);
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.implicit_wait_time));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.page_load_time));
		driver.get(prop.getProperty("url"));

		return driver;

	}

}
