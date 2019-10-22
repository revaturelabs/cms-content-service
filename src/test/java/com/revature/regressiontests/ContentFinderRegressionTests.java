package com.revature.regressiontests;

import static org.junit.Assert.assertNotEquals;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.revature.pageobjectmodel.ContentFinder;

public class ContentFinderRegressionTests {
	
	public static WebDriver driver;
	
	public static String url = "http://localhost:4200/finder";
	public String testTitle = "Test Title";
	public String testLoadModule = "Test Load Module";
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
		assertEquals(cf.loadModulesBox.getText(), testLoadModulesBox.getText());
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
	
	/**
	 * Test clicking POM codeButton WebElement
	 */
	@Test(priority=5, enabled=false)
	public void testClickCodeButton() {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Create a reference to the WebElement under test
		WebElement testCodeButton = driver.findElement(By.xpath("//*[@id='Code']"));
		//Check if WebElement under test is not null
		assertNotNull(testCodeButton);
		//Check if WebElement under test and POM WebElement under test are equal
		assertEquals(cf.codeButton, testCodeButton);
		//Store pre click class value for later assertion
		String preClickClass = cf.codeButton.getAttribute("class");
		//Perform POM WebElement action
		cf.clickButton(cf.codeButton);
		//Check if POM WebElement action succeed
		assertNotEquals(cf.codeButton.getAttribute("class"), preClickClass);
		//Check if POM WebElement post click class contains btn-secondary
		String postClickClass = cf.codeButton.getAttribute("class");
		assertTrue(postClickClass.contains("btn-secondary"));
	}
	
	/**
	 * Test selecting POM codeRadioButton WebElement
	 */
	@Test(priority=5, enabled=false)
	public void testClickedCodeRadioButton() {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Create a reference to the WebElement under test
		WebElement testCodeRadioButton = driver.findElement(By.xpath("//input[@id='Code']"));
		//Check if WebElement under test is not null
		assertNotNull(testCodeRadioButton);
		//Check if WebElement under test and POM WebElement under test are equal
		//assertEquals(cf.codeRadioButton, testCodeRadioButton);
		//Perform POM WebElement action
		//cf.clickRadioButton(cf.codeRadioButton);
		//Check if POM WebElement action succeed
		//assertTrue(cf.codeRadioButton.isSelected());
	}
	
	/**
	 * Test clicking POM documentButton WebElement
	 */
	@Test(priority=6, enabled=false)
	public void testClickDocumentButton() {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Create a reference to the WebElement under test
		WebElement testDocumentButton = driver.findElement(By.xpath("//*[@id='Document']"));
		//Check if WebElement under test is not null
		assertNotNull(testDocumentButton);
		//Check if WebElement under test and POM WebElement under test are equal
		assertEquals(cf.documentButton, testDocumentButton);
		//Store pre click class value for later assertion
		String preClickClass = cf.documentButton.getAttribute("class");
		//Perform POM WebElement action
		cf.clickButton(cf.documentButton);
		//Check if POM WebElement action succeed
		assertNotEquals(cf.documentButton.getAttribute("class"), preClickClass);
		//Check if POM WebElement post click class contains btn-secondary
		String postClickClass = cf.documentButton.getAttribute("class");
		assertTrue(postClickClass.contains("btn-secondary"));
	}
	
	/**
	 * Test selecting POM documentRadioButton WebElement
	 */
	@Test(priority=6, enabled=false)
	public void testClickedDocumentRadioButton() {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Create a reference to the WebElement under test
		WebElement testDocumentRadioButton = driver.findElement(By.xpath("//input[@id='Document']"));
		//Check if WebElement under test is not null
		assertNotNull(testDocumentRadioButton);
		//Check if WebElement under test and POM WebElement under test are equal
		//assertEquals(cf.documentRadioButton, testDocumentRadioButton);
		//Perform POM WebElement action
		//cf.clickRadioButton(cf.documentRadioButton);
		//Check if POM WebElement action succeed
		//assertTrue(cf.documentRadioButton.isSelected());
	}
	
	/**
	 * Test clicking POM powerpointButton WebElement
	 */
	@Test(priority=6, enabled=false)
	public void testClickPowerpointButton() {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Create a reference to the WebElement under test
		WebElement testPowerpointButton = driver.findElement(By.xpath("//*[@id='Powerpoint']"));
		//Check if WebElement under test is not null
		assertNotNull(testPowerpointButton);
		//Check if WebElement under test and POM WebElement under test are equal
		assertEquals(cf.powerpointButton, testPowerpointButton);
		//Store pre click class value for later assertion
		String preClickClass = cf.powerpointButton.getAttribute("class");
		//Perform POM WebElement action
		cf.clickButton(cf.documentButton);
		//Check if POM WebElement action succeed
		assertNotEquals(cf.documentButton.getAttribute("class"), preClickClass);
		//Check if POM WebElement post click class contains btn-secondary
		String postClickClass = cf.documentButton.getAttribute("class");
		assertTrue(postClickClass.contains("btn-secondary"));
	}
	
