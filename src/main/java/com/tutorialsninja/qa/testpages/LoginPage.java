package com.tutorialsninja.qa.testpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	@FindBy(name = "email")
	private WebElement emailField;
	@FindBy(name = "password")
	private WebElement passwordField;
	@FindBy(xpath = "//input[@type='submit']")
	private WebElement loginButton;
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	WebElement EamilPasswordError;
	public LoginPage (WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public AccountPage enterLogindetailClickonLogin(String email, String password) {
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		loginButton.click();
		return new AccountPage(driver);
	}
	public AccountPage clickOnLoginButton() {
		loginButton.click();
		return new AccountPage(driver);
	}
	public String noMatchEmailPasswordErrorText() {
		return EamilPasswordError.getText();
	}
	
	

}
