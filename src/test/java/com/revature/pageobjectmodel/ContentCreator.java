package com.revature.pageobjectmodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContentCreator {
	
	public final String title = "CMS Force";

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
	@FindBy(xpath="//[@id='Code']")
	public WebElement codeRadioButton;
	@FindBy(xpath="//[@id='Document']")
	public WebElement documentRadioButton;
	@FindBy(xpath="//[@id='Powerpoint']")
	public WebElement powerpointRadioButton;
	@FindBy(xpath="//[@id='submitButton']")
	public WebElement submitButton;
	
	// Instantiate a class of Content Creator with the web elements
	public ContentCreator(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	// Methods to push input to the text boxes and text area
	public void inputToTitleBox(String title) {
		this.titleBox.sendKeys(title);
	}
	public void inputToUrlBox(String url) {
		this.urlBox.sendKeys(url);
	}
	public void inputToDescriptionBox(String des) {
		this.descriptionBox.sendKeys(des);
	}
}
