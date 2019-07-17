package com.revature.controllers;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.Content;
import com.revature.entities.Module;
import com.revature.services.ContentService;
import com.revature.services.ModuleService;


@CrossOrigin
//@RestController("/metrics")
@RestController
public class MetricsController {
	// reports mapping for metrics
	@Autowired
	ModuleService moduleService;
	@Autowired
	ContentService contentService;

	private Set<Content> getNewestContent() {
		System.out.println("uh....get newest i guess");
		return contentService.getAllContent();
	}
	
	private Set<Module> getNewestModule() {
		return moduleService.getAllModules();
	}
		
	
	// code
	@GetMapping("/codeCount")
	public int getCountCodeEx(){
		int counter = 0;
		Iterator<Content> contents = getNewestContent().iterator();
		while(contents.hasNext()) {
			System.out.println("ITERATING!!!");
			if(contents.next().getFormat().equals("Code")) {
				counter++;
			}
			
		}
		return counter;
	}
	
	
	
	
	// documents 
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
	
	
	
	//powerpoints
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
	
	
	//modules
	@GetMapping("/numDiffMods")
	public int getNumDiffMod() {
		return getNewestModule().size();
	}
	
	
	
	
	// avg num resources
	@GetMapping("/avergeRecs")
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
