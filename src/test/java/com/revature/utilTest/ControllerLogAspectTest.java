package com.revature.utilTest;

import static org.testng.Assert.assertEquals;
import java.util.List;
import org.aspectj.lang.JoinPoint;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.revature.util.ControllerLogAspect;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

@Component
public class ControllerLogAspectTest extends AbstractTestNGSpringContextTests {

	private Logger classLogger = (Logger) LoggerFactory.getLogger(ControllerLogAspect.class);
	private ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
	@Mock
	JoinPoint joinPoint;
	@InjectMocks
	ControllerLogAspect controllerLogAspect;

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

	/**
	 * This method tests
	 * {@link com.revature.util.ControllerLogAspect#logContentCreation(Joinpoint jp) 
	 * logContentCreation(Joinpoint jp)}.
	 * This method assumes that a joinpoint will be passed in.
	 * The result expected is the controller log appended with the joinpoint in string form.
	 */
	@Test
	public void logContentCreationTestMessage() {
		List<ILoggingEvent> classLoggerLogs = listAppender.list;
		controllerLogAspect.logContentCreation(joinPoint);
		assertEquals("A piece of content has been created joinPoint", classLoggerLogs.get(0).getMessage());
	}

	/**
	 * This method tests
	 * {@link com.revature.util.ControllerLogAspect#logContentCreation(Joinpoint jp) 
	 * logContentCreation(Joinpoint jp)}.
	 * logContentCreation
	 * This method assumes that a joinpoint will be passed in.
	 * The result expected is that Level.INFO will return the same result as the ILoggingEvent.getLevel()
	 * after passing in the joinpoint to the mock LogAspect.
	 */
	@Test
	public void logContentCreationTestLogLevel() {
		List<ILoggingEvent> classLoggerLogs = listAppender.list;
		controllerLogAspect.logContentCreation(joinPoint);
		assertEquals(Level.INFO, classLoggerLogs.get(0).getLevel());
	}

	/**
	 * This method tests
	 * {@link com.revature.util.ControllerLogAspect#logContentGetAll(Joinpoint jp) 
	 * logContentGetAll(Joinpoint jp)}.
	 * logContentGetAll
	 * This method assumes that a joinpoint will be passed in.
	 *The result expected is the controller log appended with the joinpoint in string form to make sure the 
	 *logging works as intended for this method.
	 */
	@Test
	public void logContentGetAllTestMessage() {
		List<ILoggingEvent> classLoggerLogs = listAppender.list;
		controllerLogAspect.logContentGetAll(joinPoint);
		assertEquals("Returning all Content joinPoint", classLoggerLogs.get(0).getMessage());
	}

	/**
	 * This method tests
	 * {@link com.revature.util.ControllerLogAspect#logContentCreation(Joinpoint jp) 
	 * logContentCreation(Joinpoint jp)}.
	 * logContentCreation
	 * This method assumes that a joinpoint will be passed in.
	 * The result expected is that Level.INFO will return the same result as the ILoggingEvent.getLevel()
	 * after passing in the joinpoint to the mock LogAspect.
	 */
	@Test
	public void logContentGetAllTestLogLevel() {
		List<ILoggingEvent> classLoggerLogs = listAppender.list;
		controllerLogAspect.logContentGetAll(joinPoint);
		assertEquals(Level.INFO, classLoggerLogs.get(0).getLevel());
	}

	/**
	 * This method tests
	 * {@link com.revature.util.ControllerLogAspect#logContentGetId(Joinpoint jp) 
	 * logContentGetId(Joinpoint jp)}.
	 * logContentGetId
	 * This method assumes that a joinpoint will be passed in.
	 * The result expected is the controller log appended with the joinpoint in string form to make sure the 
	 *logging works as intended for this method.
	 */
	@Test
	public void logContentGetIdTestMessage() {
		List<ILoggingEvent> classLoggerLogs = listAppender.list;
		controllerLogAspect.logContentGetId(joinPoint);
		assertEquals("Returning Content by id joinPoint", classLoggerLogs.get(0).getMessage());
	}

	/**
	 * This method tests
	 * {@link com.revature.util.ControllerLogAspect#logContentGetId(Joinpoint jp) 
	 * logContentGetId(Joinpoint jp)}.
	 * logContentGetId
	 * This method assumes that a joinpoint will be passed in.
	 * The result expected is that Level.INFO will return the same result as the ILoggingEvent.getLevel()
	 * after passing in the joinpoint to the mock LogAspect.
	 */
	@Test
	public void logContentGetIdTestLogLevel() {
		List<ILoggingEvent> classLoggerLogs = listAppender.list;
		controllerLogAspect.logContentGetId(joinPoint);
		assertEquals(Level.INFO, classLoggerLogs.get(0).getLevel());
	}

	/**
	 * This method tests
	 * {@link com.revature.util.ControllerLogAspect#logContentModuleCreation(Joinpoint jp) 
	 * logContentModuleCreation(Joinpoint jp)}.
	 * logContentModuleCreation
	 * This method assumes that a joinpoint will be passed in.
	 * The result expected is the controller log appended with the joinpoint in string form to make sure the 
	 *logging works as intended for this method.
	 */
	@Test
	public void logModuleCreationTestMessage() {
		List<ILoggingEvent> classLoggerLogs = listAppender.list;
		controllerLogAspect.logModuleCreation(joinPoint);
		assertEquals("A Module has been created joinPoint", classLoggerLogs.get(0).getMessage());
	}

