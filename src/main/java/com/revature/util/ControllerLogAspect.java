package com.revature.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ControllerLogAspect { //Logging for the controllers
	
	@Autowired
	Logging log;
	
	@AfterReturning("execution(public * com.revature.controllers.ContentController.createContent(..))")
	public void logContentCreation(JoinPoint jp) {
		log.logger.info("A piece of content has been created " + jp.toString());
	}
	
	@AfterReturning("execution(public * com.revature.controllers.ContentController.getAllContent(..))")
	public void logContentGetAll(JoinPoint jp) {
		log.logger.info("Returning all Content " + jp.toString());
	}
	
	@AfterReturning("execution(public * com.revature.controllers.ContentController.getContentById(..))")
	public void logContentGetId(JoinPoint jp) {
		log.logger.info("Returning Content by id " + jp.toString());
	}

	@AfterReturning("execution(public * com.revature.controllers.ModuleController.createModule(..))")
	public void logModuleCreation(JoinPoint jp) {
		log.logger.info("A Module has been created " + jp.toString());
	}
	
	@AfterReturning("execution(public * com.revature.controllers.ModuleController.getAllModules(..))")
	public void logModuleGetAll(JoinPoint jp) {
		log.logger.info("Returning all Modules " + jp.toString());
	}
	
	@AfterReturning("execution(public * com.revature.controllers.ModuleController.getModuleById(..))")
	public void logModuleGetId(JoinPoint jp) {
		log.logger.info("Returning Module by id " + jp.toString());
	}
	
	@AfterReturning("execution(public * com.revature.controllers.SearchController.filter(..))")
	public void logSearch(JoinPoint jp) {
		log.logger.info("Reterned search results "+ jp.toString());
	}
}
