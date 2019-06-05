package com.revature.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.util.LogException;

@CrossOrigin(origins = "*", allowCredentials = "true")
@Transactional
@RestController
public class LogController {

	@LogException
	@RequestMapping("/log")
	public String getLog() {
		StringBuilder log = new StringBuilder("");
	     try (BufferedReader br = Files.newBufferedReader(Paths.get("../../CMSforce/src/main/resources/ErrorLog.html"))){
	    	 String line;
	    		 while((line = br.readLine())!=null) {
	    			 log.append("<");
	    			 log.append(line);
	    			 log.append(">");
	    		 }
		return log.toString();

} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
		return null;
}
}
