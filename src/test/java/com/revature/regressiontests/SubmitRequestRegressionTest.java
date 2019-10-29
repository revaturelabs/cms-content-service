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

/**
 * @batch #643 - Christina Russ - UTA branch
 * @author tyler jankowski
 * @purpose this class is used to perform tests on the Submit Request page of the CMS-Force application.
 * It uses Selenium with TestNG to perform these tests. Selenium is used to act as a real end user.
 *
 *@date 10/29/2019
 */
public class SubmitRequestRegressionTest {

	
	public static WebDriver driver;
	
//	public static String url = "http://revature-cms-dev.s3-website-us-east-1.amazonaws.com/submit-request";
	public String testTitle = "Submit Request Test";
	public static String url = "localhost:4200";
	public String testDescription = "This is an automated selenium request.";
	public String testLoadModule = "Test Load Module";
	
	/**
	 * @purpose used to set up the test class before any tests are run
	 */
	@BeforeClass
	public void setup() {
		// specifies the type and location of the selenium driver used
		System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
		// sets up an instance of the dirver object which should be the same as the type of executable
		driver = new ChromeDriver();
		// get() is used to 1) load a new web browser and 2) navigate to the supplied URL
		driver.get(url);
	}
	
	@Test(priority=1)
	public void testSubmitWithNoModule() {
		
	}
	
	@Test(priority=2) 
	public void testSubmitCodeRequest() {
		// validate we are indeed on the correct page / URL
		assertEquals(driver.getCurrentUrl(), url);
		
		// create an instance of our Content Creator POM to use the defined web elements
		SubmitRequest sr = new SubmitRequest(driver);
		
		// create local instance of the various input boxes & buttons
		WebElement titleBox = driver.findElement(By.id("titleTextBox"));
		WebElement urlBox = driver.findElement(By.id("urlTextBox"));
		WebElement moduleFilterBox = driver.findElement(By.id("filter"));
		WebElement descriptionBox = driver.findElement(By.id("exampleFormControlTextarea1"));
	}
	
	@Test(priority=3)
	public void testSubmitDocumentRequest() {
		
	}
	
	@Test(priority=4)
	public void testSubmitPowerpointRequest() {
		
	}
	
	
	
	/**
	 * @purpose used to deconstruct the class fields after all tests are completed
	 */
	@AfterClass
	public void tearDown() {
		// used to stop the current driver stream
		driver.close();
		// shuts down the driver and closes the web browser
		driver.quit();
	}
	
	
	
}
