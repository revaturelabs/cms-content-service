package com.revature.utiltests;


import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
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
	
	String badString =null;
	private static final int MAX_CHAR_LENGTH = 254;
	ValidationUtil vu;
	
	@Spy ValidationUtil vuMock;
	
	
	
	@BeforeTest
	void setup() {
		MockitoAnnotations.initMocks(this);
		for(int i=0;i<MAX_CHAR_LENGTH;i++) {
			badString =badString+"BAD";
		}
		vu= new  ValidationUtil();
		
	}
	
	
	
	@Test(expectedExceptions = InvalidContentException.class)
	public void verifyContent_TestContentEqualsNullThrowException() {
		Content content=null;
		vu.verifyContent(content);
	}
	
	
	
	
	@Test(expectedExceptions = InvalidContentException.class)
	public void verifyContent_TestSecondConditionThrowException() {
		Content content = ContentFactory.contentForExceptions(0);
		vu.verifyContent(content);
	}
	
	@Test(expectedExceptions = InvalidContentException.class)
	public void verifyContent_TestThirdConditionThrowException() {
		Content content = ContentFactory.contentForExceptions(1);
		vu.verifyContent(content);
	}
	
	
	@Test(expectedExceptions = InvalidContentException.class)
	public void verifyContent_TestFourthConditionThrowException() {
		Content content = ContentFactory.contentForExceptions(2);
		vu.verifyContent(content);
	}
	@Test(expectedExceptions = InvalidContentException.class)
	public void verifyContent_TestFifthConditionThrowException() {
		Content content = ContentFactory.contentForExceptions(3);
		vu.verifyContent(content);
	}
	@Test(expectedExceptions = InvalidContentException.class)
	public void verifyModule_TestFirstconditionThrowException() {
		Module module = null;
		vu.verifyModule(module);
	}
	@Test(expectedExceptions = InvalidModuleException.class)
	public void verifyModule_SecondConditionThrowException() {
		Module badModule = ContentFactory.moduleForException();
		vu.verifyModule(badModule);		
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
