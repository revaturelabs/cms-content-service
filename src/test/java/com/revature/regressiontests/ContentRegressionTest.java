package com.revature.regressiontests;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.pageobjectmodel.ContentCreator;

public class ContentRegressionTest {

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
		ContentCreator cc = new ContentCreator(driver);
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
	
	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}