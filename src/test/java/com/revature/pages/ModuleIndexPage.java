package com.revature.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ModuleIndexPage {
	
	//Find the modules by name
	@FindBy(xpath="//div[@name='modules']")
	public WebElement modules;
	
	//Driver to start finding web elements
//	public ModuleIndex(WebDriver driver) {
//		PageFactory.initElements(driver, this);
//	}
	
	//Method to click on a module and pull up further info
	public void moduleClick() {
		this.modules.click();
	}

}
