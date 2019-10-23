package com.revature.regressiontests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.pageobjectmodel.SubmitRequest;


public class SubmitRequestRegressionTest {

	
	public static WebDriver driver;
	
	public static String url = "http://localhost:4200/submit-request";
	public String testTitle = "Submit Request Test";
	public String testDescription = "This is an automated selenium request.";
	public String testLoadModule = "Test Load Module";
	
	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
	}
	
	@AfterClass
	public void tearDown() {
		//driver.quit();
	}
	
	@Test(priority=1)
	public void testContentFinderPage() {
		SubmitRequest sr = new SubmitRequest(driver);
		assertEquals(driver.getCurrentUrl(), sr.url);
	}

	@Test(priority=2)
	public void testInputToTitleBox() {
		SubmitRequest sr = new SubmitRequest(driver);
		WebElement testTitleBox = driver.findElement(By.xpath("//input[@name='title']"));
		assertNotNull(testTitleBox);
		assertEquals(sr.titleBox, testTitleBox);
		sr.inputToTitleBox(testTitle);
		assertEquals(sr.titleBox.getText(),testTitleBox.getText());	
	}
	
	@Test(priority=2)
	public void testInputToDescriptionBox() {
		SubmitRequest sr = new SubmitRequest(driver);
		WebElement testDescriptionBox = driver.findElement(By.xpath("//textarea[@name='description']"));
		assertNotNull(testDescriptionBox);
		assertEquals(sr.descriptionBox, testDescriptionBox);
		sr.inputToDescriptionBox(testDescription);
		assertEquals(sr.descriptionBox.getText(),testDescriptionBox.getText());	
	}
}
