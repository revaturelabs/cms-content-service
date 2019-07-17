package com.revature.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class Logging {
		
		public final Logger logger = LoggerFactory.getLogger(this.getClass());
		
		
		public void log() {
			logger.info("information: stuff that is happening ");
			logger.warn("Warning: Something that is a concern");
			logger.error("This is for exceptions, not errors"); //your program is still running
			logger.debug("Debugging: for development");
		

}
}
