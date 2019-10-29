package com.revature.pageobjectmodelTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContentFinder {
	
//	public String url = "http://revature-cms-dev.s3-website-us-east-1.amazonaws.com/finder";
	public static String url = "http://localhost:4200/finder";
	public WebDriver driver;
	//Set up user input WebElements for the Page Object Model
	@FindBy(xpath="//input[@name='title']")
	public WebElement titleBox;
	@FindBy(xpath="//*[@id=\"subjectDropDown\"]/div/div/div[2]/input")
	public WebElement modulesBox;
	@FindBy(xpath="//*[@id=\"subjectDropDown\"]/div/span[1]")
	public WebElement clearModulesBox;
	@FindBy(xpath="//*[@id=\"curriculumDropDown\"]/div/div/div[2]/input")
	public WebElement curriculaBox;
	@FindBy(xpath="//*[@id=\"curriculumDropDown\"]/div/span[1]")
	public WebElement clearCirriculaBox;
	@FindBy(xpath="//*[@id='Code']")
	public WebElement codeButton;
	@FindBy(xpath="//*[@id='Document']")
	public WebElement documentButton;
	@FindBy(xpath="//*[@id='Powerpoint']")
	public WebElement powerpointButton;
	@FindBy(xpath="//*[@id='submitButton']")
	public WebElement submitButton;
	
	/**
	 * Loaded Constructor
	 * 
	 * @param driver WebDriver driver
	 */
	public ContentFinder(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	//Interactive methods
	/**
	 * Input String into inputBox WebElement
	 * 
	 * @param inputBox WebElement inputBox HTML input element
	 * @param inputStr String inputStr to be entered into inputBox HTML input
	 * element
	 */
	public void inputToInputBox(WebElement inputBox, String inputStr) {
		inputBox.sendKeys(inputStr);
	}
	
	/**
	 * Simulate clicking given button WebElement
	 * 
	 * @param button WebElement button to be clicked
	 */
	public void clickButton(WebElement button) {
		button.click();
	}
}