	/**
	 * Test selecting POM powerpointRadioButton WebElement
	 */
	@Test(priority=7, enabled=false)
	public void testClickedPowerpointRadioButton() {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Create a reference to the WebElement under test
		WebElement testPowerpointRadioButton = driver.findElement(By.xpath("//input[@id='Powerpoint']"));
		//Check if WebElement under test is not null
		assertNotNull(testPowerpointRadioButton);
		//Check if WebElement under test and POM WebElement under test are equal
		//assertEquals(cf.powerpointRadioButton, testPowerpointRadioButton);
		//Perform POM WebElement action
		//cf.clickRadioButton(cf.powerpointRadioButton);
		//Check if POM WebElement action succeed
		//assertTrue(cf.powerpointRadioButton.isSelected());
	}
	
	/**
	 * Test selecting POM flaggedRadioButton WebElement
	 */
	@Test(priority=8, enabled=false)
	public void testClickedFlaggedRadioButton() {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Create a reference to the WebElement under test
		WebElement testFlaggedRadioButton = driver.findElement(By.xpath("//input[@id='Flagged']"));
		//Check if WebElement under test is not null
		assertNotNull(testFlaggedRadioButton);
		//Check if WebElement under test and POM WebElement under test are equal
		//assertEquals(cf.flaggedRadioButton, testFlaggedRadioButton);
		//Perform POM WebElement action
		//cf.clickRadioButton(cf.flaggedRadioButton);
		//Check if POM WebElement action succeed
		//assertTrue(cf.flaggedRadioButton.isSelected());
	}
	/**
	 * Test selecting POM allRadioButton WebElement
	 */
	@Test(priority=9, enabled=false)
	public void testClickedAllRadioButton() {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Create a reference to the WebElement under test
		WebElement testAllRadioButton = driver.findElement(By.xpath("//input[@id='All']"));
		//Check if WebElement under test is not null
		assertNotNull(testAllRadioButton);
		//Check if WebElement under test and POM WebElement under test are equal
		//assertEquals(cf.allRadioButton, testAllRadioButton);
		//Perform POM WebElement action
		//cf.clickRadioButton(cf.allRadioButton);
		//Check if POM WebElement action succeed
		//assertTrue(cf.allRadioButton.isSelected());
	}
	
