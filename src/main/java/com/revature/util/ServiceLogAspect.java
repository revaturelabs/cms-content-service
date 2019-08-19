package com.revature.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServiceLogAspect {// Logging aspect that has a list of logs for various functions that are run

	@Autowired
	Logging log;

	@AfterReturning("execution(public * com.revature.services.ContentServiceImpl.createContent(..))")
	public void logContentService_createContent(JoinPoint jp) {
		log.logger.info(jp.getSignature().getName() + " has successfully created a content");
	}
	
	@AfterReturning("execution(public * com.revature.services.ContentServiceImpl.getAllContent(..))")
	public void logContentService_getAllContent(JoinPoint jp) {
		log.logger.info(jp.getSignature().getName() + " has successfully retrieved all contents");
	}
	
	@AfterReturning("execution(public * com.revature.services.ContentServiceImpl.getContentById(..))")
	public void logContentService_getContentById(JoinPoint jp) {
		log.logger.info(jp.getSignature().getName() + " has successfully retrieved a content");
	}
	
	@AfterReturning("execution(public * com.revature.services.SearchServiceImpl.filterContentByTitle(..))")
	public void logSearchService_filterContentByTitle(JoinPoint jp) {
		log.logger.info(jp.getSignature().getName() + " has successfully filtered a content by title");
	}
	
	@AfterReturning("execution(public * com.revature.services.SearchServiceImpl.filterContentByFormat(..))")
	public void logSearchService_filterContentByFormat(JoinPoint jp) {
		log.logger.info(jp.getSignature().getName() + " has successfully filtered a content by format");
	}
	
	@AfterReturning("execution(public * com.revature.services.SearchServiceImpl.filterContentBySubjects(..))")
	public void logSearchService_filterContentBySubjects(JoinPoint jp) {
		log.logger.info(jp.getSignature().getName() + " has successfully filtered a content by subsject");
	}
	
	@AfterReturning("execution(public * com.revature.services.SearchServiceImpl.getContentByModuleId(..))")
	public void logSearchService_getContentByModuleId(JoinPoint jp) {
		log.logger.info(jp.getSignature().getName() + " has successfully retrieved a content by module ID");
	}
	
	@AfterReturning("execution(public * com.revature.services.SearchServiceImpl.filter(..))")
	public void logSearchService_filter(JoinPoint jp) {
		log.logger.info(jp.getSignature().getName() + " has successfully filtered a content");
	}
	
	@AfterReturning("execution(public * com.revature.services.ModuleServiceImpl.getAllModules(..))")
	public void logModuleService_getAllModules(JoinPoint jp) {
		log.logger.info(jp.getSignature().getName() + " has successfully retrieved all the modules");
	}

	@AfterReturning("execution(public * com.revature.services.ModuleServiceImpl.getModuleById(..))")
	public void logModuleService_getModuleById(JoinPoint jp) {
		log.logger.info(jp.getSignature().getName() + " has successfully retrieved a module by ID");
	}
	
	@AfterReturning("execution(public * com.revature.services.ModuleServiceImpl.createModule(..))")
	public void logModuleService_createModule(JoinPoint jp) {
		log.logger.info(jp.getSignature().getName() + " has successfully created a module");
	}
}
