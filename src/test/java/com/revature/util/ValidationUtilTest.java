package com.revature.util;


import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertNull;
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
	public void verifyContent_TestContentEqualsNullThrowException() {
		content=null;
		vu.verifyContent(content);
	}
	@Test(expectedExceptions = InvalidContentException.class)
	public void verifyContent_TestSecondCondition_MoreThanMAX_CHAR_LENGTH() {
		content.setFormat(badString);
		vu.verifyContent(content);
	}
	@Test(expectedExceptions = InvalidContentException.class)
	public void verifyContent_TestSecondCondition_GetFormatIsEmpty() {
		content.setFormat("");
		vu.verifyContent(content);
	}
	
	@Test(expectedExceptions = InvalidContentException.class)
	public void verifyContent_TestThirdCondition_MoreThanMAX_CHAR_LENGTH() {
		content.setTitle(badString);
		vu.verifyContent(content);
	}
	@Test(expectedExceptions = InvalidContentException.class)
	public void verifyContent_TestThirdCondition_GetTitleIsEmpty() {
		content.setTitle("");
		vu.verifyContent(content);
	}
	@Test(expectedExceptions = InvalidContentException.class)
	public void verifyContent_TestFourthCondition_MoreThanMAX_CHAR_LENGTH() {
		content.setUrl(badString);
		vu.verifyContent(content);
	}
	@Test(expectedExceptions = InvalidContentException.class)
	public void verifyContent_TestFourthCondition_GetUrlIsEmpty() {
		content.setUrl("");
		vu.verifyContent(content);
	}
	@Test(expectedExceptions = InvalidContentException.class)
	public void verifyContent_TestFifthConditionThrowException() {
		content.setDescription(badString);
		vuMock.verifyContent(content);
	}
	@Test
	public void verifyContent_Test() {
		assertTrue(vuMock.verifyContent(content));
	}
	
	
	@Test(expectedExceptions = InvalidContentException.class)
	public void verifyModule_TestFirstconditionThrowException() {
		module = null;
		vu.verifyModule(module);
	}
	@Test(expectedExceptions = InvalidModuleException.class)
	public void verifyModule_SecondCondition_MoreThanMAX_CHAR_LENGTH() {
		module.setSubject(badString);
		vu.verifyModule(module);		
	}
	@Test(expectedExceptions = InvalidModuleException.class)
	public void verifyModule_SecondCondition_getSubjectIsEmpty() {
		module.setSubject("");
		vu.verifyModule(module);		
	}
	@Test
	public void verifyModule_Test() {
		module.setSubject("asdads");
		vu.verifyModule(module);
	}
		
	@Test(expectedExceptions = InvalidSearchException.class)
	public void verifyStringTitle_TestThrowException() {
		vu.verifyStringTitle(badString);		
	}
	@Test(expectedExceptions = InvalidSearchException.class)
	public void verifyStringFortmat_TestThrowException() {
		vu.verifyStringFormat(badString);		
	}
	@Test(expectedExceptions = InvalidSearchException.class)
	public void verifyModuleId_TestThrowException() {
		vu.verifyModuleId(-20);
	}
	@Test(expectedExceptions =  InvalidSearchException.class)
	public void verifyId_TestThrowException() {
		vu.verifyId(-30);
	}
	@Test
	public void verifyId_NoThrowException() {
		vuMock.verifyId(5);
		verify(vuMock).verifyId(5);
	}
	@Test
	public void verifyListModuleId_TestRuns() {
		List<Integer> modulesIds = new ArrayList<Integer>();
		modulesIds.add(5);
		vu.verifyListModuleId(modulesIds);
	}
	@Test
	public void verifyFilter_Test() {
		String title = "1";
		String format = "sf" ;
		List<Integer> moduleIds = new ArrayList<Integer>();  
		moduleIds.add(1);
		vuMock.verifyFilter(title, format, moduleIds);
		verify(vuMock).verifyStringTitle(title);
	}
	
}
