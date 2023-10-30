package com.tutorialsninja.qa.Testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.testpages.AccountPage;
import com.tutorialsninja.qa.testpages.HomePage;
import com.tutorialsninja.qa.testpages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;
//@Listeners(MyTestListener.class)
public class LoginTest extends Base {
	LoginPage loginpage;
	public WebDriver driver;
	AccountPage accountpage;
	

	LoginTest() {
		super();
	}

	@BeforeMethod

	public void setUp() {

		driver = initializeBrowser(prop.getProperty("BrowserName"));
		HomePage homepage = new HomePage(driver);
		loginpage = homepage.navigateToLogin();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1 , dataProvider = "lav")
	public void verifyLoginUsingValidCredentials(String email, String password){
		
		
		accountpage = loginpage.enterLogindetailClickonLogin(email, password);
		
		String ActualHomepageText = accountpage.myAccountText();
		Assert.assertEquals(ActualHomepageText, propdata.getProperty("ExpectedHomepageText"));

	}

	@DataProvider(name = "lav")

	public Object[][] supplyTestData() {

		Object[][] data = Utilities.getDataFromExcel("logindata");
		return data;
	}

	@Test(priority = 2)
	public void verifyLoginUsingInValidCredentials() throws InterruptedException {
		loginpage.enterLogindetailClickonLogin(Utilities.emailTimeStamp(), propdata.getProperty("invalidPassword"));
		
		String ActualNoMatchEamilPasswordError = loginpage.noMatchEmailPasswordErrorText();

		Assert.assertEquals(ActualNoMatchEamilPasswordError, propdata.getProperty("ExpectedNoMatchEamilPasswordError"),
				"No match for E-Mail Address and/or Password message not displayed");

	}

	@Test(priority = 3)
	public void verifyLoginUsingInValidEmailValidPassword() throws InterruptedException {

		loginpage.enterLogindetailClickonLogin(Utilities.emailTimeStamp(), prop.getProperty("validPassword"));

		String ActualNoMatchEamilPasswordError = loginpage.noMatchEmailPasswordErrorText();

		Assert.assertEquals(ActualNoMatchEamilPasswordError, propdata.getProperty("ExpectedNoMatchEamilPasswordError"),
				"No match for E-Mail Address and/or Password message not displayed");

	}

	@Test(priority = 4)
	public void verifyLoginUsingValidEmailInvalidPassword() throws InterruptedException {
		
		loginpage.enterLogindetailClickonLogin(prop.getProperty("validEmail"), propdata.getProperty("invalidPassword"));

		String ActualNoMatchEamilPasswordError = loginpage.noMatchEmailPasswordErrorText();

		Assert.assertEquals(ActualNoMatchEamilPasswordError, propdata.getProperty("ExpectedNoMatchEamilPasswordError"),
				"No match for E-Mail Address and/or Password message not displayed");

	}

	@Test(priority = 5)
	public void verifyLoginUsingWithoutCredentials() throws InterruptedException {

		loginpage.clickOnLoginButton();
		String ActualNoMatchEamilPasswordError = loginpage.noMatchEmailPasswordErrorText();
		Assert.assertEquals(ActualNoMatchEamilPasswordError, propdata.getProperty("ExpectedNoMatchEamilPasswordError"),
				"No match for E-Mail Address and/or Password message not displayed");

	}
}
