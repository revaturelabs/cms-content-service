package com.revature.util;

import static org.testng.Assert.assertEquals;
import java.util.List;
import org.aspectj.lang.JoinPoint;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.revature.util.ControllerLogAspect;
import com.revature.util.Logging;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

@Component
public class ControllerLogAspectTest extends AbstractTestNGSpringContextTests{
	
	private Logger classLogger = (Logger) LoggerFactory.getLogger(Logging.class);
	private ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
	@Mock
	JoinPoint joinPoint;
	@InjectMocks
	ControllerLogAspect controllerLogAspect;
    @Spy
    @InjectMocks
    Logging logging;
    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    @BeforeMethod
    public void init() {
    	listAppender = new ListAppender<>();
    	listAppender.start();
    	classLogger.detachAndStopAllAppenders();
    	classLogger.addAppender(listAppender);
    }
    @Test
    public void logContentCreation_Test() {    	
    	List<ILoggingEvent> classLoggerLogs = listAppender.list;
    	controllerLogAspect.logContentCreation(joinPoint);
        assertEquals("A piece of content has been created joinPoint", classLoggerLogs.get(0).getMessage());
        assertEquals(Level.INFO, classLoggerLogs.get(0).getLevel());
    }
    @Test
    public void logContentGetAll_Test() {
    	List<ILoggingEvent> classLoggerLogs = listAppender.list;
        controllerLogAspect.logContentGetAll(joinPoint);
        assertEquals("Returning all Content joinPoint", classLoggerLogs.get(0).getMessage());
        assertEquals(Level.INFO, classLoggerLogs.get(0).getLevel());
    }
    @Test
    public void logContentGetId_Test() {
    	List<ILoggingEvent> classLoggerLogs = listAppender.list;
        controllerLogAspect.logContentGetId(joinPoint);
        assertEquals("Returning Content by id joinPoint", classLoggerLogs.get(0).getMessage());
        assertEquals(Level.INFO, classLoggerLogs.get(0).getLevel());
    }
    @Test
    public void logModuleCreation_Test() {
    	List<ILoggingEvent> classLoggerLogs = listAppender.list;
        controllerLogAspect.logModuleCreation(joinPoint);
        assertEquals("A Module has been created joinPoint", classLoggerLogs.get(0).getMessage());
        assertEquals(Level.INFO, classLoggerLogs.get(0).getLevel());
    }
    @Test
    public void logModuleGetAll_Test() {
    	List<ILoggingEvent> classLoggerLogs = listAppender.list;
        controllerLogAspect.logModuleGetAll(joinPoint);
        assertEquals("Returning all Modules joinPoint", classLoggerLogs.get(0).getMessage());
        assertEquals(Level.INFO, classLoggerLogs.get(0).getLevel());
    }
    @Test
    public void logModuleGetId_Test() {
    	List<ILoggingEvent> classLoggerLogs = listAppender.list;
        controllerLogAspect.logModuleGetId(joinPoint);
        assertEquals("Returning Module by id joinPoint", classLoggerLogs.get(0).getMessage());
        assertEquals(Level.INFO, classLoggerLogs.get(0).getLevel());
    }
    @Test
    public void logSearch_Test() {
    	List<ILoggingEvent> classLoggerLogs = listAppender.list;
    	controllerLogAspect.logSearch(joinPoint);
        assertEquals("Reterned search results joinPoint", classLoggerLogs.get(0).getMessage());
        assertEquals(Level.INFO, classLoggerLogs.get(0).getLevel());
    }
    
    
    
}
