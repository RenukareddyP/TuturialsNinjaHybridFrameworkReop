package com.tutorialsninja.qa.testpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	
	@FindBy (xpath = "//span[text()='My Account']")
	private WebElement myAccountDropdown;
	
	@FindBy (linkText = "Login")
	private WebElement loginButton;
	
	@FindBy (linkText = "Register")
	private WebElement registerButton;
	
	@FindBy (name = "search")
	private WebElement SearchField; 
	
	@FindBy (xpath = "//div[@id='search']/child::span")
	private WebElement SearchIcon;
	
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public LoginPage navigateToLogin() {
		myAccountDropdown.click();
		loginButton.click();
		return new LoginPage(driver);
	}
	public RegisterPage navigateToRegister() {
		myAccountDropdown.click();
		registerButton.click();
		return new RegisterPage(driver);
	}
	
	public void enterSearchText(String SearchText) {
		SearchField.sendKeys(SearchText);
		
	}
	public SearchPage clickOnSearchIcon() {
		SearchIcon.click();
		return new SearchPage(driver);
		
	}
	public SearchPage searchProduct(String Searchtext) {
		SearchField.sendKeys(Searchtext);
		SearchIcon.click();
		return new SearchPage(driver);
		
		
	}
	
	
}
