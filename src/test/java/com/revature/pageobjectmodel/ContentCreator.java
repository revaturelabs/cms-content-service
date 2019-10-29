package com.revature.pageobjectmodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContentCreator {

	public WebDriver driver;
	
	// Grab all the user input options for the Page Object Model
	@FindBy(xpath="//input[@id='titleTextBox']")
	public WebElement titleBox;

	@FindBy(xpath="//input[@id='urlTextBox']")
	public WebElement urlBox;
	
	@FindBy(xpath="//input[@id='filter']")
	public WebElement moduleFilter;
	
	@FindBy(xpath="//textarea[@id='exampleFormControlTextarea1']")
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
	 * Create a class representation of the web page.
	 * @param driver of the browser that will be tested.
	 */
	public ContentCreator(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	/**
	 * This method sends input to the Title Box.
	 * @param title, input String to be passed.
	 */
	public void inputToTitleBox(String title) {
		this.titleBox.sendKeys(title);
	}
	/**
	 * This method sends input to the Url Box.
	 * @param url, input String to be passed.
	 */
	public void inputToUrlBox(String url) {
		this.urlBox.sendKeys(url);
	}
	/**
	 * This method sends input to Description Area
	 * @param des, input String to be passed
	 */
	public void inputToDescriptionBox(String des) {
		this.descriptionBox.sendKeys(des);
	}
	/**
	 * This method sends input to the Module Filter
	 * @param module, input String to be passed
	 */
	public void inputToModuleFilterBox(String module) {
		this.moduleFilter.sendKeys(module);
	}
	/**
	 * This method is just to click buttons...
	 * @param button, the web element button to be clicked.
	 */
	public void clickButton(WebElement button) {
		button.click();
	}
}