	/**
	 * This method tests
	 * {@link com.revature.util.ControllerLogAspect#logContentModuleCreation(Joinpoint jp) 
	 * logContentModuleCreation(Joinpoint jp)}.
	 * logContentModuleCreation
	 * This method assumes that a joinpoint will be passed in.
	 * The result expected is that Level.INFO will return the same result as the ILoggingEvent.getLevel()
	 * after passing in the joinpoint to the mock LogAspect.
	 */
	@Test
	public void logModuleCreationTestLogLevel() {
		List<ILoggingEvent> classLoggerLogs = listAppender.list;
		controllerLogAspect.logModuleCreation(joinPoint);
		assertEquals(Level.INFO, classLoggerLogs.get(0).getLevel());
	}

	/**
	 * This method tests
	 * {@link com.revature.util.ControllerLogAspect#logContentGetAll(Joinpoint jp) 
	 * logContentModuleGetAll(Joinpoint jp)}.
	 * logContentModuleGetAll
	 * This method assumes that a joinpoint will be passed in.
	 * The result expected is the controller log appended with the joinpoint in string form to make sure the 
	 *logging works as intended for this method.
	 */
	@Test
	public void logModuleGetAllTestMessage() {
		List<ILoggingEvent> classLoggerLogs = listAppender.list;
		controllerLogAspect.logModuleGetAll(joinPoint);
		assertEquals("Returning all Modules joinPoint", classLoggerLogs.get(0).getMessage());
	}

	/**
	 * This method tests
	 * {@link com.revature.util.ControllerLogAspect#logContentGetAll(Joinpoint jp) 
	 * logContentModuleGetAll(Joinpoint jp)}.
	 * logContentModuleGetAll
	 * This method assumes that a joinpoint will be passed in.
	 * The result expected is that Level.INFO will return the same result as the ILoggingEvent.getLevel()
	 * after passing in the joinpoint to the mock LogAspect.
	 */
	@Test
	public void logModuleGetAllTestLogLevel() {
		List<ILoggingEvent> classLoggerLogs = listAppender.list;
		controllerLogAspect.logModuleGetAll(joinPoint);
		assertEquals(Level.INFO, classLoggerLogs.get(0).getLevel());
	}

	/**
	 * This method tests
	 * {@link com.revature.util.ControllerLogAspect#logContentGetId(Joinpoint jp) 
	 * logContentModuleGetId(Joinpoint jp)}.
	 * logContentModuleGetId
	 * This method assumes that a joinpoint will be passed in.
	 * The result expected is the controller log appended with the joinpoint in string form to make sure the 
	 *logging works as intended for this method.
	 */
	@Test
	public void logModuleGetIdTestMessage() {
		List<ILoggingEvent> classLoggerLogs = listAppender.list;
		controllerLogAspect.logModuleGetId(joinPoint);
		assertEquals("Returning Module by id joinPoint", classLoggerLogs.get(0).getMessage());
	}

	/**
	 * This method tests
	 * {@link com.revature.util.ControllerLogAspect#logContentGetIdJoinpoint jp) 
	 * logContentModuleGetId(Joinpoint jp)}.
	 * logContentModuleGetId
	 * This method assumes that a joinpoint will be passed in.
	 * The result expected is that Level.INFO will return the same result as the ILoggingEvent.getLevel()
	 * after passing in the joinpoint to the mock LogAspect.
	 */
	@Test
	public void logModuleGetIdTestLogLEvel() {
		List<ILoggingEvent> classLoggerLogs = listAppender.list;
		controllerLogAspect.logModuleGetId(joinPoint);
		assertEquals(Level.INFO, classLoggerLogs.get(0).getLevel());
	}

	/**
	 * This method tests
	 * {@link com.revature.util.ControllerLogAspect#logSearch(Joinpoint jp) 
	 * logContentModuleGetId(Joinpoint jp)}.
	 * logContentModuleGetId
	 * This method assumes that a joinpoint will be passed in.
	 * The result expected is the controller log appended with the joinpoint in string form to make sure the 
	 *logging works as intended for this method.
	 */
	@Test
	public void logSearchTestMessage() {
		List<ILoggingEvent> classLoggerLogs = listAppender.list;
		controllerLogAspect.logSearch(joinPoint);
		assertEquals("Reterned search results joinPoint", classLoggerLogs.get(0).getMessage());
	}

	/**
	 * This method tests
	 * {@link com.revature.util.ControllerLogAspect#logSearch(Joinpoint jp) 
	 * logSearch(Joinpoint jp)}.
	 * logSearch
	 * This method assumes that a joinpoint will be passed in.
	 * The result expected is that Level.INFO will return the same result as the ILoggingEvent.getLevel()
	 * after passing in the joinpoint to the mock LogAspect.
	 */
	@Test
	public void logSearchTestLogLevel() {
		List<ILoggingEvent> classLoggerLogs = listAppender.list;
		controllerLogAspect.logSearch(joinPoint);
		assertEquals(Level.INFO, classLoggerLogs.get(0).getLevel());
	}

}
