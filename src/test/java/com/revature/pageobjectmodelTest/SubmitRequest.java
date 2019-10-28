package com.revature.pageobjectmodelTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SubmitRequest {

	public WebDriver driver;
	public String url = "http://localhost:4200/submit-request";
	
	@FindBy(xpath="//input[@id='title']")
	public WebElement titleBox;
	
	@FindBy(xpath="//textarea[@name='descrizzption']")
	public WebElement descriptionBox;

	
	public SubmitRequest(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	//Interactive methods
	public void inputToTitleBox(String title) {
		this.titleBox.sendKeys(title);
	}
	
	public void inputToDescriptionBox(String descr) {
		this.descriptionBox.sendKeys(descr);
	}
}
