package com.revature.util;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
@Aspect
public class ExceptionAspect { //Aspect handling exceptions

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@AfterThrowing(pointcut = "LogException()", throwing = "e")
	public void LogException(Exception e) {
		log.error("There was an issue in " + "\n"); //Logger for identifying issues
		log.error(e.getMessage());
		
		
	}
		@Pointcut("@annotation(com.revature.util.LogException)")
		private void LogException() {
			
		}
	}

