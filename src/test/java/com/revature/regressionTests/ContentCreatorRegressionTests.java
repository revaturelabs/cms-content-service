package com.revature.regressionTests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.pageobjectmodelTest.ContentCreator;

public class ContentCreatorRegressionTests {

	public static WebDriver driver;

	public static String url = "http://revature-cms-dev.s3-website-us-east-1.amazonaws.com/content-creator";

	@BeforeClass
	public void setUP() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
	}

	@Test(enabled = false)
	/**
	 * Test to ensure the correct page was opened
	 */
	public void confirmedContentCreatorPage() {
		assertEquals(driver.getCurrentUrl(), url);
	}

	@Test(enabled = false)
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

	@Test(enabled = false)
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

	@Test(enabled = false)
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
		cc.inputToModuleFilterBox("Java");
		// Check that the text got sent to the correct box
		assertEquals(cc.moduleFilter.getText(), moduleFilterBox.getText());
	}

	/**
	 * Test that the modules are click-able
	 */
	@Test(enabled = false)
	public void testModuleResultClickable() {
		// Grab an existing module
		WebElement getModule = driver.findElement(By.xpath("//*[@id=\"tree\"]/tree-viewport/div/div/tree-node-collection/div/tree-node[1]/div/tree-node-wrapper/div/div/tree-node-content/span"));
		// Check to make sure the module is there
		assertNotNull(getModule);
		// Grab the div containing the module
		WebElement moduleWrapperDiv = driver.findElement(By.xpath("//*[@id=\"tree\"]/tree-viewport/div/div/tree-node-collection/div/tree-node[1]/div/tree-node-wrapper/div/div"));
		// The class should NOT contain the string "wrapper-active"
		assertFalse(moduleWrapperDiv.getAttribute("class").contains("wrapper-active"));
		// Click the module
		getModule.click();
		// The class should contain the string "wrapper-active"
		assertTrue(moduleWrapperDiv.getAttribute("class").contains("wrapper-active"));
	}

	@Test(enabled = false)
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

	@Test(enabled = false)
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

	@Test(enabled = false)
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

	@Test(enabled = false)
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

	@Test(enabled = false)
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

	@Test(priority = 1)
	public void testFormSubmitWithCodeButton() {
		// validate we are indeed on the correct page / URL
		assertEquals(driver.getCurrentUrl(), url);

		// create an instance of our Content Creator POM to use the defined web elements
		ContentCreator cc = new ContentCreator(driver);

		// create local instance of the various input boxes & buttons
		WebElement titleBox = driver.findElement(By.id("titleTextBox"));
		WebElement urlBox = driver.findElement(By.id("urlTextBox"));
		WebElement moduleFilterBox = driver.findElement(By.id("filter"));
		WebElement descriptionBox = driver.findElement(By.id("exampleFormControlTextarea1"));
		WebElement codeBtn = driver.findElement(By.id("Code"));
		WebElement submitBtn = driver.findElement(By.id("submitButton"));
//		WebElement getModule = driver.findElement(By.xpath("//*[@id=\"tree\"]/tree-viewport/div/div/tree-node-collection/div/tree-node[1]/div/tree-node-wrapper/div/div/tree-node-content/span"));
//		WebElement moduleWrapperDiv = driver.findElement(By.xpath("//*[@id=\"tree\"]/tree-viewport/div/div/tree-node-collection/div/tree-node[1]/div/tree-node-wrapper/div/div"));

		// get the class attribute of the code "option" / button
		String preClickClass = codeBtn.getAttribute("class");

		// test that we have retrieved a valid web element
		assertNotNull(titleBox);
		assertNotNull(urlBox);
		assertNotNull(moduleFilterBox);
		assertNotNull(descriptionBox);
		assertNotNull(codeBtn);
		assertNotNull(submitBtn);
//		assertNotNull(getModule);
//		assertNotNull(moduleWrapperDiv);
		// test that the class is not null
		assertNotNull(preClickClass);

		// test that this web element is equal to the title input box defined in the POM
		assertEquals(cc.titleBox, titleBox);
		assertEquals(cc.urlBox, urlBox);
		assertEquals(cc.moduleFilter, moduleFilterBox);
		assertEquals(cc.descriptionBox, descriptionBox);
		assertEquals(cc.codeButton, codeBtn);
		assertEquals(cc.submitButton, submitBtn);

		// The class should NOT contain the string "wrapper-active"
//		assertFalse(moduleWrapperDiv.getAttribute("class").contains("wrapper-active"));
		// Click the module
//		getModule.click();
		// The class should contain the string "wrapper-active"
//		assertTrue(moduleWrapperDiv.getAttribute("class").contains("wrapper-active"));

		// input text into the title box
		cc.inputToTitleBox("Selenium Test Title input box");
		cc.inputToUrlBox("http://www.testnumber2.com");
		cc.inputToModuleFilterBox("Java");
		cc.inputToDescriptionBox("A quality description would go here");

		// test that both instances of the input box contain the same text
		assertEquals(cc.titleBox.getText(), titleBox.getText());
		assertEquals(cc.urlBox.getText(), urlBox.getText());
		assertEquals(cc.moduleFilter.getText(), moduleFilterBox.getText());
		assertEquals(cc.descriptionBox.getText(), descriptionBox.getText());

		// "select" the code option.. by clicking it
		cc.clickButton(cc.codeButton);

		// retrieve the class
		String postClickClass = codeBtn.getAttribute("class");

		// test that the two class values are equal because code is selected by default.
		assertEquals(preClickClass, postClickClass);
		// When pressed the class attribute should contain the string "btn-primary"
		assertTrue(codeBtn.getAttribute("class").contains("btn-primary"));

		// The document button should NOT contain the string "btn-primary"
		assertFalse(cc.documentButton.getAttribute("class").contains("btn-primary"));
		// The powerpoint button should NOT contain the string "btn-primary"
		assertFalse(cc.powerpointButton.getAttribute("class").contains("btn-primary"));

		// submit the newly created content
		cc.clickButton(cc.submitButton);

		// Create wait to confirm the toast message pops up
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id='toast-container']/div")));

		// After the submit is selected, a toastr message will appear.
		WebElement confirmSubmit = driver.findElement(By.xpath("//*[@id='toast-container']/div"));
		assertNotNull(confirmSubmit);
		String toastMessageCheck = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div")).getText();
		assertEquals(toastMessageCheck, "The URL already exsists in database.");
	}

	@Test(priority = 2)
	public void testFormSubmitWithDocumentButton() {
		driver.navigate().refresh();
		// validate we are indeed on the correct page / URL
		assertEquals(driver.getCurrentUrl(), url);

		// create an instance of our Content Creator POM to use the defined web elements
		ContentCreator cc = new ContentCreator(driver);

		// create local instance of the various input boxes & buttons
		WebElement titleBox = driver.findElement(By.id("titleTextBox"));
		WebElement urlBox = driver.findElement(By.id("urlTextBox"));
		WebElement moduleFilterBox = driver.findElement(By.id("filter"));
		WebElement descriptionBox = driver.findElement(By.id("exampleFormControlTextarea1"));
		WebElement documentBtn = driver.findElement(By.id("Document"));
		WebElement submitBtn = driver.findElement(By.id("submitButton"));
//		WebElement getModule = driver.findElement(By.xpath("//*[@id=\"tree\"]/tree-viewport/div/div/tree-node-collection/div/tree-node[1]/div/tree-node-wrapper/div/div/tree-node-content/span"));
//		WebElement moduleWrapperDiv = driver.findElement(By.xpath("//*[@id=\"tree\"]/tree-viewport/div/div/tree-node-collection/div/tree-node[1]/div/tree-node-wrapper/div/div"));

		// get the class attribute of the code "option" / button
		String preClickClass = documentBtn.getAttribute("class");

		// test that we have retrieved a valid web element
		assertNotNull(titleBox);
		assertNotNull(urlBox);
		assertNotNull(moduleFilterBox);
		assertNotNull(descriptionBox);
		assertNotNull(documentBtn);
		assertNotNull(submitBtn);
//		assertNotNull(getModule);
//		assertNotNull(moduleWrapperDiv);
		// test that the class is not null
		assertNotNull(preClickClass);

		// test that this web element is equal to the title input box defined in the POM
		assertEquals(cc.titleBox, titleBox);
		assertEquals(cc.urlBox, urlBox);
		assertEquals(cc.moduleFilter, moduleFilterBox);
		assertEquals(cc.descriptionBox, descriptionBox);
		assertEquals(cc.documentButton, documentBtn);
		assertEquals(cc.submitButton, submitBtn);

		// The class should NOT contain the string "wrapper-active"
//		assertFalse(moduleWrapperDiv.getAttribute("class").contains("wrapper-active"));
		// Click the module
//		getModule.click();
		// The class should contain the string "wrapper-active"
//		assertTrue(moduleWrapperDiv.getAttribute("class").contains("wrapper-active"));

		// input text into the title box
		cc.inputToTitleBox("Selenium Test Title input box");
		cc.inputToUrlBox("http://www.testnumber2.com");
		cc.inputToModuleFilterBox("Java");
		cc.inputToDescriptionBox("A quality description would go here");

		// test that both instances of the input box contain the same text
		assertEquals(cc.titleBox.getText(), titleBox.getText());
		assertEquals(cc.urlBox.getText(), urlBox.getText());
		assertEquals(cc.moduleFilter.getText(), moduleFilterBox.getText());
		assertEquals(cc.descriptionBox.getText(), descriptionBox.getText());

		// "select" the code option.. by clicking it
		cc.clickButton(cc.documentButton);

		// retrieve the class
		String postClickClass = documentBtn.getAttribute("class");

		// test that the two class values are equal because code is selected by default.
		assertNotEquals(preClickClass, postClickClass);
		// When clicked, the document button class attribute should contain the string "btn-primary"
		assertTrue(documentBtn.getAttribute("class").contains("btn-primary"));

		// The code button should NOT contain the string "btn-primary"
		assertFalse(cc.codeButton.getAttribute("class").contains("btn-primary"));
		// The powerpoint should NOT contain the string "btn-primary"
		assertFalse(cc.powerpointButton.getAttribute("class").contains("btn-primary"));

		// submit the newly created content
		cc.clickButton(cc.submitButton);

		// Create wait to confirm the toast message pops up
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id='toast-container']/div")));

		// After the submit is selected, a toastr message will appear.
		WebElement confirmSubmit = driver.findElement(By.xpath("//*[@id='toast-container']/div"));
		assertNotNull(confirmSubmit);
		String toastMessageCheck = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div")).getText();
		assertEquals(toastMessageCheck, "The URL already exsists in database.");
	}

	@Test(priority = 3)
	public void testFormSubmitWithPowerpointButton() {
		driver.navigate().refresh();
		
		// validate we are indeed on the correct page / URL
		assertEquals(driver.getCurrentUrl(), url);

		// create an instance of our Content Creator POM to use the defined web elements
		ContentCreator cc = new ContentCreator(driver);

		// create local instance of the various input boxes & buttons
		WebElement titleBox = driver.findElement(By.id("titleTextBox"));
		WebElement urlBox = driver.findElement(By.id("urlTextBox"));
		WebElement moduleFilterBox = driver.findElement(By.id("filter"));
		WebElement descriptionBox = driver.findElement(By.id("exampleFormControlTextarea1"));
		WebElement powerpointBtn = driver.findElement(By.id("Powerpoint"));
		WebElement submitBtn = driver.findElement(By.id("submitButton"));
//		WebElement getModule = driver.findElement(By.xpath("//*[@id=\"tree\"]/tree-viewport/div/div/tree-node-collection/div/tree-node[1]/div/tree-node-wrapper/div/div/tree-node-content/span"));
//		WebElement moduleWrapperDiv = driver.findElement(By.xpath("//*[@id=\"tree\"]/tree-viewport/div/div/tree-node-collection/div/tree-node[1]/div/tree-node-wrapper/div/div"));

		// get the class attribute of the code "option" / button
		String preClickClass = powerpointBtn.getAttribute("class");

		// test that we have retrieved a valid web element
		assertNotNull(titleBox);
		assertNotNull(urlBox);
		assertNotNull(moduleFilterBox);
		assertNotNull(descriptionBox);
		assertNotNull(powerpointBtn);
		assertNotNull(submitBtn);
//		assertNotNull(getModule);
//		assertNotNull(moduleWrapperDiv);
		// test that the class is not null
		assertNotNull(preClickClass);

		// test that this web element is equal to the title input box defined in the POM
		assertEquals(cc.titleBox, titleBox);
		assertEquals(cc.urlBox, urlBox);
		assertEquals(cc.moduleFilter, moduleFilterBox);
		assertEquals(cc.descriptionBox, descriptionBox);
		assertEquals(cc.powerpointButton, powerpointBtn);
		assertEquals(cc.submitButton, submitBtn);

		// The class should NOT contain the string "wrapper-active"
//		assertFalse(moduleWrapperDiv.getAttribute("class").contains("wrapper-active"));
		// Click the module
//		getModule.click();
		// The class should contain the string "wrapper-active"
//		assertTrue(moduleWrapperDiv.getAttribute("class").contains("wrapper-active"));

		// input text into the title box
		cc.inputToTitleBox("Selenium Test Title input box");
		cc.inputToUrlBox("http://www.testnumber2.com");
		cc.inputToModuleFilterBox("Java");
		cc.inputToDescriptionBox("A quality description would go here");

		// test that both instances of the input box contain the same text
		assertEquals(cc.titleBox.getText(), titleBox.getText());
		assertEquals(cc.urlBox.getText(), urlBox.getText());
		assertEquals(cc.moduleFilter.getText(), moduleFilterBox.getText());
		assertEquals(cc.descriptionBox.getText(), descriptionBox.getText());

		// "select" the code option.. by clicking it
		cc.clickButton(cc.powerpointButton);

		// retrieve the class
		String postClickClass = powerpointBtn.getAttribute("class");

		// test that the two class values are equal because code is selected by default.
		assertNotEquals(preClickClass, postClickClass);
		// When clicked, the powerpoint button class attribute should contain the string "btn-primary"
		assertTrue(cc.powerpointButton.getAttribute("class").contains("btn-primary"));

		// The code button should NOT contain the string "btn-primary"
		assertFalse(cc.codeButton.getAttribute("class").contains("btn-primary"));
		// The document button should NOT contain the string "btn-primary"
		assertFalse(cc.documentButton.getAttribute("class").contains("btn-primary"));

		// submit the newly created content
		cc.clickButton(cc.submitButton);

		// Create wait to confirm the toast message pops up
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id='toast-container']/div")));

		// After the submit is selected, a toastr message will appear.
		WebElement confirmSubmit = driver.findElement(By.xpath("//*[@id='toast-container']/div"));
		assertNotNull(confirmSubmit);
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