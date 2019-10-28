package com.revature.util;


import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertTrue;

import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.entities.Content;
import com.revature.entities.Module;
import com.revature.exceptions.InvalidContentException;
import com.revature.exceptions.InvalidModuleException;
import com.revature.exceptions.InvalidSearchException;
import com.revature.testingutils.ContentFactory;
import com.revature.util.ValidationUtil;

public class ValidationUtilTest {	
	String badString = ContentFactory.badS;
	ValidationUtil vu;
	Content content = null;
	Module module = null;
	@Spy ValidationUtil vuMock;
	
	
	@BeforeTest
	void setup() {
		MockitoAnnotations.initMocks(this);
		vu= new  ValidationUtil();
	}
	@BeforeMethod
	void init() {
		content = ContentFactory.getContent();
		module = new Module();
		
	}
	
	/**
	 * This method tests
	 * {@link com.revature.util.ValidationUtil#verifyContent(Content content) 
	 * verifyContent(Content content)}.
	 * This method assumes that content is null.
	 * The result expected is that verifyContent(null) will throw an InvalidContentException.
	 */    
	@Test(expectedExceptions = InvalidContentException.class)
	public void verifyContentTestContentEqualsNullThrowException() {
		content=null;
		vu.verifyContent(content);
	}
	/**
	 * This method tests
	 * {@link com.revature.util.ValidationUtil#verifyContent(Content content) 
	 * verifyContent(Content content)}.
	 * This method assumes that content.setFormat has a string longer than MAXCHARLENGTH.
	 * The result expected is that verifyContent(content) will throw an InvalidContentException.
	 */    
	@Test(expectedExceptions = InvalidContentException.class)
	public void verifyContentTestSecondConditionMoreThanMAXCHARLENGTH() {
		content.setFormat(badString);
		vu.verifyContent(content);
	}
	
	/**
	 * This method tests
	 * {@link com.revature.util.ValidationUtil#verifyContent(Content content) 
	 * verifyContent(Content content)}.
	 * This method assumes that content.setFormat has a string longer than MAXCHARLENGTH.
	 * The result expected is that verifyContent(content) will throw an InvalidContentException.
	 */  
	@Test(expectedExceptions = InvalidContentException.class)
	public void verifyContentTestSecondConditionGetFormatIsEmpty() {
		content.setFormat("");
		vu.verifyContent(content);
	}
	
	/**
	 * This method tests
	 * {@link com.revature.util.ValidationUtil#verifyContent(Content content) 
	 * verifyContent(Content content)}.
	 * This method assumes that content.setTitle has a string longer than MAXCHARLENGTH.
	 * The result expected is that verifyContent(content) will throw an InvalidContentException.
	 */  
	@Test(expectedExceptions = InvalidContentException.class)
	public void verifyContentTestThirdConditionMoreThanMAXCHARLENGTH() {
		content.setTitle(badString);
		vu.verifyContent(content);
	}
	
	/**
	 * This method tests
	 * {@link com.revature.util.ValidationUtil#verifyContent(Content content) 
	 * verifyContent(Content content)}.
	 * This method assumes that content.setTitle is empty.
	 * The result expected is that verifyContent(content) will throw an InvalidContentException.
	 */  
	@Test(expectedExceptions = InvalidContentException.class)
	public void verifyContentTestThirdConditionGetTitleIsEmpty() {
		content.setTitle("");
		vu.verifyContent(content);
	}
	
	/**
	 * This method tests
	 * {@link com.revature.util.ValidationUtil#verifyContent(Content content) 
	 * verifyContent(Content content)}.
	 * This method assumes that content.setUrl has a string longer than MAXCHARLENGTH.
	 * The result expected is that verifyContent(content) will throw an InvalidContentException.
	 */  
	@Test(expectedExceptions = InvalidContentException.class)
	public void verifyContentTestFourthConditionMoreThanMAXCHARLENGTH() {
		content.setUrl(badString);
		vu.verifyContent(content);
	}
	
	/**
	 * This method tests
	 * {@link com.revature.util.ValidationUtil#verifyContent(Content content) 
	 * verifyContent(Content content)}.
	 * This method assumes that content.setUrl is empty.
	 * The result expected is that verifyContent(content) will throw an InvalidContentException.
	 */  
	@Test(expectedExceptions = InvalidContentException.class)
	public void verifyContentTestFourthConditionGetUrlIsEmpty() {
		content.setUrl("");
		vu.verifyContent(content);
	}
	
	/**
	 * This method tests
	 * {@link com.revature.util.ValidationUtil#verifyContent(Content content) 
	 * verifyContent(Content content)}.
	 * This method assumes that content.setDescription has a string longer than MAXCHARLENGTH.
	 * The result expected is that verifyContent(content) will throw an InvalidContentException.
	 * NOTE FROM TESTER: vuMock was used here to test the exception, unlike in the other exception tests.
	 */  
	@Test(expectedExceptions = InvalidContentException.class)
	public void verifyContentTestFifthConditionThrowException() {
		content.setDescription(badString);
		vuMock.verifyContent(content);
	}
	
	/**
	 * This method tests
	 * {@link com.revature.util.ValidationUtil#verifyContent(Content content) 
	 * verifyContent(Content content)}.
	 * This method assumes that content.setFormat has a string longer than MAXCHARLENGTH.
	 * The result expected is that verifyContent(content) will throw an InvalidContentException.
	 */  
	@Test
	public void verifyContentTest() {
		assertTrue(vuMock.verifyContent(content));
	}
	
