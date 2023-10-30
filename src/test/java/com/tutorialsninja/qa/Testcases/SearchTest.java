package com.tutorialsninja.qa.Testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.testpages.HomePage;
import com.tutorialsninja.qa.testpages.SearchPage;

public class SearchTest extends Base {

	public WebDriver driver;
	SearchPage searchpage;
	HomePage homepage;

	SearchTest() {
		super();
	}

	@BeforeMethod
	public void setup() {

		driver = initializeBrowser(prop.getProperty("BrowserName"));
		homepage = new HomePage(driver);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		searchpage = homepage.searchProduct(propdata.getProperty("SearchExistingProduct"));
		Assert.assertTrue(searchpage.verifySearchproduct() , "Searching product not displayed");
	}

	@Test(priority = 2)
	public void verifySearchWithInValidProduct() {
		searchpage = homepage.searchProduct(propdata.getProperty("SearchNonExistingProduct"));
		Assert.assertEquals(searchpage.verifySearchMessage(), "hello",
				"Searching product is displayed");
	}

	@Test(priority = 3, dependsOnMethods = {"verifySearchWithValidProduct","verifySearchWithInValidProduct"})
	public void verifySearchWithoutProduct() {
		searchpage = homepage.clickOnSearchIcon();
		Assert.assertEquals(searchpage.verifySearchMessage(), propdata.getProperty("ExpectedSearchMessage"),
				"Searching product is displayed");
	}

}
