package com.revature.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ControllerLogAspect { //Logging for the controllers
	
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@AfterReturning("execution(public * com.revature.controllers.ContentController.createContent(..))")
	public void logContentCreation(JoinPoint jp) {
		log.info("A piece of content has been created " + jp.toString());
	}
	
	@AfterReturning("execution(public * com.revature.controllers.ContentController.getAllContent(..))")
	public void logContentGetAll(JoinPoint jp) {
		log.info("Returning all Content " + jp.toString());
	}
	
	@AfterReturning("execution(public * com.revature.controllers.ContentController.getContentById(..))")
	public void logContentGetId(JoinPoint jp) {
		log.info("Returning Content by id " + jp.toString());
	}

	@AfterReturning("execution(public * com.revature.controllers.ModuleController.createModule(..))")
	public void logModuleCreation(JoinPoint jp) {
		log.info("A Module has been created " + jp.toString());
	}
	
	@AfterReturning("execution(public * com.revature.controllers.ModuleController.getAllModules(..))")
	public void logModuleGetAll(JoinPoint jp) {
		log.info("Returning all Modules " + jp.toString());
	}
	
	@AfterReturning("execution(public * com.revature.controllers.ModuleController.getModuleById(..))")
	public void logModuleGetId(JoinPoint jp) {
		log.info("Returning Module by id " + jp.toString());
	}
	
	@AfterReturning("execution(public * com.revature.controllers.ContentController.getSearchResults(..))")
	public void logSearch(JoinPoint jp) {
		log.info("Reterned search results "+ jp.toString());
	}
}
