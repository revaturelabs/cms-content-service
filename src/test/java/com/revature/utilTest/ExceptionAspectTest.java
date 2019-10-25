package com.revature.utilTest;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.revature.util.ExceptionAspect;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

public class ExceptionAspectTest {
	private Logger classLogger =  (Logger) LoggerFactory.getLogger(ExceptionAspect.class);
	private ListAppender<ILoggingEvent> listAppender = new ListAppender<>();	
	ExceptionAspect eAspectReflex = new ExceptionAspect();
	@Mock
	Exception e;
	
	
	@InjectMocks
	ExceptionAspect easpect;
	

	
    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Mock
    ExceptionAspect eAspct;
    
    @BeforeMethod
    public void init() {
    	listAppender = new ListAppender<>();
    	listAppender.start();
    	classLogger.detachAndStopAllAppenders();
    	classLogger.addAppender(listAppender);
    	when(e.getMessage()).thenReturn("message");
    	
    }   
    
    
    @Test
    public void LogExceptionTestLogMessage() {
    	List<ILoggingEvent> classLoggerLogs = listAppender.list;
        easpect.LogException(e);
        assertEquals("There was an issue in " + "\n", classLoggerLogs.get(0).getMessage());        
    }
    
    @Test
    public void LogExceptionTestExceptionMessage() {
    	List<ILoggingEvent> classLoggerLogs = listAppender.list;
        easpect.LogException(e);
        assertEquals(e.getMessage(), classLoggerLogs.get(1).getMessage());
        
    }
    @Test
    public void LogExceptionTestLogLevel() {
    	List<ILoggingEvent> classLoggerLogs = listAppender.list;
        easpect.LogException(e);
        assertEquals(Level.ERROR,classLoggerLogs.get(1).getLevel());
        
    }
    @Test
    public void LogException_Overload_Test() {    	
    	easpect.LogException();    	
    }
    
}









