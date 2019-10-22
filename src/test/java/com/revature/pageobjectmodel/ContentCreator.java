package com.revature.pageobjectmodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContentCreator {

	public WebDriver driver;
	
	// Grab all the user input options for the Page Object Model
	@FindBy(xpath="//input[@id='titleTextBox']")
	public WebElement titleBox;
	@FindBy(xpath="//input[@id='urlTextBox']")
	public WebElement urlBox;
	@FindBy(xpath="//*[@id='filter']")
	public WebElement moduleFilter;
	@FindBy(xpath="//textarea[@id='exampleFormControlTextarea1']")
	public WebElement descriptionBox;
	@FindBy(xpath="//*[@id=\"Code\"]")
	public WebElement codeButton;
	@FindBy(xpath="//*[@id=\"Document\"]")
	public WebElement documentButton;
	@FindBy(xpath="//*[@id=\"Powerpoint\"]")
	public WebElement powerpointButton;
	@FindBy(xpath="//*[@id=\"submitButton\"]")
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
	 * This method sends input to the Title Box of the web page.
	 * @param title, input String to be passed.
	 */
	public void inputToTitleBox(String title) {
		this.titleBox.sendKeys(title);
	}
	/**
	 * 
	 * @param url
	 */
	public void inputToUrlBox(String url) {
		this.urlBox.sendKeys(url);
	}
	/**
	 * 
	 * @param des
	 */
	public void inputToDescriptionBox(String des) {
		this.descriptionBox.sendKeys(des);
	}
	/**
	 * 
	 * @param module
	 */
	public void inputToModuleFilterBox(String module) {
		this.moduleFilter.sendKeys(module);
	}
	/**
	 * 
	 * @param button
	 */
	public void clickButton(WebElement button) {
		button.click();
	}
}
