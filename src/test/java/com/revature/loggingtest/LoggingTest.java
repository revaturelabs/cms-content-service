package com.revature.loggingtest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

	 
public class LoggingTest {


	    private static Logger LOGGER = null;
	     
	    @BeforeAll
	    public static void setLogger()
	    {
	        System.setProperty("log4j.configurationFile","log4j.properties");
	        LOGGER = LogManager.getLogger();
	    }
	     
	    @Test
	    public void testOne()
	    {
	        LOGGER.debug("Debug Message Logged !!!");
	        LOGGER.info("Info Message Logged !!!");
	        LOGGER.error("Error Message Logged !!!", new NullPointerException("NullError"));
	    }
	}

