package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.util.LogException;

@CrossOrigin(origins = "*", allowCredentials = "true")
@Transactional
@RestController
/**
 * This is a controller which allows users to view the log created by the jenkins back end. Every time a new build is run
 * it is important to make sure that the html logging file is empty.
 * The file used here is the absolute path on the EC2. It will likely need to change.
 */
public class LogController {
	
	@LogException
	@GetMapping("/log")
	public String getLog() throws IOException {
		StringBuilder log = new StringBuilder("");
	     try (BufferedReader br = Files.newBufferedReader(Paths.get("/home/ec2-user/.jenkins/workspace/CMSforce/CMSforce/src/main/resources/ErrorLog.html"))){
	    	 String line;
	    		 while((line = br.readLine())!=null) {
	    			 log.append("\n");
	    			 log.append(line);
	    		 }
		return log.toString();

}finally{
	
}
	
}
}
