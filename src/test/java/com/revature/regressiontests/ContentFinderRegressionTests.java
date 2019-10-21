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
	
	/**
	 * Set up chrome webdriver and navigate to ContentFinder page
	 */
	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
	}
	
	/**
	 * Close connection and close browser
	 */
	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}
	
	/**
	 * Test if the driver url is equal to the POM url
	 */
	@Test(priority=1)
	public void testContentFinderPage() {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Check if driver url is equal to the POM url
		assertEquals(driver.getCurrentUrl(), cf.url);
	}
	
	/**
	 * Test input String into POM titleBox WebElement
	 */
	@Test(priority=2)
	public void testInputToTitleBox() {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Create a reference to the WebElement under test
		WebElement testTitleBox = driver.findElement(By.xpath("//input[@name='title']"));
		//Check if WebElement under test is not null
		assertNotNull(testTitleBox);
		//Check if WebElement under test and POM WebElement under test are equal
		assertEquals(cf.titleBox, testTitleBox);
		//Perform POM WebElement action
		cf.inputToTitleBox(testTitle);
		//Check if POM WebElement action succeed
		assertEquals(cf.titleBox.getText(),testTitleBox.getText());
		
	}
	/**
	 * Test input String into POM loadModulesBox WebElement
	 */
	@Test(priority=3)
	public void testInputToLoadModulesBox() {
		ContentFinder cf = new ContentFinder(driver);
		WebElement testLoadModulesBox = driver.findElement(By.xpath("//*[@id=\"subjectDropDown\"]/div/div/div[2]/input"));
		assertNotNull(testLoadModulesBox);
		assertEquals(cf.loadModulesBox, testLoadModulesBox);
		cf.inputToLoadModulesBox(testLoadModule);
		assertEquals(cf.loadModulesBox.getText(), testLoadModulesBox.getText());
	}
	
	/**
	 * Test clearing POM loadModulesBox WebElement
	 */
	@Test(priority=4)
	public void testClearLoadModulesBox() {
		ContentFinder cf = new ContentFinder(driver);
		WebElement testClearLoadModulesBox = driver.findElement(By.xpath("//*[@id=\"subjectDropDown\"]/div/span[1]"));
		assertNotNull(testClearLoadModulesBox);
		assertEquals(cf.clearLoadModulesBox, testClearLoadModulesBox);
		cf.clearLoadModulesBox();
		assertEquals(cf.loadModulesBox.getText(), "");
	}
	
	/**
	 * Test selecting POM codeRadioButton WebElement
	 */
	@Test(priority=5)
	public void testClickedCodeRadioButton() {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Create a reference to the WebElement under test
		WebElement testCodeRadioButton = driver.findElement(By.xpath("//input[@id='Code']"));
		//Check if WebElement under test is not null
		assertNotNull(testCodeRadioButton);
		//Check if WebElement under test and POM WebElement under test are equal
		assertEquals(cf.codeRadioButton, testCodeRadioButton);
		//Perform POM WebElement action
		cf.clickRadioButton(cf.codeRadioButton);
		//Check if POM WebElement action succeed
		assertTrue(cf.codeRadioButton.isSelected());
	}
	/**
	 * Test selecting POM documentRadioButton WebElement
	 */
	@Test(priority=6)
	public void testClickedDocumentRadioButton() {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Create a reference to the WebElement under test
		WebElement testDocumentRadioButton = driver.findElement(By.xpath("//input[@id='Document']"));
		//Check if WebElement under test is not null
		assertNotNull(testDocumentRadioButton);
		//Check if WebElement under test and POM WebElement under test are equal
		assertEquals(cf.documentRadioButton, testDocumentRadioButton);
		//Perform POM WebElement action
		cf.clickRadioButton(cf.documentRadioButton);
		//Check if POM WebElement action succeed
		assertTrue(cf.documentRadioButton.isSelected());
	}
	
	/**
	 * Test selecting POM powerpointRadioButton WebElement
	 */
	@Test(priority=7)
	public void testClickedPowerpointRadioButton() {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Create a reference to the WebElement under test
		WebElement testPowerpointRadioButton = driver.findElement(By.xpath("//input[@id='Powerpoint']"));
		//Check if WebElement under test is not null
		assertNotNull(testPowerpointRadioButton);
		//Check if WebElement under test and POM WebElement under test are equal
		assertEquals(cf.powerpointRadioButton, testPowerpointRadioButton);
		//Perform POM WebElement action
		cf.clickRadioButton(cf.powerpointRadioButton);
		//Check if POM WebElement action succeed
		assertTrue(cf.powerpointRadioButton.isSelected());
	}
	
	/**
	 * Test selecting POM flaggedRadioButton WebElement
	 */
	@Test(priority=8)
	public void testClickedFlaggedRadioButton() {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Create a reference to the WebElement under test
		WebElement testFlaggedRadioButton = driver.findElement(By.xpath("//input[@id='Flagged']"));
		//Check if WebElement under test is not null
		assertNotNull(testFlaggedRadioButton);
		//Check if WebElement under test and POM WebElement under test are equal
		assertEquals(cf.flaggedRadioButton, testFlaggedRadioButton);
		//Perform POM WebElement action
		cf.clickRadioButton(cf.flaggedRadioButton);
		//Check if POM WebElement action succeed
		assertTrue(cf.flaggedRadioButton.isSelected());
	}
	/**
	 * Test selecting POM allRadioButton WebElement
	 */
	@Test(priority=9)
	public void testClickedAllRadioButton() {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Create a reference to the WebElement under test
		WebElement testAllRadioButton = driver.findElement(By.xpath("//input[@id='All']"));
		//Check if WebElement under test is not null
		assertNotNull(testAllRadioButton);
		//Check if WebElement under test and POM WebElement under test are equal
		assertEquals(cf.allRadioButton, testAllRadioButton);
		//Perform POM WebElement action
		cf.clickRadioButton(cf.allRadioButton);
		//Check if POM WebElement action succeed
		assertTrue(cf.allRadioButton.isSelected());
	}
	
	/**
	 * Test clicking the POM submitButton WebElement
	 */
	public void testClickedSubmitButton() {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Create a reference to the WebElement under test
		WebElement testSubmitButton = driver.findElement(By.xpath("//button[@id='submitButton']"));
		//Check if WebElement under test is not null
		assertNotNull(testSubmitButton);
		//Check if WebElement under test and POM WebElement under test are equal
		assertEquals(cf.submitButton, testSubmitButton);
		//Perform POM WebElment action
		cf.clickSubmitButton();
		assertTrue(cf.submitButton.isSelected());
		//Create a reference to the WebElement toastr pop up
		//Check css property opacity value is equal to 1
		
	}
}
