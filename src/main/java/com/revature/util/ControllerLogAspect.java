package com.revature.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component
@Aspect
public class ControllerLogAspect { //Logging for the controllers
	
	

//	@Autowired
//	Logging log;
	public final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@AfterReturning("execution(public * com.revature.controllers.ContentController.createContent(..))")
	public void logContentCreation(JoinPoint jp) {
		logger.info("A piece of content has been created " + jp.toString());
	}
	
	@AfterReturning("execution(public * com.revature.controllers.ContentController.getAllContent(..))")
	public void logContentGetAll(JoinPoint jp) {
		logger.info("Returning all Content " + jp.toString());
	}
	
	@AfterReturning("execution(public * com.revature.controllers.ContentController.getContentById(..))")
	public void logContentGetId(JoinPoint jp) {
		logger.info("Returning Content by id " + jp.toString());
	}

	@AfterReturning("execution(public * com.revature.controllers.ModuleController.createModule(..))")
	public void logModuleCreation(JoinPoint jp) {
		logger.info("A Module has been created " + jp.toString());
	}
	
	@AfterReturning("execution(public * com.revature.controllers.ModuleController.getAllModules(..))")
	public void logModuleGetAll(JoinPoint jp) {
		logger.info("Returning all Modules " + jp.toString());
	}
	
	@AfterReturning("execution(public * com.revature.controllers.ModuleController.getModuleById(..))")
	public void logModuleGetId(JoinPoint jp) {
		logger.info("Returning Module by id " + jp.toString());
	}
	
	@AfterReturning("execution(public * com.revature.controllers.ContentController.getSearchResults(..))")
	public void logSearch(JoinPoint jp) {
		logger.info("Reterned search results "+ jp.toString());
	}
}
