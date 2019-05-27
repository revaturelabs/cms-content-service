package com.revature.util;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExceptionAspect {

	@AfterThrowing("LogException()")
	public void LogException(JoinPoint jp) {
		System.out.println(jp.getArgs());
		Logger.getLogger(jp.toString());
		
		
		
		
	}
		@Pointcut("@annotation(com.revature.util.ExceptionAspectAnnotation)")
		private void LogException() {
			
		}
	}

