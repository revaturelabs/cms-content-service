package com.revature.util;

import org.aspectj.lang.JoinPoint;
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
	@AfterThrowing("LogException()")
	public void LogException(JoinPoint jp) {
		System.out.println(jp.getArgs());
		log.logger.info(jp.toString() + "This has been logged and terminated.");
		
		
		
	}
		@Pointcut("@annotation(com.revature.util.ExceptionAspectAnnotation)")
		private void LogException() {
			
		}
	}

