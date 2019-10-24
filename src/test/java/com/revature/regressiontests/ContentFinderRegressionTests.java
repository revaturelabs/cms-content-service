package com.revature.regressiontests;

import static org.hamcrest.CoreMatchers.not;
import static org.testng.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.hamcrest.collection.IsEmptyCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.revature.pageobjectmodel.ContentFinder;

public class ContentFinderRegressionTests {
	
	public static WebDriver driver;
	
	public static String url = "http://revature-cms-dev.s3-website-us-east-1.amazonaws.com/finder";
	public String testTitle = "Test Title";
	public String testLoadModule = "qazxsw";
	public WebDriverWait wait;
	
	/**
	 * Set up chrome webdriver and navigate to ContentFinder page
	 */
	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
		wait = new WebDriverWait(driver, 20);
	}
	
	/**
	 * Close connection and close browser
	 */
	@AfterClass
	public void tearDown() {
		//driver.close();
		//driver.quit();
	}
	
	@DataProvider(name="contentFinder")
	public Object[][] contendFinderDP() {
		return new Object[][] {{"TestCode", "C#"}};
	}
	
	
	/**
	 * Test if the driver url is equal to the POM url
	 */
	@Test(priority=1, enabled=false)
	public void testContentFinderPage() {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Check if driver url is equal to the POM url
		assertEquals(driver.getCurrentUrl(), cf.url);
	}
	
	/**
	 * Test input String into POM titleBox WebElement
	 */
	@Test(priority=2, enabled=false)
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
	@Test(priority=3, enabled=false)
	public void testInputToLoadModulesBox() {
		ContentFinder cf = new ContentFinder(driver);
		WebElement testLoadModulesBox = driver.findElement(By.xpath("//*[@id=\"subjectDropDown\"]/div/div/div[2]/input"));
		assertNotNull(testLoadModulesBox);
		assertEquals(cf.loadModulesBox, testLoadModulesBox);
		cf.inputToLoadModulesBox(testLoadModule);
		List<WebElement> testModuleOptions = driver.findElements(By.className("ng-option"));
		assertThat(testModuleOptions, not(IsEmptyCollection.empty()));
		for(WebElement option : testModuleOptions) {
			System.out.println(option.getText());
			if(option.getText().equals(testLoadModule)) {
				option.click();
				break;
			}
		}
		List<WebElement> testModuleValues = driver.findElements(By.className("ng-value-label"));
		assertThat(testModuleValues, not(IsEmptyCollection.empty()));
		for(WebElement value : testModuleValues) {
			if(value.getText().equals(testLoadModule)) {
				assertEquals(value.getText(), testLoadModule);
				break;
			}
		}
	}
	
	/**
	 * Test clearing POM loadModulesBox WebElement
	 */
	@Test(priority=4, enabled=false)
	public void testClearLoadModulesBox() {
		ContentFinder cf = new ContentFinder(driver);
		WebElement testClearLoadModulesBox = driver.findElement(By.xpath("//*[@id=\"subjectDropDown\"]/div/span[1]"));
		assertNotNull(testClearLoadModulesBox);
		assertEquals(cf.clearLoadModulesBox, testClearLoadModulesBox);
		cf.clearLoadModulesBox();
		assertEquals(cf.loadModulesBox.getText(), "");
	}
	
	//=== REAL TESTS ===
	/**
	 * Test finding (All) Code, Document, Powerpoint content
	 */
	@Test(dataProvider="contentFinder")
	@Parameters({"title", "module"})
	public void testFindingAllContent(String title, String module) {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Check if driver url is equal to the POM url
		assertTrue(driver.getCurrentUrl().contains(cf.url));

		//Create references to WebElements under test
		WebElement testTitleBox = driver.findElement(By.xpath("//input[@name='title']"));
		WebElement testLoadModulesBox = driver.findElement(By.xpath("//*[@id=\"subjectDropDown\"]/div/div/div[2]/input"));
		
		//Check if WebElements under test are not null
		assertNotNull(testTitleBox);
		assertNotNull(testLoadModulesBox);
		
		//Check if local WebElements and POM WebElements are equal
		assertEquals(cf.titleBox, testTitleBox);
		assertEquals(cf.loadModulesBox, testLoadModulesBox);
		
		//Perform POM WebElements actions
		cf.inputToTitleBox(title);
		cf.inputToLoadModulesBox(module);
		List<WebElement> testModuleOptions = driver.findElements(By.className("ng-option"));
		
		//Check if POM titleBox WebElement action succeeded
		assertEquals(cf.titleBox.getText(),testTitleBox.getText());
		assertThat(testModuleOptions, not(IsEmptyCollection.empty()));
		
		//Find and perform option click
		for(WebElement option : testModuleOptions) {
			System.out.println(option.getText());
			if(option.getText().equals(module)) {
				option.click();
				break;
			}
		}
		//Find and perform assertion on load module selected value
		List<WebElement> testModuleValues = driver.findElements(By.className("ng-value-label"));
		assertThat(testModuleValues, not(IsEmptyCollection.empty()));
		for(WebElement value : testModuleValues) {
			if(value.getText().equals(module)) {
				assertEquals(value.getText(), module);
				break;
			}
		}
		
		//Wait until submitButton WebElement is visible
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='submitButton']")));
		//Create a reference to the submitButton WebElement
		WebElement testSubmitButton = driver.findElement(By.xpath("//*[@id='submitButton']"));
		//Check if submitButton WebElement is not null
		assertNotNull(testSubmitButton);
		//Check if submitButton WebElement and POM submitButton WebElement are equal
		assertEquals(cf.submitButton, testSubmitButton);
		//Perform POM submitButton WebElement action
		cf.clickButton(cf.submitButton);
		//Check if submitButton WebElement action succeeded
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("format=Code,Document,Powerpoint"));
		driver.navigate().to(cf.url);
	}

	/**
	 * Test finding Code content only
	 */
	@Test(enabled=false)
	public void testFindingCodeContent() {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Check if driver url is equal to the POM url
		assertTrue(driver.getCurrentUrl().contains(cf.url));
		
		//Create references to WebElements under test
		WebElement testTitleBox = driver.findElement(By.xpath("//input[@name='title']"));
		WebElement testDocumentButton = driver.findElement(By.xpath("//*[@id='Document']"));
		WebElement testPowerpointButton = driver.findElement(By.xpath("//*[@id='Powerpoint']"));
		
		//Check if WebElements under test are not null
		assertNotNull(testTitleBox);
		assertNotNull(testDocumentButton);
		assertNotNull(testPowerpointButton);
		
		//Check if local WebElements and POM WebElements are equal
		assertEquals(cf.titleBox, testTitleBox);
		assertEquals(cf.documentButton, testDocumentButton);
		assertEquals(cf.powerpointButton, testPowerpointButton);
		
		//Store pre click classes of content type buttons
		String preClickClassDocumentButton = cf.documentButton.getAttribute("class");
		String preClickClassPowerpointButton = cf.powerpointButton.getAttribute("class");
		
		//Perform POM WebElements actions
		cf.inputToTitleBox(testTitle);
		cf.clickButton(cf.documentButton);
		cf.clickButton(cf.powerpointButton);
		
		//Verify POM WebElements actions succeeded
		assertEquals(cf.titleBox.getText(),testTitleBox.getText());
		assertNotEquals(cf.documentButton.getAttribute("class"), preClickClassDocumentButton);
		assertNotEquals(cf.powerpointButton.getAttribute("class"), preClickClassPowerpointButton);
		
		//Store post click classes of content type buttons
		String postClickClassDocumentButton = cf.documentButton.getAttribute("class");
		String postClickClassPowerpointButton = cf.powerpointButton.getAttribute("class");
		
		//Verify post click classes of content type buttons contain btn-secondary
		assertTrue(postClickClassDocumentButton.contains("btn-secondary"));
		assertTrue(postClickClassPowerpointButton.contains("btn-secondary"));
		
		//Wait until submitButton WebElement is visible
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='submitButton']")));
		//Create a reference to the submitButton WebElement
		WebElement testSubmitButton = driver.findElement(By.xpath("//*[@id='submitButton']"));
		//Check if submitButton WebElement is not null
		assertNotNull(testSubmitButton);
		//Check if submitButton WebElement and POM submitButton WebElement are equal
		assertEquals(cf.submitButton, testSubmitButton);
		//Perform POM submitButton WebElement action
		cf.clickButton(cf.submitButton);
		//Check if submitButton WebElement action succeeded
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("format=Code"));
		driver.navigate().to(cf.url);
	}
	
	/**
	 * Test finding Document content only
	 */
	@Test(enabled=false)
	public void testFindingDocumentContent() {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Check if driver url is equal to the POM url
		assertTrue(driver.getCurrentUrl().contains(cf.url));
		
		//Create references to WebElements under test
		WebElement testTitleBox = driver.findElement(By.xpath("//input[@name='title']"));
		WebElement testCodeButton = driver.findElement(By.xpath("//*[@id='Code']"));
		WebElement testPowerpointButton = driver.findElement(By.xpath("//*[@id='Powerpoint']"));
		
		//Check if WebElements under test are not null
		assertNotNull(testTitleBox);
		assertNotNull(testCodeButton);
		assertNotNull(testPowerpointButton);
		
		//Check if local WebElements and POM WebElements are equal
		assertEquals(cf.titleBox, testTitleBox);
		assertEquals(cf.codeButton, testCodeButton);
		assertEquals(cf.powerpointButton, testPowerpointButton);
		
		//Store pre click classes of content type buttons
		String preClickClassCodeButton = cf.codeButton.getAttribute("class");
		String preClickClassPowerpointButton = cf.powerpointButton.getAttribute("class");
		
		//Perform POM WebElements actions
		cf.inputToTitleBox(testTitle);
		cf.clickButton(cf.codeButton);
		cf.clickButton(cf.powerpointButton);
		
		//Verify POM WebElements actions succeeded
		assertEquals(cf.titleBox.getText(),testTitleBox.getText());
		assertNotEquals(cf.codeButton.getAttribute("class"), preClickClassCodeButton);
		assertNotEquals(cf.powerpointButton.getAttribute("class"), preClickClassPowerpointButton);
		
		//Store post click classes of content type buttons
		String postClickClassCodeButton = cf.codeButton.getAttribute("class");
		String postClickClassPowerpointButton = cf.powerpointButton.getAttribute("class");
		
		//Verify post click classes of content type buttons contain btn-secondary
		assertTrue(postClickClassCodeButton.contains("btn-secondary"));
		assertTrue(postClickClassPowerpointButton.contains("btn-secondary"));
		
		//Wait until submitButton WebElement is visible
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='submitButton']")));
		//Create a reference to the submitButton WebElement
		WebElement testSubmitButton = driver.findElement(By.xpath("//*[@id='submitButton']"));
		//Check if submitButton WebElement is not null
		assertNotNull(testSubmitButton);
		//Check if submitButton WebElement and POM submitButton WebElement are equal
		assertEquals(cf.submitButton, testSubmitButton);
		//Perform POM submitButton WebElement action
		cf.clickButton(cf.submitButton);
		//Check if submitButton WebElement action succeeded
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("format=Document"));
		driver.navigate().to(cf.url);
	}
	
	/**
	 * Test finding Powerpoint content only
	 */
	@Test(enabled=false)
	public void testFindingPowerpointContent() {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Check if driver url is equal to the POM url
		assertTrue(driver.getCurrentUrl().contains(cf.url));
		
		//Create references to WebElements under test
		WebElement testTitleBox = driver.findElement(By.xpath("//input[@name='title']"));
		WebElement testCodeButton = driver.findElement(By.xpath("//*[@id='Code']"));
		WebElement testDocumentButton = driver.findElement(By.xpath("//*[@id='Document']"));
		
		//Check if WebElements under test are not null
		assertNotNull(testTitleBox);
		assertNotNull(testCodeButton);
		assertNotNull(testDocumentButton);
		
		//Check if local WebElements and POM WebElements are equal
		assertEquals(cf.titleBox, testTitleBox);
		assertEquals(cf.codeButton, testCodeButton);
		assertEquals(cf.documentButton, testDocumentButton);
		
		//Store pre click classes of content type buttons
		String preClickClassCodeButton = cf.codeButton.getAttribute("class");
		String preClickClassDocumentButton = cf.documentButton.getAttribute("class");
		
		//Perform POM WebElements actions
		cf.inputToTitleBox(testTitle);
		cf.clickButton(cf.codeButton);
		cf.clickButton(cf.documentButton);
		
		//Verify POM WebElements actions succeeded
		assertEquals(cf.titleBox.getText(),testTitleBox.getText());
		assertNotEquals(cf.codeButton.getAttribute("class"), preClickClassCodeButton);
		assertNotEquals(cf.documentButton.getAttribute("class"), preClickClassDocumentButton);
		
		//Store post click classes of content type buttons
		String postClickClassCodeButton = cf.codeButton.getAttribute("class");
		String postClickClassDocumentButton = cf.documentButton.getAttribute("class");
		
		//Verify post click classes of content type buttons contain btn-secondary
		assertTrue(postClickClassCodeButton.contains("btn-secondary"));
		assertTrue(postClickClassDocumentButton.contains("btn-secondary"));
		
		//Wait until submitButton WebElement is visible
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='submitButton']")));
		//Create a reference to the submitButton WebElement
		WebElement testSubmitButton = driver.findElement(By.xpath("//*[@id='submitButton']"));
		//Check if submitButton WebElement is not null
		assertNotNull(testSubmitButton);
		//Check if submitButton WebElement and POM submitButton WebElement are equal
		assertEquals(cf.submitButton, testSubmitButton);
		//Perform POM submitButton WebElement action
		cf.clickButton(cf.submitButton);
		//Check if submitButton WebElement action succeeded
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("format=Powerpoint"));
		driver.navigate().to(cf.url);
	}
	
	/**
	 * Test finding Code and Document content
	 */
	@Test(enabled=false)
	public void testFindingCodeAndDocumentContent() {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Check if driver url is equal to the POM url
		assertTrue(driver.getCurrentUrl().contains(cf.url));
		
		//Create references to WebElements under test
		WebElement testTitleBox = driver.findElement(By.xpath("//input[@name='title']"));
		WebElement testPowerpointButton = driver.findElement(By.xpath("//*[@id='Powerpoint']"));
		
		//Check if WebElements under test are not null
		assertNotNull(testTitleBox);
		assertNotNull(testPowerpointButton);
		
		//Check if local WebElements and POM WebElements are equal
		assertEquals(cf.titleBox, testTitleBox);
		assertEquals(cf.powerpointButton, testPowerpointButton);
		
		//Store pre click classes of content type buttons
		String preClickClassPowerpointButton = cf.powerpointButton.getAttribute("class");
		
		//Perform POM WebElements actions
		cf.inputToTitleBox(testTitle);
		cf.clickButton(cf.powerpointButton);
		
		//Verify POM WebElements actions succeeded
		assertEquals(cf.titleBox.getText(),testTitleBox.getText());
		assertNotEquals(cf.powerpointButton.getAttribute("class"), preClickClassPowerpointButton);
		
		//Store post click classes of content type buttons
		String postClickClassPowerpointButton = cf.powerpointButton.getAttribute("class");
		
		//Verify post click classes of content type buttons contain btn-secondary
		assertTrue(postClickClassPowerpointButton.contains("btn-secondary"));

		//Wait until submitButton WebElement is visible
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='submitButton']")));
		//Create a reference to the submitButton WebElement
		WebElement testSubmitButton = driver.findElement(By.xpath("//*[@id='submitButton']"));
		//Check if submitButton WebElement is not null
		assertNotNull(testSubmitButton);
		//Check if submitButton WebElement and POM submitButton WebElement are equal
		assertEquals(cf.submitButton, testSubmitButton);
		//Perform POM submitButton WebElement action
		cf.clickButton(cf.submitButton);
		//Check if submitButton WebElement action succeeded
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("format=Code,Document"));
		driver.navigate().to(cf.url);
	}
	
	/**
	 * Test finding Code, Powerpoint content
	 */
	@Test(enabled=false)
	public void testFindingCodeAndPowerpointContent() {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Check if driver url is equal to the POM url
		assertTrue(driver.getCurrentUrl().contains(cf.url));
		
		//Create references to WebElements under test
		WebElement testTitleBox = driver.findElement(By.xpath("//input[@name='title']"));
		WebElement testDocumentButton = driver.findElement(By.xpath("//*[@id='Document']"));
		
		//Check if WebElements under test are not null
		assertNotNull(testTitleBox);
		assertNotNull(testDocumentButton);
		
		//Check if local WebElements and POM WebElements are equal
		assertEquals(cf.titleBox, testTitleBox);
		assertEquals(cf.documentButton, testDocumentButton);
		
		//Store pre click classes of content type buttons
		String preClickClassDocumentButton = cf.documentButton.getAttribute("class");
		
		//Perform POM WebElements actions
		cf.inputToTitleBox(testTitle);
		cf.clickButton(cf.documentButton);
		
		//Verify POM WebElements actions succeeded
		assertEquals(cf.titleBox.getText(),testTitleBox.getText());
		assertNotEquals(cf.documentButton.getAttribute("class"), preClickClassDocumentButton);
		
		//Store post click classes of content type buttons
		String postClickClassDocumentButton = cf.documentButton.getAttribute("class");

		//Verify post click classes of content type buttons contain btn-secondary
		assertTrue(postClickClassDocumentButton.contains("btn-secondary"));

		//Wait until submitButton WebElement is visible
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='submitButton']")));
		//Create a reference to the submitButton WebElement
		WebElement testSubmitButton = driver.findElement(By.xpath("//*[@id='submitButton']"));
		//Check if submitButton WebElement is not null
		assertNotNull(testSubmitButton);
		//Check if submitButton WebElement and POM submitButton WebElement are equal
		assertEquals(cf.submitButton, testSubmitButton);
		//Perform POM submitButton WebElement action
		cf.clickButton(cf.submitButton);
		//Check if submitButton WebElement action succeeded
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("format=Code,Powerpoint"));
		driver.navigate().to(cf.url);
	}
	
	/**
	 * Test finding Document, Powerpoint content
	 */
	@Test(enabled=false)
	public void testFindingDocumentAndPowerpointContent() {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Check if driver url is equal to the POM url
		assertTrue(driver.getCurrentUrl().contains(cf.url));
		
		//Create references to WebElements under test
		WebElement testTitleBox = driver.findElement(By.xpath("//input[@name='title']"));
		WebElement testCodeButton = driver.findElement(By.xpath("//*[@id='Code']"));
		
		//Check if WebElements under test are not null
		assertNotNull(testTitleBox);
		assertNotNull(testCodeButton);
		
		//Check if local WebElements and POM WebElements are equal
		assertEquals(cf.titleBox, testTitleBox);
		assertEquals(cf.codeButton, testCodeButton);
		
		//Store pre click classes of content type buttons
		String preClickClassCodeButton = cf.codeButton.getAttribute("class");
		
		//Perform POM WebElements actions
		cf.inputToTitleBox(testTitle);
		cf.clickButton(cf.codeButton);
		
		//Check if POM titleBox WebElement action succeeded
		assertEquals(cf.titleBox.getText(),testTitleBox.getText());
		assertNotEquals(cf.codeButton.getAttribute("class"), preClickClassCodeButton);
		
		//Store post click classes of content type buttons
		String postClickClassCodeButton = cf.codeButton.getAttribute("class");
		
		//Verify post click classes of content type buttons contain btn-secondary
		assertTrue(postClickClassCodeButton.contains("btn-secondary"));

		//Wait until submitButton WebElement is visible
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='submitButton']")));
		//Create a reference to the submitButton WebElement
		WebElement testSubmitButton = driver.findElement(By.xpath("//*[@id='submitButton']"));
		//Check if submitButton WebElement is not null
		assertNotNull(testSubmitButton);
		//Check if submitButton WebElement and POM submitButton WebElement are equal
		assertEquals(cf.submitButton, testSubmitButton);
		//Perform POM submitButton WebElement action
		cf.clickButton(cf.submitButton);
		//Check if submitButton WebElement action succeeded
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("format=Document,Powerpoint"));
		driver.navigate().to(cf.url);
	}
}
