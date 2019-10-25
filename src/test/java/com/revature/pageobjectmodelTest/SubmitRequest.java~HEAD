package com.revature.pageobjectmodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SubmitRequest {

	public WebDriver driver;
	public String url = "http://revature-cms-dev.s3-website-us-east-1.amazonaws.com/submit-request";
	
	@FindBy(xpath="//input[@id='title']")
	public WebElement titleBox;
	
	@FindBy(xpath="//textarea[@name='description']")
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
