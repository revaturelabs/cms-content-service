package com.revature.regressiontests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

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
	public void testTitleBox() {
		ContentCreator cc = new ContentCreator(driver);
		WebElement titleBox = driver.findElement(By.id("titleTextBox"));
		assertNotNull(titleBox);
		assertEquals(cc.titleBox, titleBox);
		cc.inputToTitleBox("Testing the selenium title box test");
		assertEquals(cc.titleBox.getText(), titleBox.getText());
	}
	@Test
	public void testUrlBox() {
		ContentCreator cc = new ContentCreator(driver);
		WebElement urlBox = driver.findElement(By.id("urlTextBox"));
		assertNotNull(urlBox);
		assertEquals(cc.urlBox, urlBox);
		cc.inputToUrlBox("http://www.testnumber2.com");
		assertEquals(cc.urlBox.getText(), urlBox.getText());
	}
	@Test
	public void testModuleSearchBox() {
		ContentCreator cc = new ContentCreator(driver);
		WebElement moduleFilterBox = driver.findElement(By.id("filter"));
		assertNotNull(moduleFilterBox);
		assertEquals(cc.moduleFilter, moduleFilterBox);
		cc.inputToModuleFilterBox("The Big Downs");
		assertEquals(cc.moduleFilter.getText(), moduleFilterBox.getText());
	}
	@Test
	public void testModuleResultClickable() {
		driver.findElement(By.xpath("//*[@id=\"tree\"]/tree-viewport/div/div/tree-node-collection/div/tree-node/div/tree-node-wrapper/div/div")).click();
		driver.findElement(By.xpath("//*[@id=\"tree\"]/tree-viewport/div/div/tree-node-collection/div/tree-node/div/tree-node-wrapper/div/div")).isSelected();
	}
	@Test
	public void testDescriptionBox() {
		ContentCreator cc = new ContentCreator(driver);
		WebElement descriptionBox = driver.findElement(By.id("exampleFormControlTextarea1"));
		assertNotNull(descriptionBox);
		assertEquals(cc.descriptionBox, descriptionBox);
		cc.inputToDescriptionBox("A quality description would go here");
		assertEquals(cc.descriptionBox.getText(), descriptionBox.getText());
	}
	@Test
	public void testCodeRadioButton() {
		Actions actions = new Actions(driver);
		WebElement radio = driver.findElement(By.id("Code"));
		actions.moveToElement(radio).click().perform();
		assertTrue(radio.isSelected());
	}
	@Test
	public void testDocumentRadioButton() {
		Actions actions = new Actions(driver);
		WebElement radio = driver.findElement(By.id("Document"));
		actions.moveToElement(radio).click().perform();
		assertTrue(radio.isSelected());
	}
	@Test
	public void testPowerpointRadioButton() {
		Actions actions = new Actions(driver);
		WebElement radio = driver.findElement(By.id("Powerpoint"));
		actions.moveToElement(radio).click().perform();
		assertTrue(radio.isSelected());
	}
	@Test
	public void testSubmitButtonCheck() {
		driver.findElement(By.id("submitButton")).click();
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}