package com.revature.util;
import static org.testng.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.mockito.Mockito.*;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.FileAppender;
import ch.qos.logback.core.read.ListAppender;

@Component
public class ServiceLogAspectTest extends AbstractTestNGSpringContextTests {
	private Logger classLogger =  (Logger) LoggerFactory.getLogger(ServiceLogAspect.class);
	private ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
	@Mock
	ProceedingJoinPoint joinPoint;
	@Mock
	MethodSignature mSig;
	@InjectMocks
	ServiceLogAspect serviceLogAspect;

    @BeforeClass
    public void setup() {
    	serviceLogAspect = new ServiceLogAspect();
        MockitoAnnotations.initMocks(this);
        when(mSig.getName()).thenReturn("Method Signature");
        when(joinPoint.getSignature()).thenReturn(mSig);
        
    }
    @BeforeMethod
    public void init() {
    	listAppender = new ListAppender<>();
    	listAppender.start();
    	classLogger.detachAndStopAllAppenders();
    	classLogger.addAppender(listAppender);
    }    
    @Test
    public void logContentService_createContent_Test() {
    	serviceLogAspect.logContentServiceCreateContent(joinPoint);
        List<ILoggingEvent> classLoggerLogs = listAppender.list;
        assertEquals(mSig.getName() + " has successfully created a content", classLoggerLogs.get(0).getMessage());
    }
    @Test
    public void logContentService_getAllContent_Test() {
        serviceLogAspect.logContentServiceGetAllContent(joinPoint);
        List<ILoggingEvent> classLoggerLogs = listAppender.list;
        assertEquals(mSig.getName() + " has successfully retrieved all contents", classLoggerLogs.get(0).getMessage());
    }
    @Test
    public void logContentService_getContentById_Test() {
        serviceLogAspect.logContentServiceGetContentById(joinPoint);
        List<ILoggingEvent> classLoggerLogs = listAppender.list;
        assertEquals(mSig.getName() + " has successfully retrieved a content", classLoggerLogs.get(0).getMessage());
    }
    
    @Test
    public void logSearchService_filterContentByTitle_Test() {
        serviceLogAspect.logSearchService_filterContentByTitle(joinPoint);
        List<ILoggingEvent> classLoggerLogs = listAppender.list;
        assertEquals(mSig.getName() + " has successfully filtered a content by title", classLoggerLogs.get(0).getMessage());
    }
    
    @Test
    public void logSearchService_filterContentByFormat_Test() {
        serviceLogAspect.logSearchServiceFilterContentByFormat(joinPoint);
        List<ILoggingEvent> classLoggerLogs = listAppender.list;
        assertEquals(mSig.getName() + " has successfully filtered a content by format", classLoggerLogs.get(0).getMessage());
    }
    @Test
    public void logSearchService_filterContentBySubjects_Test() {
        serviceLogAspect.logSearchServiceFilterContentBySubjects(joinPoint);
        List<ILoggingEvent> classLoggerLogs = listAppender.list;
        assertEquals(mSig.getName() + " has successfully filtered a content by subject", classLoggerLogs.get(0).getMessage());
    }
    @Test
    public void logSearchService_getContentByModuleId_Test() {
        serviceLogAspect.logSearchServiceGetContentByModuleId(joinPoint);
        List<ILoggingEvent> classLoggerLogs = listAppender.list;
        assertEquals(mSig.getName() + " has successfully retrieved a content by module ID", classLoggerLogs.get(0).getMessage());
    }
    @Test
    public void logSearchService_filter_Test() {
        serviceLogAspect.logSearchServiceFilter(joinPoint);
        List<ILoggingEvent> classLoggerLogs = listAppender.list;
        assertEquals(mSig.getName() + " has successfully filtered a content", classLoggerLogs.get(0).getMessage());
    }
    @Test
    public void logModuleService_getAllModules_Test() {
        serviceLogAspect.logModuleServiceGetAllModules(joinPoint);
        List<ILoggingEvent> classLoggerLogs = listAppender.list;
        assertEquals(mSig.getName() + " has successfully retrieved all the modules", classLoggerLogs.get(0).getMessage());
    }
    @Test
    public void logModuleService_getModuleById_Test() {
        serviceLogAspect.logModuleServiceGetModuleById(joinPoint);
        List<ILoggingEvent> classLoggerLogs = listAppender.list;
        assertEquals(mSig.getName() + " has successfully retrieved a module by ID", classLoggerLogs.get(0).getMessage());
    }
    @Test
    public void logModuleService_createModule() {
        serviceLogAspect.logModuleServiceCreateModule(joinPoint);
        List<ILoggingEvent> classLoggerLogs = listAppender.list;
        assertEquals(mSig.getName() + " has successfully created a module", classLoggerLogs.get(0).getMessage());
    }
}
