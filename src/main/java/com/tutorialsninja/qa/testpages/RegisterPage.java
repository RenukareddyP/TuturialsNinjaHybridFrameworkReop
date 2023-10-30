package com.tutorialsninja.qa.testpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver;
	@FindBy(name = "firstname")
	private WebElement FirstName;
	@FindBy(name = "lastname")
	private WebElement LastName;
	@FindBy(name = "email")
	private WebElement Email;
	@FindBy(name = "telephone")
	private WebElement Telephone;
	@FindBy(name = "password")
	private WebElement Password;
	@FindBy(name = "confirm")
	private WebElement ConfirmPassword;
	@FindBy(name = "agree")
	private WebElement SelectAgree;
	@FindBy(xpath = "//input[@value = 'Continue']")
	private WebElement Continue;
	@FindBy(linkText = "Continue")
	private WebElement ContinueButton;
	@FindBy(xpath = "//input[@name = 'newsletter' and @value = '1']")
	private WebElement NewsLetter;
	@FindBy(xpath = "//div[contains(@class , 'alert-dismissible')]")
	private WebElement ExsitingEmailError;
	@FindBy(xpath = "//input[@name= 'firstname']/following-sibling::div")
	private WebElement FirstNameError;

	@FindBy(xpath = "//input[@name= 'lastname']/following-sibling::div")
	private WebElement LastNameError;

	@FindBy(xpath = "//input[@name= 'email']/following-sibling::div")
	private WebElement EmailError;

	@FindBy(xpath = "//input[@name= 'telephone']/following-sibling::div")
	private WebElement TelephoneError;

	@FindBy(xpath = "//input[@name= 'password']/following-sibling::div")
	private WebElement PasswordError;

	@FindBy(xpath = "//div[contains(@class , 'alert-dismissible')]")
	private WebElement CheckBoxError;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterFirstName(String firstName) {
		FirstName.sendKeys(firstName);
	}

	public void enterLastName(String lastName) {
		LastName.sendKeys(lastName);
	}

	public void enterEmail(String email) {
		Email.sendKeys(email);
	}

	public void enterTelephone(String telephone) {
		Telephone.sendKeys(telephone);
	}

	public void enterPassword(String password) {
		Password.sendKeys(password);
	}

	public void enterConfirmPassword(String confirmPassword) {
		ConfirmPassword.sendKeys(confirmPassword);
	}

	public void clickOnAgree() {
		SelectAgree.click();
	}

	public void clickOnContinue() {
		Continue.click();
	}

	public AccountPage clickOnContinueButton() {
		ContinueButton.click();
		return new AccountPage(driver);
	}

	public void selectNewsLetter() {
		NewsLetter.click();
	}
	public String exsitingEmailErrorText() {
		return ExsitingEmailError.getText();
	}

	public String firstNameErrorText() {
		return FirstNameError.getText();
	}
	public String lastNameErrorText() {
		return LastNameError.getText();
	}
	public String emailErrorText() {
		return EmailError.getText();
	}
	public String telephoneErrorText() {
		return TelephoneError.getText();
	}
	public String passwordErrorText() {
		return PasswordError.getText();
	}
	public String checkBoxErrorText() {
		return CheckBoxError.getText();
	}
	
	public AccountPage enterMandatoryRegisterFields(String firstName, String lastName, String email, String telephone, String password, String confirmPassword  ) {
		FirstName.sendKeys(firstName);
		LastName.sendKeys(lastName);
		Email.sendKeys(email);
		Telephone.sendKeys(telephone);
		Password.sendKeys(password);
		ConfirmPassword.sendKeys(confirmPassword);
		SelectAgree.click();
		Continue.click();
		ContinueButton.click();
		return new AccountPage(driver);
	}
	
	public void enterAllRegisterFields(String firstName, String lastName, String email, String telephone, String password, String confirmPassword  ) {
		FirstName.sendKeys(firstName);
		LastName.sendKeys(lastName);
		Email.sendKeys(email);
		Telephone.sendKeys(telephone);
		Password.sendKeys(password);
		ConfirmPassword.sendKeys(confirmPassword);
		NewsLetter.click();
		SelectAgree.click();
		Continue.click();
		
	}
	
	public boolean verifyErrorsOnRegister(String ExpectedFirstNameError, String ExpectedLastNameError, String ExpectedEmailError, String ExpectedTelephoneError, String ExpectedPasswordError, String ExpectedAgreeCheckboxError ) {
		boolean FirstNameErrorStatus = ExpectedFirstNameError.contains(FirstNameError.getText());
		boolean LastNameErrorStatus = ExpectedLastNameError.contains(LastNameError.getText());
		boolean EmailErrorStatus = ExpectedEmailError.contains(EmailError.getText());
		boolean TelephoneErrorStatus = ExpectedTelephoneError.contains(TelephoneError.getText());
		boolean PasswordErrorStatus = ExpectedPasswordError.contains(PasswordError.getText());
		boolean AgreeCheckboxErrorStatus = ExpectedAgreeCheckboxError.contains(CheckBoxError.getText());
		
		return FirstNameErrorStatus && LastNameErrorStatus && EmailErrorStatus && TelephoneErrorStatus && PasswordErrorStatus && AgreeCheckboxErrorStatus;

	}
	
	
	

}
