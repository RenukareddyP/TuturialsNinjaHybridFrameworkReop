package com.tutorialsninja.qa.testpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	WebDriver driver;
	
	@FindBy(linkText = "iPhone")
	private WebElement SearchProduct;

	@FindBy(xpath = "//input[@id = 'button-search']/following-sibling::p")
	private WebElement SearchMessage;
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifySearchproduct() {
		return SearchProduct.isDisplayed();
	}
	public String verifySearchMessage() {
		return SearchMessage.getText();
	}

}
