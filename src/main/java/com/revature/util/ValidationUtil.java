package com.revature.util;

import java.util.List;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.entities.Content;
import com.revature.entities.Module;
import com.revature.exceptions.InvalidContentException;
import com.revature.exceptions.InvalidModuleException;
import com.revature.exceptions.InvalidSearchException;

@Component
@Aspect
public class ValidationUtil {
	
	private static final int MAX_CHAR_LENGTH = 254;
	
	@Pointcut("within(com.revature.services..*) ")//Pointcut location
	public void servicesPC() {
		
	}
	
	@LogException
	@Before("servicesPC() && args(content,..)")
	public void verifyContent(Content content) { //Method for throwing exceptions for content issues
		if(content == null)
			throw new InvalidContentException("content is of null value");
		
		if(content.getFormat().isEmpty() || content.getFormat().length() > MAX_CHAR_LENGTH)
			throw new InvalidContentException("Format is either empty or is longer than 254 characters.");
		
		if(content.getTitle().isEmpty() || content.getTitle().length() > MAX_CHAR_LENGTH)
			throw new InvalidContentException("Title is either empty or is longer than 254 characters.");
		
		if(content.getUrl().isEmpty() || content.getUrl().length() > MAX_CHAR_LENGTH)
			throw new InvalidContentException("URL is either empty or is longer than 254 characters.");
		
		if(content.getDescription().length() > MAX_CHAR_LENGTH)
			throw new InvalidContentException("Description is longer than 254 characters.");
	}
	
	@LogException
	@Before("servicesPC() && args(module,..)")
	public void verifyModule(Module module) { //Method for throwing exceptions if modules are empty or subjects are empty
		if(module == null)
			throw new InvalidContentException("Module is of null value");
		
		if(module.getSubject().isEmpty() || module.getSubject().length() > MAX_CHAR_LENGTH)
			throw new InvalidModuleException("Subject is empty or is longer than 254 characters.");
	}
	
	@LogException
	@Before("servicesPC() && args(title,..)")
	public void verifyStringTitle(String title) { //Exception thrown when titles exceed maximum length
		if(title.length() > MAX_CHAR_LENGTH)
			throw new InvalidSearchException("title is longer than 254 characters.");
	}
	
	@LogException
	@Before("servicesPC() && args(format,..)")
	public void verifyStringFormat(String format) { //Same as above but with formats
		if(format.length() > MAX_CHAR_LENGTH)
			throw new InvalidSearchException("format is longer than 254 characters.");
	}
	
	@LogException
	@Before("servicesPC() && args(moduleId,..)")
	public void verifyModuleId(int moduleId) { //Exception thrown if there is an issue with the ID index location
		if(moduleId <= 0)
			throw new InvalidSearchException("the ModuleId is not a valid index.");
	}
	
	@LogException
	@Before("servicesPC() && args(moduleIds,..)")
	public void verifyListModuleId(List<Integer> moduleIds) { //Verifying modules match their ID's
		for(int x = 0; x < moduleIds.size(); x++) {
			this.verifyModuleId(moduleIds.get(x));
		}
	}
	
	@LogException
	@Before("servicesPC() && args(title, format, moduleIds,..)")
	public void verifyFilter(String title, String format, List<Integer> moduleIds) {
		this.verifyStringTitle(title);
		this.verifyStringFormat(format);
		this.verifyListModuleId(moduleIds);
	}
	
	@LogException
	@Before("servicesPC() && args(id,..)")
	public void verifyId(int id) {
		if(id <= 0)
			throw new InvalidSearchException("the id is not a valid index.");
	}

}
