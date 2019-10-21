package com.revature.pageobjectmodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContentFinder {
	public String url = "http://localhost:4200/finder";
	public WebDriver driver;
	//Set up user input WebElements for the Page Object Model
	@FindBy(xpath="//input[@name='title']")
	public WebElement titleBox;
	@FindBy(xpath="//*[@id=\"subjectDropDown\"]/div/div/div[2]/input")
	public WebElement loadModulesBox;
	@FindBy(xpath="//*[@id=\"subjectDropDown\"]/div/span[1]")
	public WebElement clearLoadModulesBox;
	@FindBy(xpath="//input[@id='Code']")
	public WebElement codeRadioButton;
	@FindBy(xpath="//input[@id='Document']")
	public WebElement documentRadioButton;
	@FindBy(xpath="//input[@id='Powerpoint']")
	public WebElement powerpointRadioButton;
	@FindBy(xpath="//input[@id='Flagged']")
	public WebElement flaggedRadioButton;
	@FindBy(xpath="//input[@id='All']")
	public WebElement allRadioButton;
	@FindBy(xpath="//button[@id='submitButton']")
	public WebElement submitButton;
	
	public ContentFinder(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	//Interactive methods
	public void inputToTitleBox(String title) {
		this.titleBox.sendKeys(title);
	}
	
	public void inputToLoadModulesBox(String modules) {
		this.loadModulesBox.sendKeys(modules);
	}
	
	public void clearLoadModulesBox() {
		this.clearLoadModulesBox.click();
	}
	
	public void clickCodeRadioButton() {
		Actions actions = new Actions(driver);
		actions.moveToElement(codeRadioButton).click().perform();
	}
}