	/**
	 * Test clicking the POM submitButton WebElement
	 */
	@Test(priority=10, enabled=false)
	public void testClickedSubmitButton() {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Create a reference to the WebElement under test
		WebElement testSubmitButton = driver.findElement(By.xpath("//*[@id='submitButton']"));
		//Check if WebElement under test is not null
		assertNotNull(testSubmitButton);
		//Check if WebElement under test and POM WebElement under test are equal
		assertEquals(cf.submitButton, testSubmitButton);
		//Perform POM WebElment action
		cf.clickSubmitButton();
		//assertTrue(testSubmitButton.isSelected());
		//driver.manage().timeouts().implicitlyWait(10, Time)
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("?"));
	}
	
	@DataProvider(name="formatButtons")
	public Object[][] formatButtonsDP(){
		Object[][] formatButtons = new Object[][] {{}};
		return formatButtons;
	}
	
	//=== REAL TESTS ===
	/**
	 * Test finding (All) Code, Document, Powerpoint content
	 */
	@Test
	public void testFindingAllContent() {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Check if driver url is equal to the POM url
		assertTrue(driver.getCurrentUrl().contains(cf.url));

		//Create a reference to the titleBox WebElement
		WebElement testTitleBox = driver.findElement(By.xpath("//input[@name='title']"));
		//Check if titleBox WebElement is not null
		assertNotNull(testTitleBox);
		//Check if titleBox WebElement and POM titleBox WebElement are equal
		assertEquals(cf.titleBox, testTitleBox);
		//Perform POM titleBox WebElement action
		cf.inputToTitleBox(testTitle);
		//Check if POM titleBox WebElement action succeeded
		assertEquals(cf.titleBox.getText(),testTitleBox.getText());
		
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
	@Test
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
	@Test
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
	@Test
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
	@Test
	public void testFindingCodeAndDocumentContent() {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Check if driver url is equal to the POM url
		assertTrue(driver.getCurrentUrl().contains(cf.url));
		//Create a reference to the titleBox WebElement
		WebElement testTitleBox = driver.findElement(By.xpath("//input[@name='title']"));
		//Check if titleBox WebElement is not null
		assertNotNull(testTitleBox);
		//Check if titleBox WebElement and POM titleBox WebElement are equal
		assertEquals(cf.titleBox, testTitleBox);
		//Perform POM titleBox WebElement action
		cf.inputToTitleBox(testTitle);
		//Check if POM titleBox WebElement action succeeded
		assertEquals(cf.titleBox.getText(),testTitleBox.getText());
		
		//create a reference to the powerpointButton WebElement
		WebElement testPowerpointButton = driver.findElement(By.xpath("//*[@id='Powerpoint']"));
		//check if powerpointButton WebElement is not null
		assertNotNull(testPowerpointButton);
		//Check if powerpointButton WebElement and POM powerointButton WebElement are equal
		assertEquals(cf.powerpointButton, testPowerpointButton);
		//Store pre click powerpointButton class value for later assertion
		String preClickClassPowerpointButton = cf.powerpointButton.getAttribute("class");
		//Perform POM powerointButton WebElement action
		cf.clickButton(cf.powerpointButton);
		//Check if POM powerpointButton WebElement action succeeded
		assertNotEquals(cf.powerpointButton.getAttribute("class"), preClickClassPowerpointButton);
		//Check if POM powerpointButton WebElement post click class contains btn-secondary
		String postClickClassPowerpointButton = cf.powerpointButton.getAttribute("class");
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
	@Test
	public void testFindingCodeAndPowerpointContent() {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Check if driver url is equal to the POM url
		assertTrue(driver.getCurrentUrl().contains(cf.url));
		//Create a reference to the titleBox WebElement
		WebElement testTitleBox = driver.findElement(By.xpath("//input[@name='title']"));
		//Check if titleBox WebElement is not null
		assertNotNull(testTitleBox);
		//Check if titleBox WebElement and POM titleBox WebElement are equal
		assertEquals(cf.titleBox, testTitleBox);
		//Perform POM titleBox WebElement action
		cf.inputToTitleBox(testTitle);
		//Check if POM titleBox WebElement action succeeded
		assertEquals(cf.titleBox.getText(),testTitleBox.getText());
		
		//Create a reference to the documentButton WebElement
		WebElement testDocumentButton = driver.findElement(By.xpath("//*[@id='Document']"));
		//check if documentButton WebElement is not null
		assertNotNull(testDocumentButton);
		//Check if documentButton WebElement and POM documentButton WebElement are equal
		assertEquals(cf.documentButton, testDocumentButton);
		//Store pre click documentButton class value for later assertion
		String preClickClassDocumentButton = cf.documentButton.getAttribute("class");
		//Perform POM documentButton WebElement action
		cf.clickButton(cf.documentButton);
		//Check if POM documentButton WebElement action succeeded
		assertNotEquals(cf.documentButton.getAttribute("class"), preClickClassDocumentButton);
		//Check if POM documentButton WebElement post click class contains btn-secondary
		String postClickClassDocumentButton = cf.documentButton.getAttribute("class");
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
	@Test
	public void testFindingDocumentAndPowerpointContent() {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Check if driver url is equal to the POM url
		assertTrue(driver.getCurrentUrl().contains(cf.url));
		//Create a reference to the titleBox WebElement
		WebElement testTitleBox = driver.findElement(By.xpath("//input[@name='title']"));
		//Check if titleBox WebElement is not null
		assertNotNull(testTitleBox);
		//Check if titleBox WebElement and POM titleBox WebElement are equal
		assertEquals(cf.titleBox, testTitleBox);
		//Perform POM titleBox WebElement action
		cf.inputToTitleBox(testTitle);
		//Check if POM titleBox WebElement action succeeded
		assertEquals(cf.titleBox.getText(),testTitleBox.getText());
		
		//Create a reference to the codeButton WebElement
		WebElement testCodeButton = driver.findElement(By.xpath("//*[@id='Code']"));
		//Check if codeButton WebElement is not null
		assertNotNull(testCodeButton);
		//Check if codeButton WebElement and POM codeButton WebElement are equal
		assertEquals(cf.codeButton, testCodeButton);
		//Store pre click codeButton class value for later assertion
		String preClickClassCodeButton = cf.codeButton.getAttribute("class");
		//Perform POM codeButton WebElement action
		cf.clickButton(cf.codeButton);
		//Check if POM codeButton WebElement action succeeded
		assertNotEquals(cf.codeButton.getAttribute("class"), preClickClassCodeButton);
		//Check if POM codeButton WebElement post click class contains btn-secondary
		String postClickClassCodeButton = cf.codeButton.getAttribute("class");
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
