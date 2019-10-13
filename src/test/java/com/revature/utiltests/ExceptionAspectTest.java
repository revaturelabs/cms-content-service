package com.revature.utiltests;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.mockito.Mockito.*;
import com.revature.util.ExceptionAspect;
import com.revature.util.Logging;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

public class ExceptionAspectTest {
	@Mock
	Exception e;
	
	@Spy
	@InjectMocks
	ExceptionAspect easpect;
	
	@Spy
	@InjectMocks
	Logging logging;
	
    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    
    @Test
    public void LogException_Test() {
    	
    	when(e.getMessage()).thenReturn("message");
    	Logger classLogger = (Logger) LoggerFactory.getLogger(Logging.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        classLogger.addAppender(listAppender);
        
        
        easpect.LogException(e);
        
        
    }
    
}









