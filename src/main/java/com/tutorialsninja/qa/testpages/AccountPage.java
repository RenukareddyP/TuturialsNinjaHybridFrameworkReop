package com.tutorialsninja.qa.testpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
	WebDriver driver;
	
	@FindBy (xpath = "//h2[text()='My Account']")
	private WebElement myAccounttext;
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public String myAccountText() {
		return myAccounttext.getText();
	}

}
