package com.revature.pageobjectmodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContentFinder {
	
	@FindBy(xpath="//input[@name='title']")
	public WebElement titleBox;

	public ContentFinder(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void inputToTitleBox(String title) {
		this.titleBox.sendKeys(title);
	}
}
