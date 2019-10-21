package com.revature.regressiontests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.revature.pageobjectmodel.ContentFinder;

public class ContentFinderRegressionTests {
	
	public static WebDriver driver;
	
	public static String url = "http://localhost:4200/finder";
	public String testTitle = "Test Title";
	public String testLoadModule = "Test Load Module";
	
	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
	}
	
	@AfterClass
	public void tearDown() {
		
	}
	
	@Test(priority=1)
	public void testContentFinderPage() {
		ContentFinder cf = new ContentFinder(driver);
		assertEquals(driver.getCurrentUrl(), cf.url);
	}
	
	@Test(priority=2)
	public void testInputToTitleBox() {
		ContentFinder cf = new ContentFinder(driver);
		WebElement testTitleBox = driver.findElement(By.xpath("//input[@name='title']"));
		assertNotNull(testTitleBox);
		assertEquals(cf.titleBox, testTitleBox);
		cf.inputToTitleBox(testTitle);
		assertEquals(cf.titleBox.getText(),testTitleBox.getText());
		
	}
	
	@Test(priority=3)
	public void testInputToLoadModulesBox() {
		ContentFinder cf = new ContentFinder(driver);
		WebElement testLoadModulesBox = driver.findElement(By.xpath("//*[@id=\"subjectDropDown\"]/div/div/div[2]/input"));
		assertNotNull(testLoadModulesBox);
		assertEquals(cf.loadModulesBox, testLoadModulesBox);
		cf.inputToLoadModulesBox(testLoadModule);
		assertEquals(cf.loadModulesBox.getText(), testLoadModulesBox.getText());
	}
	
	@Test(priority=4)
	public void testClearLoadModulesBox() {
		ContentFinder cf = new ContentFinder(driver);
		WebElement testClearLoadModulesBox = driver.findElement(By.xpath("//*[@id=\"subjectDropDown\"]/div/span[1]"));
		assertNotNull(testClearLoadModulesBox);
		assertEquals(cf.clearLoadModulesBox, testClearLoadModulesBox);
		cf.clearLoadModulesBox();
		assertEquals(cf.loadModulesBox.getText(), "");
	}
	
	@Test(priority=5)
	public void testClickedCodeRadioButton() {
		ContentFinder cf = new ContentFinder(driver);
		WebElement testCodeRadioButton = driver.findElement(By.xpath("//input[@id='Code']"));
		assertNotNull(testCodeRadioButton);
		assertEquals(cf.codeRadioButton, testCodeRadioButton);
		cf.clickCodeRadioButton();
		assertTrue(cf.allRadioButton.isSelected());
	}
}
