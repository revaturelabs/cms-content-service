package com.revature.util;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
@Aspect
public class ExceptionAspect {
@Autowired
Logging log;
	@AfterThrowing(pointcut = "LogException()", throwing = "e")
	public void LogException(Exception e) {
		log.logger.error("There was an issue in " + "\n");
		log.logger.error(e.getMessage());
		
		
	}
		@Pointcut("@annotation(com.revature.util.LogException)")
		private void LogException() {
			
		}
	}

