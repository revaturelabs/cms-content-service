package com.revature.util;

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
    
	/**
	 * This method tests
	 * {@link com.revature.util.ExceptionAspect#LogException() 
	 * LogException()}.
	 * This method assumes that a that an exception was thrown and passed into the LogException Method.
	 * The result expected is the controller log appended with the message generated from the logger when
	 * the program does NOT throw an exception.
	 */
    @Test
    public void LogExceptionTestLogMessage() {
    	List<ILoggingEvent> classLoggerLogs = listAppender.list;
        easpect.LogException(e);
        assertEquals("There was an issue in " + "\n", classLoggerLogs.get(0).getMessage());        
    }
    
	/**
	 * This method tests
	 * {@link com.revature.util.ExceptionAspect#LogException() 
	 * LogException()}.
	 * This method assumes that a that an exception was thrown and passed into the LogException Method.
	 * The result expected is the controller log appended with the message generated from the logger when
	 * the program throws the exception.
	 */
    @Test
    public void LogExceptionTestExceptionMessage() {
    	List<ILoggingEvent> classLoggerLogs = listAppender.list;
        easpect.LogException(e);
        assertEquals(e.getMessage(), classLoggerLogs.get(1).getMessage());
        
	/**
	 * This method tests
	 * {@link com.revature.util.ExceptionAspect#LogException() 
	 * LogException()}.
	 * This method assumes that a that an exception was thrown and passed into the LogException Method.
	 * The result expected is that Level.ERROR will return the same result as the ILoggingEvent.getLevel()
	 * after the program throws the exception.
	 */    
    }
    @Test
    public void LogExceptionTestLogLevel() {
    	List<ILoggingEvent> classLoggerLogs = listAppender.list;
        easpect.LogException(e);
        assertEquals(Level.ERROR,classLoggerLogs.get(1).getLevel());
        
    }
    //NO ASSERTION
    @Test
    public void LogException_Overload_Test() {    	
    	easpect.LogException();    	
    }
    
}









