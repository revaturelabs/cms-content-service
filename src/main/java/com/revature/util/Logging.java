package com.revature.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class Logging {
		
		public final Logger logger = LoggerFactory.getLogger(this.getClass());
		
		

}