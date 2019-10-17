package com.revature.pageobjectmodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContentCreator {

	// Grab all the user input options for the Page Object Model
	@FindBy(xpath="//input[@id='titleTextBox']")
	public WebElement titleBox;
	@FindBy(xpath="//input[@id='urlTextBox']")
	public WebElement urlBox;
	@FindBy(xpath="//[@id='filter']")
	public WebElement filter;
	//@FindBy(xpath="//[@id='descriptionTextBox']")
	@FindBy(xpath="//textarea[@id='exampleFormControlTextarea1']")
	public WebElement descriptionBox;
	@FindBy(xpath="//input[@id='filter']")
	public WebElement moduleFilterBox;
	@FindBy(xpath="//button[@id='submitButton']")
	public WebElement submitButton;
	
	// Instantiate a class of Content Creator with the web elements
	public ContentCreator(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//These methods interact with the Web Elements
	public void inputToTitleBox(String title) {
		this.titleBox.sendKeys(title);
	}
	public void inputToUrlBox(String url) {
		this.urlBox.sendKeys(url);
	}
	public void inputToDescriptionBox(String des) {
		this.descriptionBox.sendKeys(des);
	}
	public void selectSubmitButton() {
		this.submitButton.click();
	}
}
