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
	
	@FindBy(xpath="//button[@id='Code']")
	public WebElement codeButton;
	
	@FindBy(xpath="//button[@id='Document']")
	public WebElement documentButton;
	
	@FindBy(xpath="//button[@id='Powerpoint']")
	public WebElement powerpointButton;
	
	@FindBy(xpath="//button[@id='submitButton']")
	public WebElement submitButton;


	/**
	 * Constructor, used to instantiate the POM
	 * @param driver, instance of the web driver from test file,
	 * driver is used to retrieve all of the web elements
	 */
	public SubmitRequest(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	/**
	 * This method is to input text into the title box...
	 * @param title, the string of the input text
	 */
	public void inputToTitleBox(String title) {
		this.titleBox.sendKeys(title);
	}
	
	/**
	 * This method is to input text into the description box...
	 * @param descr, the string of the input text
	 */
	public void inputToDescriptionBox(String descr) {
		this.descriptionBox.sendKeys(descr);
	}
	
	/**
	 * This method is just to click buttons...
	 * @param button, the web element button to be clicked.
	 */
	public void clickButton(WebElement button) {
		button.click();
	}
}
