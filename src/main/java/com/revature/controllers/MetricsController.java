package com.revature.controllers;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.Content;
import com.revature.entities.Module;
import com.revature.services.ContentService;
import com.revature.services.ModuleService;


@CrossOrigin
@RestController
@RequestMapping("/metrics")
public class MetricsController {
	// reports mapping for metrics
	@Autowired
	ModuleService moduleService;
	@Autowired
	ContentService contentService;

	/*
	 * Fills contentService variable with newest info from DB
	 * @return set of contents
	 * */
	private Set<Content> getNewestContent() {
		return contentService.getAllContent();
	}
	
	/*
	 * Fills moduleService variable with newest info from DB
	 * @return Set of modules
	 * 
	 * */
	private Set<Module> getNewestModule() {
		return moduleService.getAllModules();
	}
		
	
	
	/*
	 * Returns the number of Contents with format set to code
	 * @return count of code formats 
	 * */
	@GetMapping("/codeCount")
	public int getCountCodeEx(){
		int counter = 0;
		Iterator<Content> contents = getNewestContent().iterator();
		while(contents.hasNext()) {
			if(contents.next().getFormat().equals("Code")) {
				counter++;
			}
			
		}
		return counter;
	}
	
	
	
	
	/*
	 * Returns the number of Contents with format set to document
	 * @return count of document formats 
	 * */
	@GetMapping("/documentCount")
	public int getCountDocEx(){
		int counter = 0;
		Iterator<Content> contents = getNewestContent().iterator();
		while(contents.hasNext()) {
			if(contents.next().getFormat().equals("Document")) {
				counter++;
			}
		}
		return counter;
	}
	
	
	
	
	/*
	 * Returns the number of Contents with format set to powerpoint
	 * @return count of powerpoint formats 
	 * */
	@GetMapping("/ppCount")
	public int getCountPPEx(){
		int counter = 0;
		Iterator<Content> contents = getNewestContent().iterator();
		while(contents.hasNext()) {
			if(contents.next().getFormat().equals("Powerpoint")) {
				counter++;
			}
		}
		return counter;
	}
	
	
	
	/*
	 * Returns the number of different modules in DB
	 * @return number of modules 
	 * */
	@GetMapping("/numDiffMods")
	public int getNumDiffMod() {
		return getNewestModule().size();
	}
	
	
	
	
	/*
	 * Return average count of links in each Content object from DB
	 * @returns average number of links
	 * */
	@GetMapping("/averageRecs")
	public int getAvgRec() {
		int counter = 0;
		int size = 0;
		
		Iterator<Content> contents = getNewestContent().iterator();
		while(contents.hasNext()) {
			size += contents.next().getLinks().size();
			counter++;
		}
		
		return size/counter;
	}
		
}//end class
