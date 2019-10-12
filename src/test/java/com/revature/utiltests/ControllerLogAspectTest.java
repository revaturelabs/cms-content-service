package com.revature.utiltests;

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
import org.testng.annotations.Test;

import com.revature.util.ControllerLogAspect;
import com.revature.util.Logging;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;


public class ControllerLogAspectTest extends AbstractTestNGSpringContextTests{
	
	
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
    @Test
    public void logContentCreation_Test() {
    	Logger classLogger = (Logger) LoggerFactory.getLogger(Logging.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        classLogger.addAppender(listAppender);
        controllerLogAspect.logContentCreation(joinPoint);
        List<ILoggingEvent> classLoggerLogs = listAppender.list;
        assertEquals("A piece of content has been created joinPoint", classLoggerLogs.get(0).getMessage());
        assertEquals(Level.INFO, classLoggerLogs.get(0).getLevel());
    }
    @Test
    public void logContentGetAll_Test() {
    	Logger classLogger = (Logger) LoggerFactory.getLogger(Logging.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        classLogger.addAppender(listAppender);
        controllerLogAspect.logContentGetAll(joinPoint);
        List<ILoggingEvent> classLoggerLogs = listAppender.list;
        assertEquals("Returning all Content joinPoint", classLoggerLogs.get(0).getMessage());
        assertEquals(Level.INFO, classLoggerLogs.get(0).getLevel());
    }
    @Test
    public void logContentGetId_Test() {
    	Logger classLogger = (Logger) LoggerFactory.getLogger(Logging.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        classLogger.addAppender(listAppender);
        controllerLogAspect.logContentGetId(joinPoint);
        List<ILoggingEvent> classLoggerLogs = listAppender.list;
        assertEquals("Returning Content by id joinPoint", classLoggerLogs.get(0).getMessage());
        assertEquals(Level.INFO, classLoggerLogs.get(0).getLevel());
    }
    @Test
    public void logModuleCreation_Test() {
    	Logger classLogger = (Logger) LoggerFactory.getLogger(Logging.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        classLogger.addAppender(listAppender);
        controllerLogAspect.logModuleCreation(joinPoint);
        List<ILoggingEvent> classLoggerLogs = listAppender.list;
        assertEquals("A Module has been created joinPoint", classLoggerLogs.get(0).getMessage());
        assertEquals(Level.INFO, classLoggerLogs.get(0).getLevel());
    }
    @Test
    public void logModuleGetAll_Test() {
    	Logger classLogger = (Logger) LoggerFactory.getLogger(Logging.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        classLogger.addAppender(listAppender);
        controllerLogAspect.logModuleGetAll(joinPoint);
        List<ILoggingEvent> classLoggerLogs = listAppender.list;
        assertEquals("Returning all Modules joinPoint", classLoggerLogs.get(0).getMessage());
        assertEquals(Level.INFO, classLoggerLogs.get(0).getLevel());
    }
    @Test
    public void logModuleGetId_Test() {
    	Logger classLogger = (Logger) LoggerFactory.getLogger(Logging.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        classLogger.addAppender(listAppender);
        controllerLogAspect.logModuleGetId(joinPoint);
        List<ILoggingEvent> classLoggerLogs = listAppender.list;
        assertEquals("Returning Module by id joinPoint", classLoggerLogs.get(0).getMessage());
        assertEquals(Level.INFO, classLoggerLogs.get(0).getLevel());
    }
    @Test
    public void logSearch_Test() {
    	Logger classLogger = (Logger) LoggerFactory.getLogger(Logging.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        classLogger.addAppender(listAppender);
        controllerLogAspect.logSearch(joinPoint);
        List<ILoggingEvent> classLoggerLogs = listAppender.list;
        assertEquals("Reterned search results joinPoint", classLoggerLogs.get(0).getMessage());
        assertEquals(Level.INFO, classLoggerLogs.get(0).getLevel());
    }
    
    
    
}
