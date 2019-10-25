package com.revature.utilTest;


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
	@Test(expectedExceptions = InvalidContentException.class)
	public void verifyContentTestContentEqualsNullThrowException() {
		content=null;
		vu.verifyContent(content);
	}
	@Test(expectedExceptions = InvalidContentException.class)
	public void verifyContentTestSecondConditionMoreThanMAXCHARLENGTH() {
		content.setFormat(badString);
		vu.verifyContent(content);
	}
	@Test(expectedExceptions = InvalidContentException.class)
	public void verifyContentTestSecondConditionGetFormatIsEmpty() {
		content.setFormat("");
		vu.verifyContent(content);
	}
	
	@Test(expectedExceptions = InvalidContentException.class)
	public void verifyContentTestThirdConditionMoreThanMAXCHARLENGTH() {
		content.setTitle(badString);
		vu.verifyContent(content);
	}
	@Test(expectedExceptions = InvalidContentException.class)
	public void verifyContentTestThirdConditionGetTitleIsEmpty() {
		content.setTitle("");
		vu.verifyContent(content);
	}
	@Test(expectedExceptions = InvalidContentException.class)
	public void verifyContentTestFourthConditionMoreThanMAXCHARLENGTH() {
		content.setUrl(badString);
		vu.verifyContent(content);
	}
	@Test(expectedExceptions = InvalidContentException.class)
	public void verifyContentTestFourthConditionGetUrlIsEmpty() {
		content.setUrl("");
		vu.verifyContent(content);
	}
	@Test(expectedExceptions = InvalidContentException.class)
	public void verifyContentTestFifthConditionThrowException() {
		content.setDescription(badString);
		vuMock.verifyContent(content);
	}
	@Test
	public void verifyContentTest() {
		assertTrue(vuMock.verifyContent(content));
	}
	
	
	@Test(expectedExceptions = InvalidContentException.class)
	public void verifyModuleTestFirstconditionThrowException() {
		module = null;
		vuMock.verifyModule(module);
	}
	@Test(expectedExceptions = InvalidModuleException.class)
	public void verifyModuleSecondConditionMoreThanMAXCHARLENGTH() {
		module.setSubject(badString);
		vuMock.verifyModule(module);		
	}
	@Test(expectedExceptions = InvalidModuleException.class)
	public void verifyModuleSecondConditiongetSubjectIsEmpty() {
		module.setSubject("");
		vuMock.verifyModule(module);		
	}
	@Test
	public void verifyModuleTest() {
		module.setSubject("asdads");
		assertTrue(vu.verifyModule(module));
	}
		
	@Test(expectedExceptions = InvalidSearchException.class)
	public void verifyStringTitleTestThrowException() {
		vuMock.verifyStringTitle(badString);		
	}
	@Test(expectedExceptions = InvalidSearchException.class)
	public void verifyStringFortmatTestThrowException() {
		vuMock.verifyStringFormat(badString);		
	}
	@Test(expectedExceptions = InvalidSearchException.class)
	public void verifyModuleIdTestThrowException() {
		vuMock.verifyModuleId(-20);
	}
	@Test(expectedExceptions =  InvalidSearchException.class)
	public void verifyIdTestThrowException() {
		vuMock.verifyId(-30);
	}
	@Test
	public void verifyIdNoThrowException() {
		vuMock.verifyId(5);
		verify(vuMock).verifyId(5);
	}
	@Test
	public void verifyListModuleIdTestRuns() {
		List<Integer> modulesIds = new ArrayList<Integer>();
		modulesIds.add(5);
		vuMock.verifyListModuleId(modulesIds);
	}
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
