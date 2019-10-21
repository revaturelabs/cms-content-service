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
	/**
	 * Input String into titleBox WebElement
	 * 
	 * @param title String title to be entered into titleBox HTML input element
	 */
	public void inputToTitleBox(String title) {
		this.titleBox.sendKeys(title);
	}
	/**
	 * Input String into LoadModulesBox WebElement
	 * 
	 * @param modules String modules to be entered into loadModulesBox HTML 
	 * input element
	 */
	public void inputToLoadModulesBox(String modules) {
		this.loadModulesBox.sendKeys(modules);
	}
	
	/**
	 * Simulate clicking the clear loadModulesBox cross WebElement
	 */
	public void clearLoadModulesBox() {
		this.clearLoadModulesBox.click();
	}
	
	/**
	 * Simulate clicking the Code Radio Button option WebElement
	 */
	public void clickCodeRadioButton() {
		Actions actions = new Actions(driver);
		actions.moveToElement(codeRadioButton).click().perform();
	}
	
	/**
	 * Simulate clicking the Document Radio Button option WebElement
	 */
	public void clickDocumentRadioButton() {
		Actions actions = new Actions(driver);
		actions.moveToElement(documentRadioButton).click().perform();
	}
	
	/**
	 * Simulate clicking the Powerpoint Radio Button option WebElement
	 */
	public void clickPowerpointRadioButton() {
		Actions actions = new Actions(driver);
		actions.moveToElement(powerpointRadioButton).click().perform();
	}
}