	/**
	 * This method tests
	 * {@link com.revature.util.ValidationUtil#verifyModule(Module module) 
	 * verifyModule(Module module)}.
	 * This method assumes that module is null.
	 * The result expected is that verifyModule(null) will throw an InvalidContentException.
	 */  
	@Test(expectedExceptions = InvalidContentException.class)
	public void verifyModuleTestFirstconditionThrowException() {
		module = null;
		vuMock.verifyModule(module);
	}
	
	/**
	 * This method tests
	 * {@link com.revature.util.ValidationUtil#verifyModule(Module module) 
	 * verifyModule(Module module)}.
	 * This method assumes that module.subject gets set to a string longer than the max length.
	 * The result expected is that verifyModule(module) will throw an InvalidModuleException.
	 */
	@Test(expectedExceptions = InvalidModuleException.class)
	public void verifyModuleSecondConditionMoreThanMAXCHARLENGTH() {
		module.setSubject(badString);
		vuMock.verifyModule(module);		
	}
	
	/**
	 * This method tests
	 * {@link com.revature.util.ValidationUtil#verifyModule(Module module) 
	 * verifyModule(Module module)}.
	 * This method assumes that module.subject gets set to an empty string.
	 * The result expected is that verifyModule(module) will throw an InvalidModuleException.
	 */
	@Test(expectedExceptions = InvalidModuleException.class)
	public void verifyModuleSecondConditiongetSubjectIsEmpty() {
		module.setSubject("");
		vuMock.verifyModule(module);		
	}
	
	/**
	 * This method tests
	 * {@link com.revature.util.ValidationUtil#verifyModule(Module module) 
	 * verifyModule(Module module)}.
	 * This method assumes that module.subject gets set to a test string.
	 * The result expected is that verifyModule(module) will assert true.
	 */
	@Test
	public void verifyModuleTest() {
		module.setSubject("asdads");
		assertTrue(vu.verifyModule(module));
	}
		
	/**
	 * This method tests
	 * {@link com.revature.util.ValidationUtil#verifyStringTitle(String title) 
	 * verifyStringTitle(String title) }.
	 * This method assumes that module.title gets set to a test string that exceeds the maximum length.
	 * The result expected is that verifyStringTitle(String title) will throw an invalid search exception.
	 */
	@Test(expectedExceptions = InvalidSearchException.class)
	public void verifyStringTitleTestThrowException() {
		vuMock.verifyStringTitle(badString);		
	}
	
	/**
	 * This method tests
	 * {@link com.revature.util.ValidationUtil#verifyStringFormat(String format) 
	 * verifyStringFormat(String format)}.
	 * This method assumes that module.format gets set to a test string that exceeds the maximum length.
	 * The result expected is that verifyStringFormat(String format) will throw an invalid search exception.
	 */
	@Test(expectedExceptions = InvalidSearchException.class)
	public void verifyStringFormatTestThrowException() {
		vuMock.verifyStringFormat(badString);		
	}
	
	/**
	 * This method tests
	 * {@link com.revature.util.ValidationUtil#verifyModuleId(int moduleId) 
	 * verifyModuleId(int moduleId)}.
	 * This method passes in a negative number to verifyModuleId(int moduleId).
	 * The result expected is that verifyModuleId(int moduleId) will throw an invalid search exception.
	 */
	@Test(expectedExceptions = InvalidSearchException.class)
	public void verifyModuleIdTestThrowException() {
		vuMock.verifyModuleId(-20);
	}
	
	/**
	 * This method tests
	 * {@link com.revature.util.ValidationUtil#verifyId(int id) 
	 * verifyId(int id)}.
	 * This method passes in a negative number to verifyId(int id).
	 * The result expected is that verifyId(int id) will throw an invalid search exception.
	 */
	@Test(expectedExceptions =  InvalidSearchException.class)
	public void verifyIdTestThrowException() {
		vuMock.verifyId(-30);
	}
	
	/**
	 * This method tests
	 * {@link com.revature.util.ValidationUtil#verifyId(int id) 
	 * verifyId(int id)}.
	 * This method passes in a positive number to verifyId(int id).
	 * The result expected is that verifyId(int id) will  not throw an exception.
	 * NOTE FROM TESTER: one of few cases of positive testing done here.
	 */
	@Test
	public void verifyIdNoThrowException() {
		vuMock.verifyId(5);
		verify(vuMock).verifyId(5);
	}
	
	/**
	 * This method tests
	 * {@link com.revature.util.ValidationUtil#verifyListModuleId(List<Integer> moduleIds) 
	 * verifyListModuleId(List<Integer> moduleIds)}.
	 * This method assumes that a new arraylist adds the integer 5.
	 * The result expected is that verifyListModuleId(List<Integer> moduleIds) will  not throw an exception 
	 * for any of the entries in the array list.
	 */
	@Test
	public void verifyListModuleIdTestRuns() {
		List<Integer> modulesIds = new ArrayList<Integer>();
		modulesIds.add(5);
		vuMock.verifyListModuleId(modulesIds);
	}
	
	/**
	 * This method tests
	 * {@link com.revature.util.ValidationUtil#verifyFilter(String title, String format, List<Integer> moduleIds) 
	 * verifyFilter(String title, String format, List<Integer> moduleIds)}.
	 * This method assumes that a verifyFilter(String title, String format, List<Integer> moduleIds)
	 * receives all valid input.
	 * The result expected is that a line within verifyListModuleId(List<Integer> moduleIds) 
	 * executed properly.
	 */
	@Test
	public void verifyFilterTest() {
		String title = "1";
		String format = "sf" ;
		List<Integer> moduleIds = new ArrayList<Integer>();  
		moduleIds.add(1);
		vuMock.verifyFilter(title, format, moduleIds);
		verify(vuMock).verifyStringTitle(title);
	}
	
}
