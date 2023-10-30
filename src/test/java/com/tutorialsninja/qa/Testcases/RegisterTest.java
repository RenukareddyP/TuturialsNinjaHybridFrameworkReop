package com.tutorialsninja.qa.Testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.testpages.AccountPage;
import com.tutorialsninja.qa.testpages.HomePage;
import com.tutorialsninja.qa.testpages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base {

	public WebDriver driver;
	RegisterPage registerpage;
	AccountPage accountpage;

	RegisterTest() {
		super();
	}

	@BeforeMethod

	public void setUp() {

		driver = initializeBrowser(prop.getProperty("BrowserName"));
		HomePage homepage = new HomePage(driver);
		registerpage = homepage.navigateToRegister();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)

	public void verifyRegisterProvidingMandatoryFields() {
		accountpage = registerpage.enterMandatoryRegisterFields(propdata.getProperty("FirstName"), propdata.getProperty("LastName"), Utilities.emailTimeStamp(), propdata.getProperty("TelephoneNumber"), propdata.getProperty("Password"), propdata.getProperty("ConfirmPassword"));
		Assert.assertEquals(accountpage.myAccountText(), propdata.getProperty("ExpectedHomepageText"), "MSyAccount Text not displayed");

	}

	@Test(priority = 2)
	public void verifyRegisterProvidingAllFields() {
	
		registerpage.enterAllRegisterFields(propdata.getProperty("FirstName"), propdata.getProperty("LastName"), Utilities.emailTimeStamp(), propdata.getProperty("TelephoneNumber"), propdata.getProperty("Password"), propdata.getProperty("ConfirmPassword"));
		accountpage = registerpage.clickOnContinueButton();
		Assert.assertEquals(accountpage.myAccountText(), propdata.getProperty("ExpectedHomepageText"), "MSyAccount Text not displayed");

	}

	@Test(priority = 3)
	public void verifyRegisterProvidingExistinEmail() {
		registerpage.enterAllRegisterFields(propdata.getProperty("FirstName"), propdata.getProperty("LastName"), prop.getProperty("validEmail"), propdata.getProperty("TelephoneNumber"), propdata.getProperty("Password"), propdata.getProperty("ConfirmPassword"));
		Assert.assertTrue(registerpage.exsitingEmailErrorText().contains(propdata.getProperty("ExpectedExsitingEmailError")),
				"Duplicate email address error message not displaying");

	}

	@Test(priority = 4)
	public void verifyRegisterWithoutProvidingAnyDetails() {

		registerpage.clickOnContinue();
		Assert.assertTrue(registerpage.verifyErrorsOnRegister(propdata.getProperty("ExpectedFirstNameError"), propdata.getProperty("ExpectedLastNameError"), propdata.getProperty("ExpectedemailError"), propdata.getProperty("ExpectedTelephoneError"), propdata.getProperty("ExpectedpasswordError"), propdata.getProperty("ExpectedAgreeCheckboxError")), "All errors not displayed ");

	}

}
