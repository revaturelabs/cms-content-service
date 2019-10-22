package com.revature.regressiontests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
	/**
	 * Test to ensure the correct page was opened
	 */
	public void confirmedContentCreatorPage() {
		assertEquals(driver.getCurrentUrl(), url);
	}
	@Test
	/**
	 * Test to ensure text can be put into the title text box
	 */
	public void testTitleBox() {
		ContentCreator cc = new ContentCreator(driver);
		// Find the titleTextBox with xpath
		WebElement titleBox = driver.findElement(By.xpath("//*[@id=\"titleTextBox\"]"));
		// Check to make sure the Web Element is in fact there
		assertNotNull(titleBox);
		// Just a quick check to make sure the page factory can find the same element
		assertEquals(cc.titleBox, titleBox);
		// Send text input to the title text box
		cc.inputToTitleBox("Testing the selenium title box test");
		// Check that the text got sent to the correct box
		assertEquals(cc.titleBox.getText(), titleBox.getText());
	}
	@Test
	/**
	 * Test to ensure text can be put into the url text box
	 */
	public void testUrlBox() {
		ContentCreator cc = new ContentCreator(driver);
		// Find the url text box with xpath
		WebElement urlBox = driver.findElement(By.xpath("//*[@id=\"urlTextBox\"]"));
		// Check to make sure the Web Element is in fact there
		assertNotNull(urlBox);
		// Just a quick check to make sure the page factory can find the same element
		assertEquals(cc.urlBox, urlBox);
		// Send text input to the url text box
		cc.inputToUrlBox("http://www.testnumber2.com");
		// Check that the text got sent to the correct box
		assertEquals(cc.urlBox.getText(), urlBox.getText());
	}
	@Test
	/**
	 * Test to ensure text can be put into the module filter text box
	 */
	public void testModuleSearchBox() {
		ContentCreator cc = new ContentCreator(driver);
		// Find the module filter text box with xpath
		WebElement moduleFilterBox = driver.findElement(By.xpath("//*[@id=\"filter\"]"));
		// Check to make sure the Web Element is in fact there
		assertNotNull(moduleFilterBox);
		// Just a quick check to make sure the page factory can find the same element
		assertEquals(cc.moduleFilter, moduleFilterBox);
		// Send text input to the module filter text box
		cc.inputToModuleFilterBox("The Big Downs");
		// Check that the text got sent to the correct box
		assertEquals(cc.moduleFilter.getText(), moduleFilterBox.getText());
	}
	/**
	 * Test that the modules are click-able
	 */
	@Test(enabled=false)
	public void testModuleResultClickable() {
		driver.findElement(By.xpath("")).click();
		driver.findElement(By.xpath("")).isSelected();
	}
	@Test
	/**
	 * Test to ensure text can be put into the description text area
	 */
	public void testDescriptionBox() {
		ContentCreator cc = new ContentCreator(driver);
		// Find the description text area with xpath
		WebElement descriptionBox = driver.findElement(By.xpath("//*[@id=\"exampleFormControlTextarea1\"]"));
		// Check to make sure the Web Element is in fact there
		assertNotNull(descriptionBox);
		// Just a quick check to make sure the page factory can find the same element
		assertEquals(cc.descriptionBox, descriptionBox);
		// Send text input to the description text area
		cc.inputToDescriptionBox("A quality description would go here");
		// Check that the text got sent to the correct box
		assertEquals(cc.descriptionBox.getText(), descriptionBox.getText());
	}
	@Test
	/**
	 * Test the code button
	 */
	public void testCodeButton() {
		ContentCreator cc = new ContentCreator(driver);
		// Find the code button with xpath
		WebElement codeButton = driver.findElement(By.xpath("//*[@id=\"Code\"]"));
		// Check to make sure the web element is there
		assertNotNull(codeButton);
		// Just a quick check to make sure the page factory can find the same element
		assertEquals(cc.codeButton, codeButton);
		// Store the class attribute into a variable before the click is pressed
		String preClickClass = codeButton.getAttribute("class");
		// Click the code button
		codeButton.click();
		// Compare the preclick value with the class attribute after the button is pressed.
		// Because code button is selected on page load, these two values will actually be the same
		assertEquals(preClickClass, codeButton.getAttribute("class"));
		// When pressed the class attribute should contain the string "btn-primary"
		assertTrue(codeButton.getAttribute("class").contains("btn-primary"));
		// The document button should NOT contain the string "btn-primary"
		assertFalse(cc.documentButton.getAttribute("class").contains("btn-primary"));
		// The powerpoint button should NOT contain the string "btn-primary"
		assertFalse(cc.powerpointButton.getAttribute("class").contains("btn-primary"));
	}
	@Test
	/**
	 * Test the document button
	 */
	public void testDocumentButton() {
		ContentCreator cc = new ContentCreator(driver);
		// Find the document button with xpath
		WebElement documentButton = driver.findElement(By.xpath("//*[@id=\"Document\"]"));
		// Check to make sure the web element is there
		assertNotNull(documentButton);
		// Just a quick check to make sure the page factory can find the same element
		assertEquals(cc.documentButton, documentButton);
		// Store the class attribute into a variable before the click is pressed
		String preClickClass = documentButton.getAttribute("class");
		// Click the document button
		documentButton.click();
		// Compare the preclick and the post click class attribute, they should be different
		assertNotEquals(preClickClass, documentButton.getAttribute("class"));
		// When clicked, the document button class attribute should contain the string "btn-primary"
		assertTrue(documentButton.getAttribute("class").contains("btn-primary"));
		// The code button should NOT contain the string "btn-primary"
		assertFalse(cc.codeButton.getAttribute("class").contains("btn-primary"));
		// The powerpoint should NOT contain the string "btn-primary"
		assertFalse(cc.powerpointButton.getAttribute("class").contains("btn-primary"));
	}
	@Test
	/**
	 * Test the powerpoint button
	 */
	public void testPowerpointRadioButton() {
		ContentCreator cc = new ContentCreator(driver);
		// Find the powerpoint button with xpath
		WebElement powerpointButton = driver.findElement(By.xpath("//*[@id=\"Powerpoint\"]"));
		// Check to make sure the web element is there
		assertNotNull(powerpointButton);
		// Just a quick check to make sure the page factory can find the same element
		assertEquals(cc.powerpointButton, powerpointButton);
		// Store the class attribute into a variable before the click is pressed
		String preClickClass = cc.powerpointButton.getAttribute("class");
		// Click the powerpoint button
		cc.powerpointButton.click();
		// Compare the preclick and the post click class attribute, the should be different 
		assertNotEquals(preClickClass, cc.powerpointButton.getAttribute("class"));
		// When clicked, the powerpoint button class attribute should contain the string "btn-primary"
		assertTrue(cc.powerpointButton.getAttribute("class").contains("btn-primary"));
		// The code button should NOT contain the string "btn-primary"
		assertFalse(cc.codeButton.getAttribute("class").contains("btn-primary"));
		// The document button should NOT contain the string "btn-primary"
		assertFalse(cc.documentButton.getAttribute("class").contains("btn-primary"));
 	}
	@Test()
	/**
	 * Test the submit button
	 */
	public void testSubmitButtonCheck() {
		ContentCreator cc = new ContentCreator(driver);
		// Find the submit button with xpath
		WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"submitButton\"]"));
		// Check to make sure the web element is there
		assertNotNull(submitButton);
		// Just a quick check to make sure the page factory can find the same element
		assertEquals(cc.submitButton, submitButton);
		// Click the submit button
		cc.clickButton(submitButton);
		// After the submit is selected, a toastr message will appear.
		driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div"));
	}
	@AfterClass
	/**
	 * Tear down the test class after all tests are run
	 */
	public void tearDown() {
		// Close the stream
		driver.close();
		// Shut everything down
		driver.quit();
	}
}