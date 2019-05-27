package com.revature.util;

import org.apache.log4j.Logger;

public class Logging {
		
		final public static Logger logger = Logger.getLogger(Logging.class);
		
		
		public static void log() {
			logger.info("information: stuff that is happening ");
			logger.warn("Warning: Something that is a concern");
			logger.error("This is for exceptions, not errors"); //your program is still running
			logger.debug("Debugging: for development");
			logger.fatal("This is for catastrophic failures. Your program crashed.");
			

}
}
