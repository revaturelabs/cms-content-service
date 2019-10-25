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
	public String testCurricula = "Java Full Stack";
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
		return new Object[][] {{"TestCode", "C#", ".NET Full Stack"}};
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
		cf.inputToInputBox(cf.titleBox, testTitle);
		//Check if POM WebElement action succeed
		assertEquals(cf.titleBox.getText(),testTitleBox.getText());
	}
	
	/**
	 * Test input String into POM loadModulesBox WebElement
	 */
	@Test(priority=3, enabled=false)
	public void testInputToLoadModulesBox() {
		ContentFinder cf = new ContentFinder(driver);
		WebElement testLoadModulesBox = driver.findElement(By.xpath("//*[@id='subjectDropDown']/div/div/div[2]/input"));
		assertNotNull(testLoadModulesBox);
		assertEquals(cf.modulesBox, testLoadModulesBox);
		cf.inputToInputBox(cf.modulesBox, testLoadModule);
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
	 * Test input String into POM cirriculaBox WebElement
	 */
	@Test(priority=3, enabled=false)
	public void testInputToCurriculaBox() {
		ContentFinder cf = new ContentFinder(driver);
		WebElement testLoadCurriculaBox = driver.findElement(By.xpath("//*[@id='curriculumDropDown']/div/div/div[2]/input"));
		assertNotNull(testLoadCurriculaBox);
		assertEquals(cf.curriculaBox, testLoadCurriculaBox);
		cf.inputToInputBox(cf.curriculaBox, testCurricula);
		List<WebElement> testCurriculaOptions = driver.findElements(By.className("ng-option"));
		assertThat(testCurriculaOptions, not(IsEmptyCollection.empty()));
		for(WebElement option : testCurriculaOptions) {
			System.out.println(option.getText());
			if(option.getText().equals(testCurricula)) {
				option.click();
				break;
			}
		}
		List<WebElement> testCurriculaValues = driver.findElements(By.className("ng-value-label"));
		assertThat(testCurriculaValues, not(IsEmptyCollection.empty()));
		for(WebElement value : testCurriculaValues) {
			if(value.getText().equals(testCurricula)) {
				assertEquals(value.getText(), testCurricula);
				break;
			}
		}
	}
	
	/**
	 * Test clearing POM modulesBox WebElement
	 */
	@Test(priority=4, enabled=false)
	public void testClearModulesBox() {
		ContentFinder cf = new ContentFinder(driver);
		WebElement testClearModulesBox = driver.findElement(By.xpath("//*[@id='subjectDropDown']/div/span[1]"));
		assertNotNull(testClearModulesBox);
		assertEquals(cf.clearModulesBox, testClearModulesBox);
		cf.clickButton(cf.clearModulesBox);
		assertEquals(cf.modulesBox.getText(), "");
	}
	
	/**
	 * Test clearing POM cirruclaBox WebElement
	 */
	@Test(priority=4, enabled=false)
	public void testClearCurriculaBox() {
		ContentFinder cf = new ContentFinder(driver);
		WebElement testClearCirriculaBox = driver.findElement(By.xpath("//*[@id='curriculumDropDown']/div/span[1]"));
		assertNotNull(testClearCirriculaBox);
		assertEquals(cf.clearCirriculaBox, testClearCirriculaBox);
		cf.clickButton(cf.clearCirriculaBox);
		assertEquals(cf.curriculaBox.getText(), "");
	}
	
	//=== REAL TESTS ===
	/**
	 * Test finding (All) Code, Document, Powerpoint content
	 * 
	 * @param title String Content title
	 * @param module String Content module
	 * @param curricula String Content curricula
	 */
	@Test(dataProvider="contentFinder")
	@Parameters({"title", "module", "curricula"})
	public void testFindingAllContent(String title, String module, String curricula) {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Check if driver url is equal to the POM url
		assertTrue(driver.getCurrentUrl().contains(cf.url));

		//Create references to WebElements under test
		WebElement testTitleBox = driver.findElement(By.xpath("//input[@name='title']"));
		WebElement testModulesBox = driver.findElement(By.xpath("//*[@id='subjectDropDown']/div/div/div[2]/input"));
		WebElement testCurriculaBox = driver.findElement(By.xpath("//*[@id='curriculumDropDown']/div/div/div[2]/input"));
		
		//Check if WebElements under test are not null
		assertNotNull(testTitleBox);
		assertNotNull(testModulesBox);
		assertNotNull(testCurriculaBox);
		
		//Check if local WebElements and POM WebElements are equal
		assertEquals(cf.titleBox, testTitleBox);
		assertEquals(cf.modulesBox, testModulesBox);
		assertEquals(cf.curriculaBox, testCurriculaBox);
		
		//Perform POM WebElements actions
		cf.inputToInputBox(cf.titleBox, title);
		cf.inputToInputBox(cf.modulesBox, module);
		List<WebElement> testModuleOptions = driver.findElements(By.className("ng-option"));
		assertThat(testModuleOptions, not(IsEmptyCollection.empty()));
		//Find and perform option click
		for(WebElement moduleOption : testModuleOptions) {
			if(moduleOption.getText().equals(module)) {
				moduleOption.click();
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
		cf.inputToInputBox(cf.curriculaBox, curricula);
		List<WebElement> testCurriculaOptions = driver.findElements(By.className("ng-option"));
		assertThat(testCurriculaOptions, not(IsEmptyCollection.empty()));
		//Find and perform option click
		for(WebElement curriculaOption : testCurriculaOptions) {
			if(curriculaOption.getText().equals(curricula)) {
				curriculaOption.click();
				break;
			}
		}
		//Find and perform assertion on load curricula selected value
		List<WebElement> testCurriculaValues = driver.findElements(By.className("ng-value-label"));
		assertThat(testCurriculaValues, not(IsEmptyCollection.empty()));
		for(WebElement value : testCurriculaValues) {
			if(value.getText().equals(curricula)) {
				assertEquals(value.getText(), curricula);
				break;
			}
		}
		
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
	 * 
	 * @param title String Content title
	 * @param module String Content module
	 * @param curricula String Content curricula
	 */
	@Test(dataProvider="contentFinder")
	@Parameters({"title", "module", "curricula"})
	public void testFindingCodeContent(String title, String module, String curricula) {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Check if driver url is equal to the POM url
		assertTrue(driver.getCurrentUrl().contains(cf.url));
		
		//Create references to WebElements under test
		WebElement testTitleBox = driver.findElement(By.xpath("//input[@name='title']"));
		WebElement testDocumentButton = driver.findElement(By.xpath("//*[@id='Document']"));
		WebElement testPowerpointButton = driver.findElement(By.xpath("//*[@id='Powerpoint']"));
		WebElement testModulesBox = driver.findElement(By.xpath("//*[@id='subjectDropDown']/div/div/div[2]/input"));
		WebElement testCurriculaBox = driver.findElement(By.xpath("//*[@id='curriculumDropDown']/div/div/div[2]/input"));
		
		//Check if WebElements under test are not null
		assertNotNull(testTitleBox);
		assertNotNull(testDocumentButton);
		assertNotNull(testPowerpointButton);
		assertNotNull(testModulesBox);
		assertNotNull(testCurriculaBox);
		
		//Check if local WebElements and POM WebElements are equal
		assertEquals(cf.titleBox, testTitleBox);
		assertEquals(cf.documentButton, testDocumentButton);
		assertEquals(cf.powerpointButton, testPowerpointButton);
		assertEquals(cf.modulesBox, testModulesBox);
		assertEquals(cf.curriculaBox, testCurriculaBox);
		
		//Store pre click classes of content type buttons
		String preClickClassDocumentButton = cf.documentButton.getAttribute("class");
		String preClickClassPowerpointButton = cf.powerpointButton.getAttribute("class");
		
		//Perform POM WebElements actions
		cf.inputToInputBox(cf.titleBox, title);
		cf.clickButton(cf.documentButton);
		cf.clickButton(cf.powerpointButton);
		cf.inputToInputBox(cf.modulesBox, module);
		List<WebElement> testModuleOptions = driver.findElements(By.className("ng-option"));
		assertThat(testModuleOptions, not(IsEmptyCollection.empty()));
		//Find and perform option click
		for(WebElement moduleOption : testModuleOptions) {
			if(moduleOption.getText().equals(module)) {
				moduleOption.click();
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
		cf.inputToInputBox(cf.curriculaBox, curricula);
		List<WebElement> testCurriculaOptions = driver.findElements(By.className("ng-option"));
		assertThat(testCurriculaOptions, not(IsEmptyCollection.empty()));
		//Find and perform option click
		for(WebElement curriculaOption : testCurriculaOptions) {
			if(curriculaOption.getText().equals(curricula)) {
				curriculaOption.click();
				break;
			}
		}
		//Find and perform assertion on load curricula selected value
		List<WebElement> testCurriculaValues = driver.findElements(By.className("ng-value-label"));
		assertThat(testCurriculaValues, not(IsEmptyCollection.empty()));
		for(WebElement value : testCurriculaValues) {
			if(value.getText().equals(curricula)) {
				assertEquals(value.getText(), curricula);
				break;
			}
		}
		
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
	 * 
	 * @param title String Content title
	 * @param module String Content module
	 * @param curricula String Content curricula
	 */
	@Test(dataProvider="contentFinder")
	@Parameters({"title", "module", "curricula"})
	public void testFindingDocumentContent(String title, String module, String curricula) {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Check if driver url is equal to the POM url
		assertTrue(driver.getCurrentUrl().contains(cf.url));
		
		//Create references to WebElements under test
		WebElement testTitleBox = driver.findElement(By.xpath("//input[@name='title']"));
		WebElement testCodeButton = driver.findElement(By.xpath("//*[@id='Code']"));
		WebElement testPowerpointButton = driver.findElement(By.xpath("//*[@id='Powerpoint']"));
		WebElement testModulesBox = driver.findElement(By.xpath("//*[@id='subjectDropDown']/div/div/div[2]/input"));
		WebElement testCurriculaBox = driver.findElement(By.xpath("//*[@id='curriculumDropDown']/div/div/div[2]/input"));
		
		//Check if WebElements under test are not null
		assertNotNull(testTitleBox);
		assertNotNull(testCodeButton);
		assertNotNull(testPowerpointButton);
		assertNotNull(testModulesBox);
		assertNotNull(testCurriculaBox);
		
		//Check if local WebElements and POM WebElements are equal
		assertEquals(cf.titleBox, testTitleBox);
		assertEquals(cf.codeButton, testCodeButton);
		assertEquals(cf.powerpointButton, testPowerpointButton);
		assertEquals(cf.modulesBox, testModulesBox);
		assertEquals(cf.curriculaBox, testCurriculaBox);
		
		//Store pre click classes of content type buttons
		String preClickClassCodeButton = cf.codeButton.getAttribute("class");
		String preClickClassPowerpointButton = cf.powerpointButton.getAttribute("class");
		
		//Perform POM WebElements actions
		cf.inputToInputBox(cf.titleBox, title);
		cf.clickButton(cf.codeButton);
		cf.clickButton(cf.powerpointButton);
		cf.inputToInputBox(cf.modulesBox, module);
		List<WebElement> testModuleOptions = driver.findElements(By.className("ng-option"));
		assertThat(testModuleOptions, not(IsEmptyCollection.empty()));
		//Find and perform option click
		for(WebElement moduleOption : testModuleOptions) {
			if(moduleOption.getText().equals(module)) {
				moduleOption.click();
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
		cf.inputToInputBox(cf.curriculaBox, curricula);
		List<WebElement> testCurriculaOptions = driver.findElements(By.className("ng-option"));
		assertThat(testCurriculaOptions, not(IsEmptyCollection.empty()));
		//Find and perform option click
		for(WebElement curriculaOption : testCurriculaOptions) {
			if(curriculaOption.getText().equals(curricula)) {
				curriculaOption.click();
				break;
			}
		}
		//Find and perform assertion on load curricula selected value
		List<WebElement> testCurriculaValues = driver.findElements(By.className("ng-value-label"));
		assertThat(testCurriculaValues, not(IsEmptyCollection.empty()));
		for(WebElement value : testCurriculaValues) {
			if(value.getText().equals(curricula)) {
				assertEquals(value.getText(), curricula);
				break;
			}
		}
		
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
	 * 
	 * @param title String Content title
	 * @param module String Content module
	 * @param curricula String Content curricula
	 */
	@Test(dataProvider="contentFinder")
	@Parameters({"title", "module", "curricula"})
	public void testFindingPowerpointContent(String title, String module, String curricula) {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Check if driver url is equal to the POM url
		assertTrue(driver.getCurrentUrl().contains(cf.url));
		
		//Create references to WebElements under test
		WebElement testTitleBox = driver.findElement(By.xpath("//input[@name='title']"));
		WebElement testCodeButton = driver.findElement(By.xpath("//*[@id='Code']"));
		WebElement testDocumentButton = driver.findElement(By.xpath("//*[@id='Document']"));
		WebElement testModulesBox = driver.findElement(By.xpath("//*[@id='subjectDropDown']/div/div/div[2]/input"));
		WebElement testCurriculaBox = driver.findElement(By.xpath("//*[@id='curriculumDropDown']/div/div/div[2]/input"));
		
		//Check if WebElements under test are not null
		assertNotNull(testTitleBox);
		assertNotNull(testCodeButton);
		assertNotNull(testDocumentButton);
		assertNotNull(testModulesBox);
		assertNotNull(testCurriculaBox);
		
		//Check if local WebElements and POM WebElements are equal
		assertEquals(cf.titleBox, testTitleBox);
		assertEquals(cf.codeButton, testCodeButton);
		assertEquals(cf.documentButton, testDocumentButton);
		assertEquals(cf.modulesBox, testModulesBox);
		assertEquals(cf.curriculaBox, testCurriculaBox);
		
		//Store pre click classes of content type buttons
		String preClickClassCodeButton = cf.codeButton.getAttribute("class");
		String preClickClassDocumentButton = cf.documentButton.getAttribute("class");
		
		//Perform POM WebElements actions
		cf.inputToInputBox(cf.titleBox, title);
		cf.clickButton(cf.codeButton);
		cf.clickButton(cf.documentButton);
		cf.inputToInputBox(cf.modulesBox, module);
		List<WebElement> testModuleOptions = driver.findElements(By.className("ng-option"));
		assertThat(testModuleOptions, not(IsEmptyCollection.empty()));
		//Find and perform option click
		for(WebElement moduleOption : testModuleOptions) {
			if(moduleOption.getText().equals(module)) {
				moduleOption.click();
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
		cf.inputToInputBox(cf.curriculaBox, curricula);
		List<WebElement> testCurriculaOptions = driver.findElements(By.className("ng-option"));
		assertThat(testCurriculaOptions, not(IsEmptyCollection.empty()));
		//Find and perform option click
		for(WebElement curriculaOption : testCurriculaOptions) {
			if(curriculaOption.getText().equals(curricula)) {
				curriculaOption.click();
				break;
			}
		}
		//Find and perform assertion on load curricula selected value
		List<WebElement> testCurriculaValues = driver.findElements(By.className("ng-value-label"));
		assertThat(testCurriculaValues, not(IsEmptyCollection.empty()));
		for(WebElement value : testCurriculaValues) {
			if(value.getText().equals(curricula)) {
				assertEquals(value.getText(), curricula);
				break;
			}
		}
		
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
	 * 
	 * @param title String Content title
	 * @param module String Content module
	 * @param curricula String Content curricula
	 */
	@Test(dataProvider="contentFinder")
	@Parameters({"title", "module", "curricula"})
	public void testFindingCodeAndDocumentContent(String title, String module, String curricula) {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Check if driver url is equal to the POM url
		assertTrue(driver.getCurrentUrl().contains(cf.url));
		
		//Create references to WebElements under test
		WebElement testTitleBox = driver.findElement(By.xpath("//input[@name='title']"));
		WebElement testPowerpointButton = driver.findElement(By.xpath("//*[@id='Powerpoint']"));
		WebElement testModulesBox = driver.findElement(By.xpath("//*[@id='subjectDropDown']/div/div/div[2]/input"));
		WebElement testCurriculaBox = driver.findElement(By.xpath("//*[@id='curriculumDropDown']/div/div/div[2]/input"));
		
		//Check if WebElements under test are not null
		assertNotNull(testTitleBox);
		assertNotNull(testPowerpointButton);
		assertNotNull(testModulesBox);
		assertNotNull(testCurriculaBox);
		
		//Check if local WebElements and POM WebElements are equal
		assertEquals(cf.titleBox, testTitleBox);
		assertEquals(cf.powerpointButton, testPowerpointButton);
		assertEquals(cf.modulesBox, testModulesBox);
		assertEquals(cf.curriculaBox, testCurriculaBox);
		
		//Store pre click classes of content type buttons
		String preClickClassPowerpointButton = cf.powerpointButton.getAttribute("class");
		
		//Perform POM WebElements actions
		cf.inputToInputBox(cf.titleBox, title);
		cf.clickButton(cf.powerpointButton);
		cf.inputToInputBox(cf.modulesBox, module);
		List<WebElement> testModuleOptions = driver.findElements(By.className("ng-option"));
		assertThat(testModuleOptions, not(IsEmptyCollection.empty()));
		//Find and perform option click
		for(WebElement moduleOption : testModuleOptions) {
			if(moduleOption.getText().equals(module)) {
				moduleOption.click();
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
		cf.inputToInputBox(cf.curriculaBox, curricula);
		List<WebElement> testCurriculaOptions = driver.findElements(By.className("ng-option"));
		assertThat(testCurriculaOptions, not(IsEmptyCollection.empty()));
		//Find and perform option click
		for(WebElement curriculaOption : testCurriculaOptions) {
			if(curriculaOption.getText().equals(curricula)) {
				curriculaOption.click();
				break;
			}
		}
		//Find and perform assertion on load curricula selected value
		List<WebElement> testCurriculaValues = driver.findElements(By.className("ng-value-label"));
		assertThat(testCurriculaValues, not(IsEmptyCollection.empty()));
		for(WebElement value : testCurriculaValues) {
			if(value.getText().equals(curricula)) {
				assertEquals(value.getText(), curricula);
				break;
			}
		}
		
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
	 * 
	 * @param title String Content title
	 * @param module String Content module
	 * @param curricula String Content curricula
	 */
	@Test(dataProvider="contentFinder")
	@Parameters({"title", "module", "curricula"})
	public void testFindingCodeAndPowerpointContent(String title, String module, String curricula) {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Check if driver url is equal to the POM url
		assertTrue(driver.getCurrentUrl().contains(cf.url));
		
		//Create references to WebElements under test
		WebElement testTitleBox = driver.findElement(By.xpath("//input[@name='title']"));
		WebElement testDocumentButton = driver.findElement(By.xpath("//*[@id='Document']"));
		WebElement testModulesBox = driver.findElement(By.xpath("//*[@id='subjectDropDown']/div/div/div[2]/input"));
		WebElement testCurriculaBox = driver.findElement(By.xpath("//*[@id='curriculumDropDown']/div/div/div[2]/input"));
		
		//Check if WebElements under test are not null
		assertNotNull(testTitleBox);
		assertNotNull(testDocumentButton);
		assertNotNull(testModulesBox);
		assertNotNull(testCurriculaBox);
		
		//Check if local WebElements and POM WebElements are equal
		assertEquals(cf.titleBox, testTitleBox);
		assertEquals(cf.documentButton, testDocumentButton);
		assertEquals(cf.modulesBox, testModulesBox);
		assertEquals(cf.curriculaBox, testCurriculaBox);
		
		//Store pre click classes of content type buttons
		String preClickClassDocumentButton = cf.documentButton.getAttribute("class");
		
		//Perform POM WebElements actions
		cf.inputToInputBox(cf.titleBox, title);
		cf.clickButton(cf.documentButton);
		cf.inputToInputBox(cf.modulesBox, module);
		List<WebElement> testModuleOptions = driver.findElements(By.className("ng-option"));
		assertThat(testModuleOptions, not(IsEmptyCollection.empty()));
		//Find and perform option click
		for(WebElement moduleOption : testModuleOptions) {
			if(moduleOption.getText().equals(module)) {
				moduleOption.click();
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
		cf.inputToInputBox(cf.curriculaBox, curricula);
		List<WebElement> testCurriculaOptions = driver.findElements(By.className("ng-option"));
		assertThat(testCurriculaOptions, not(IsEmptyCollection.empty()));
		//Find and perform option click
		for(WebElement curriculaOption : testCurriculaOptions) {
			if(curriculaOption.getText().equals(curricula)) {
				curriculaOption.click();
				break;
			}
		}
		//Find and perform assertion on load curricula selected value
		List<WebElement> testCurriculaValues = driver.findElements(By.className("ng-value-label"));
		assertThat(testCurriculaValues, not(IsEmptyCollection.empty()));
		for(WebElement value : testCurriculaValues) {
			if(value.getText().equals(curricula)) {
				assertEquals(value.getText(), curricula);
				break;
			}
		}		

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
	 * 
	 * @param title String Content title
	 * @param module String Content module
	 * @param curricula String Content curricula
	 */
	@Test(dataProvider="contentFinder")
	@Parameters({"title", "module", "curricula"})
	public void testFindingDocumentAndPowerpointContent(String title, String module, String curricula) {
		//Instantiate new POM of ContentFinder
		ContentFinder cf = new ContentFinder(driver);
		//Check if driver url is equal to the POM url
		assertTrue(driver.getCurrentUrl().contains(cf.url));
		
		//Create references to WebElements under test
		WebElement testTitleBox = driver.findElement(By.xpath("//input[@name='title']"));
		WebElement testCodeButton = driver.findElement(By.xpath("//*[@id='Code']"));
		WebElement testModulesBox = driver.findElement(By.xpath("//*[@id='subjectDropDown']/div/div/div[2]/input"));
		WebElement testCurriculaBox = driver.findElement(By.xpath("//*[@id='curriculumDropDown']/div/div/div[2]/input"));
		
		//Check if WebElements under test are not null
		assertNotNull(testTitleBox);
		assertNotNull(testCodeButton);
		assertNotNull(testModulesBox);
		assertNotNull(testCurriculaBox);
		
		//Check if local WebElements and POM WebElements are equal
		assertEquals(cf.titleBox, testTitleBox);
		assertEquals(cf.codeButton, testCodeButton);
		assertEquals(cf.modulesBox, testModulesBox);
		assertEquals(cf.curriculaBox, testCurriculaBox);
		
		//Store pre click classes of content type buttons
		String preClickClassCodeButton = cf.codeButton.getAttribute("class");
		
		//Perform POM WebElements actions
		cf.inputToInputBox(cf.titleBox, testTitle);
		cf.clickButton(cf.codeButton);
		cf.inputToInputBox(cf.modulesBox, module);
		List<WebElement> testModuleOptions = driver.findElements(By.className("ng-option"));
		assertThat(testModuleOptions, not(IsEmptyCollection.empty()));
		//Find and perform option click
		for(WebElement moduleOption : testModuleOptions) {
			if(moduleOption.getText().equals(module)) {
				moduleOption.click();
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
		cf.inputToInputBox(cf.curriculaBox, curricula);
		List<WebElement> testCurriculaOptions = driver.findElements(By.className("ng-option"));
		assertThat(testCurriculaOptions, not(IsEmptyCollection.empty()));
		//Find and perform option click
		for(WebElement curriculaOption : testCurriculaOptions) {
			if(curriculaOption.getText().equals(curricula)) {
				curriculaOption.click();
				break;
			}
		}
		//Find and perform assertion on load curricula selected value
		List<WebElement> testCurriculaValues = driver.findElements(By.className("ng-value-label"));
		assertThat(testCurriculaValues, not(IsEmptyCollection.empty()));
		for(WebElement value : testCurriculaValues) {
			if(value.getText().equals(curricula)) {
				assertEquals(value.getText(), curricula);
				break;
			}
		}
		
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
