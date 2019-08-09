package com.revature.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//POM for the Module Creator page
public class ModuleCreatorPage {

	//Finds the subject input box
	@FindBy(xpath="//input[@id='inputText']")
	public WebElement subject;
	
	//Finds the submit button
	@FindBy(xpath="//button[@id='submitButton'}")
	public WebElement submitButton;
	
	//Driver to start finding the web elements
//	public ModuleCreator(webDriver driver) {
//		PageFactory.initElements(driver, this);
//	}
	
	//Method for submitting a subject
	public void submitSubject(String subject) {
		this.subject.sendKeys(subject);
		this.submitButton.click();
	}
	
}
