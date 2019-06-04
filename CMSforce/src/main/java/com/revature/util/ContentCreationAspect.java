package com.revature.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ContentCreationAspect {
	
	@Autowired
	Logging log;
	
	@AfterReturning("LogCreation()")
	public void LogAnnotation(JoinPoint jp) {
		
		log.logger.info("A piece of content has been created " + jp.toString());
		
	}
	
	@Pointcut("@annotation(com.revature.util.LogCreation)")
	private void LogCreation() {
		
	}
	
	

}
