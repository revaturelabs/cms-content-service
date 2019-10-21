package com.revature.regressiontests;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.pageobjectmodel.ContentCreator;

public class ContentCreatorRegressionTests {

	public static WebDriver driver;
	
	public static String url = "http://localhost:4200/content-creator";
	
	@BeforeClass
	public void setUP() {
		System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
	}
	
	@Test
	public void confirmedContentCreatorPage() {
		assertEquals(driver.getCurrentUrl(), url);
	}
	@Test
	public void checkTitleBox() {
		ContentCreator cc = new ContentCreator(driver);
		cc.inputToTitleBox("Testing the selenium title box test");
		assertEquals(driver.findElement(By.id("titleTextBox")), cc.titleBox);
	}
	@Test
	public void checkUrlBox() {
		ContentCreator cc = new ContentCreator(driver);
		cc.inputToUrlBox("http://www.testnumber2.com");
		assertEquals(driver.findElement(By.id("urlTextBox")), cc.urlBox);
	}
	@Test
	public void checkDescriptionBox() {
		ContentCreator cc = new ContentCreator(driver);
		cc.inputToDescriptionBox("A quality description would go here");
		assertEquals(driver.findElement(By.id("exampleFormControlTextarea1")), cc.descriptionBox);
	}
	@Test
	public void checkCodeRadioButton() {
		Actions actions = new Actions(driver);
		WebElement radio = driver.findElement(By.id("Code"));
		actions.moveToElement(radio).click().perform();
		driver.findElement(By.id("Code")).isSelected();
	}
	@Test
	public void checkDocumentRadioButton() {
		Actions actions = new Actions(driver);
		WebElement radio = driver.findElement(By.id("Document"));
		actions.moveToElement(radio).click().perform();
		driver.findElement(By.id("Document")).isSelected();
	}
	@Test
	public void checkPowerpointRadioButton() {
		Actions actions = new Actions(driver);
		WebElement radio = driver.findElement(By.id("Powerpoint"));
		actions.moveToElement(radio).click().perform();
		driver.findElement(By.id("Powerpoint")).isSelected();
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}