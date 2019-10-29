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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.revature.pageobjectmodel.ContentCreator;

public class ContentCreatorRegressionTests {

	public static WebDriver driver;

//	public static String url = "http://revature-cms-dev.s3-website-us-east-1.amazonaws.com/content-creator";
	public static String url = "http://localhost:4200/content-creator";
	@BeforeClass
	public void setUP() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
	}

	/**
	 * @purpose this method is used to test the content creator page
	 * this is a "happy path" test and tests all input fields with 
	 * correct input. Then the test will select the "Code" button specifically
	 * and then submit the form. Lastly, it is testing for a toastr message.
	 * Currently the toastr message should say "The URL already exsists in database."
	 * because we are re-inputting the same input. (We didn't want to fill up the test DB)
	 */
	@Test(priority = 1)
	@Parameters({"title", "goodUrl", "module", "description"})
	public void testFormSubmitWithCodeButton(String title, String goodUrl, String module, String description) {
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
		WebElement getModule = driver.findElement(By.xpath("//*[@id=\"tree\"]/tree-viewport/div/div/tree-node-collection/div/tree-node[2]/div/tree-node-wrapper/div/div/tree-node-content/comment()[2]"));
		WebElement moduleWrapperDiv = driver.findElement(By.xpath("//*[@id=\"tree\"]/tree-viewport/div/div/tree-node-collection/div/tree-node[1]/div/tree-node-wrapper/div/div"));

		// get the class attribute of the code "option" / button
		String preClickClass = codeBtn.getAttribute("class");

		// test that we have retrieved a valid web element
		assertNotNull(titleBox);
		assertNotNull(urlBox);
		assertNotNull(moduleFilterBox);
		assertNotNull(descriptionBox);
		assertNotNull(codeBtn);
		assertNotNull(submitBtn);
		assertNotNull(getModule);
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
//		// Click the module
//		getModule.click();
//		// The class should contain the string "wrapper-active"
//		assertTrue(moduleWrapperDiv.getAttribute("class").contains("wrapper-active"));

		// input text into the title box
		cc.inputToTitleBox(title);
		cc.inputToUrlBox(goodUrl);
		cc.inputToModuleFilterBox(module);
		cc.inputToDescriptionBox(description);

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

	/**
	 * @purpose this method is used to test the content creator page
	 * this is a "happy path" test and tests all input fields with 
	 * correct input. Then the test will select the "Document" button specifically
	 * and then submit the form. Lastly, it is testing for a toastr message.
	 * Currently the toastr message should say "The URL already exsists in database."
	 * because we are re-inputting the same input. (We didn't want to fill up the test DB)
	 */
	@Test(priority = 2)
	@Parameters({"title", "goodUrl", "module", "description"})
	public void testFormSubmitWithDocumentButton(String title, String goodUrl, String module, String description) {
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
//		// Click the module
//		getModule.click();
//		// The class should contain the string "wrapper-active"
//		assertTrue(moduleWrapperDiv.getAttribute("class").contains("wrapper-active"));

		// input text into the title box
		cc.inputToTitleBox(title);
		cc.inputToUrlBox(goodUrl);
		cc.inputToModuleFilterBox(module);
		cc.inputToDescriptionBox(description);

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

	/**
	 * @purpose this method is used to test the content creator page
	 * this is a "happy path" test and tests all input fields with 
	 * correct input. Then the test will select the "Powerpoint" button specifically
	 * and then submit the form. Lastly, it is testing for a toastr message.
	 * Currently the toastr message should say "The URL already exsists in database."
	 * because we are re-inputting the same input. (We didn't want to fill up the test DB)
	 */
	@Test(priority = 3)
	@Parameters({"title", "goodUrl", "module", "description"})
	public void testFormSubmitWithPowerpointButton(String title, String goodUrl, String module, String description) {
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
//		// Click the module
//		getModule.click();
//		// The class should contain the string "wrapper-active"
//		assertTrue(moduleWrapperDiv.getAttribute("class").contains("wrapper-active"));

		// input text into the title box
		cc.inputToTitleBox(title);
		cc.inputToUrlBox(goodUrl);
		cc.inputToModuleFilterBox(module);
		cc.inputToDescriptionBox(description);

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
		String toastMessageCheck = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div")).getText();
		assertEquals(toastMessageCheck, "The URL already exsists in database.");
	}
	
	/**
	 * @purpose this method is used to test the content creator page
	 * this is a "negative path" test and tests inputting negative or invalid data.
	 * The result of this test will test for an error or "invalid URL" message within
	 * the toastr pop-up. 
	 */
	@Test(priority = 4)
	@Parameters({"title", "badUrl", "module", "description"})
	public void testInvalidURL(String title, String badUrl, String module, String description) {
		driver.navigate().refresh();
		
		// validate we are indeed on the correct page / URL
		assertEquals(driver.getCurrentUrl(), url);

		// create an instance of our Content Creator POM to use the defined web elements
		ContentCreator cc = new ContentCreator(driver);
		
		// test that we have retrieved a valid web elements
		assertNotNull(cc.titleBox);
		assertNotNull(cc.moduleFilter);
		assertNotNull(cc.descriptionBox);
		assertNotNull(cc.codeButton);
		assertNotNull(cc.submitButton);
		
		cc.inputToTitleBox(title);
		cc.inputToUrlBox(badUrl);
		cc.inputToModuleFilterBox(module);
		cc.inputToDescriptionBox(description);
		
		// attempt to submit the create content form
		cc.clickButton(cc.submitButton);
		
		// Create wait to confirm the toast message pops up
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id='toast-container']/div")));

		// After the submit is selected, a toastr message will appear.
		WebElement confirmSubmit = driver.findElement(By.xpath("//*[@id='toast-container']/div"));
		assertNotNull(confirmSubmit);
		// Retrieve our toast message
		String toastMessageCheck = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div")).getText();
		// test our toast message to be the defined error message.
		assertEquals(toastMessageCheck, "Invalid URL. e.g. \"http://example.com\", \"ftp://www.example.com\", \"http://192.168.0.0\"");
	}
	
	@Test(priority = 5)
	@Parameters({"validUrl", "module", "description"})
	public void testSubmitWithNoTitle(String validUrl, String module, String description) {
		driver.navigate().refresh();
		
		// validate we are indeed on the correct page / URL
		assertEquals(driver.getCurrentUrl(), url);

		// create an instance of our Content Creator POM to use the defined web elements
		ContentCreator cc = new ContentCreator(driver);
		
		// test that we have retrieved a valid web elements
		assertNotNull(cc.titleBox);
		assertNotNull(cc.moduleFilter);
		assertNotNull(cc.descriptionBox);
		assertNotNull(cc.codeButton);
		assertNotNull(cc.submitButton);
		
		cc.inputToUrlBox(validUrl);
		cc.inputToModuleFilterBox(module);
		cc.inputToDescriptionBox(description);
		
		// attempt to submit the create content form
		cc.clickButton(cc.submitButton);
		
		// Create wait to confirm the toast message pops up
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id='toast-container']/div")));

		// After the submit is selected, a toastr message will appear.
		WebElement confirmSubmit = driver.findElement(By.xpath("//*[@id='toast-container']/div"));
		assertNotNull(confirmSubmit);
		// Retrieve our toast message
		String toastMessageCheck = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div")).getText();
		// test our toast message to be the defined error message.
		assertEquals(toastMessageCheck, "Please fill in all input fields!");
	}
	
	@Test(priority=6)
	public void testExistingURL() {
		driver.navigate().refresh();
		
		// validate we are indeed on the correct page / URL
		assertEquals(driver.getCurrentUrl(), url);

		// create an instance of our Content Creator POM to use the defined web elements
		ContentCreator cc = new ContentCreator(driver);
		
		// test that we have retrieved a valid web elements
		assertNotNull(cc.titleBox);
		assertNotNull(cc.moduleFilter);
		assertNotNull(cc.descriptionBox);
		assertNotNull(cc.codeButton);
		assertNotNull(cc.submitButton);
		
		cc.inputToTitleBox("Testing Existing URL Msg");
		cc.inputToUrlBox("http://www.testnumber2.com");
		cc.inputToModuleFilterBox("Java");
		cc.inputToDescriptionBox("A quality description would go here");
		
		// attempt to submit the create content form
		cc.clickButton(cc.submitButton);
		
		// Create wait to confirm the toast message pops up
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id='toast-container']/div")));
		
		// After the submit is selected, a toastr message will appear.
		WebElement errorMsg = driver.findElement(By.xpath("//*[@id='toast-container']/div"));
		assertNotNull(errorMsg);
		// Retrieve our toast message
		String toastMessageCheck = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div")).getText();
		// test our toast message to be the defined error message.
		assertEquals(toastMessageCheck, "The URL already exsists in database.");
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