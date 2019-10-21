package com.revature.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component; 

@Component
@Aspect
public class ServiceLogAspect {

	public final Logger logger = LoggerFactory.getLogger(this.getClass());

	@AfterReturning("execution(public * com.revature.services.ContentServiceImpl.createContent(..))")
	public void logContentServiceCreateContent(JoinPoint jp) {
		logger.info(jp.getSignature().getName() + " has successfully created a content");
	}
	
	@AfterReturning("execution(public * com.revature.services.ContentServiceImpl.getAllContent(..))")
	public void logContentServiceGetAllContent(JoinPoint jp) {
		logger.info(jp.getSignature().getName() + " has successfully retrieved all contents");
	}
	
	@AfterReturning("execution(public * com.revature.services.ContentServiceImpl.getContentById(..))")
	public void logContentServiceGetContentById(JoinPoint jp) {
		logger.info(jp.getSignature().getName() + " has successfully retrieved a content");
	}
	
	@AfterReturning("execution(public * com.revature.services.SearchServiceImpl.filterContentByTitle(..))")
	public void logSearchService_filterContentByTitle(JoinPoint jp) {
		logger.info(jp.getSignature().getName() + " has successfully filtered a content by title");
	}
	
	@AfterReturning("execution(public * com.revature.services.SearchServiceImpl.filterContentByFormat(..))")
	public void logSearchServiceFilterContentByFormat(JoinPoint jp) {
		logger.info(jp.getSignature().getName() + " has successfully filtered a content by format");
	}
	
	@AfterReturning("execution(public * com.revature.services.SearchServiceImpl.filterContentBySubjects(..))")
	public void logSearchServiceFilterContentBySubjects(JoinPoint jp) {
		logger.info(jp.getSignature().getName() + " has successfully filtered a content by subject");
	}
	
	@AfterReturning("execution(public * com.revature.services.SearchServiceImpl.getContentByModuleId(..))")
	public void logSearchServiceGetContentByModuleId(JoinPoint jp) {
		logger.info(jp.getSignature().getName() + " has successfully retrieved a content by module ID");
	}
	
	@AfterReturning("execution(public * com.revature.services.SearchServiceImpl.filter(..))")
	public void logSearchServiceFilter(JoinPoint jp) {
		logger.info(jp.getSignature().getName() + " has successfully filtered a content");
	}
	
	@AfterReturning("execution(public * com.revature.services.ModuleServiceImpl.getAllModules(..))")
	public void logModuleServiceGetAllModules(JoinPoint jp) {
		logger.info(jp.getSignature().getName() + " has successfully retrieved all the modules");
	}

	@AfterReturning("execution(public * com.revature.services.ModuleServiceImpl.getModuleById(..))")
	public void logModuleServiceGetModuleById(JoinPoint jp) {
		logger.info(jp.getSignature().getName() + " has successfully retrieved a module by ID");
	}
	
	@AfterReturning("execution(public * com.revature.services.ModuleServiceImpl.createModule(..))")
	public void logModuleServiceCreateModule(JoinPoint jp) {
		logger.info(jp.getSignature().getName() + " has successfully created a module");
	}
}
