package com.revature.util;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component
@Aspect
public class ExceptionAspect { //Aspect handling exceptions

	public final Logger logger = LoggerFactory.getLogger(this.getClass());
	@AfterThrowing(pointcut = "LogException()", throwing = "e")
	public void LogException(Exception e) {
		logger.error("There was an issue in " + "\n"); //Logger for identifying issues
		logger.error(e.getMessage());
	}

	@Pointcut("@annotation(com.revature.util.LogException)")
	void LogException() {
	}
}
