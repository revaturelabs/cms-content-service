package com.revature.controllers;

import java.util.Iterator;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.Content;
import com.revature.entities.Module;
import com.revature.services.ContentServiceImpl;
import com.revature.services.ModuleServiceImpl;


@RestController
public class MetricsController {
	// reports mapping for metrics
	private Set<Content> allContents = new ContentServiceImpl().getAllContent();
	private Set<Module> allModules = new ModuleServiceImpl().getAllModules();

	
	
	
	// code
	@GetMapping("/metrics/code/all")
	public int getCountCodeEx(){
		int counter = 0;
		Iterator<Content> contents = allContents.iterator();
		while(contents.hasNext()) {
			if(contents.next().getFormat().equals("Code")) {
				counter++;
			}
		}
		return counter;
	}
	
	
	
	
	// documents 
	@GetMapping("/metrics/documents/all")
	public int getCountDocEx(){
		int counter = 0;
		Iterator<Content> contents = allContents.iterator();
		while(contents.hasNext()) {
			if(contents.next().getFormat().equals("Document")) {
				counter++;
			}
		}
		return counter;
	}
	
	
	
	//powerpoints
	@GetMapping("/metrics/powerpoints/all")
	public int getCountPPEx(){
		int counter = 0;
		Iterator<Content> contents = allContents.iterator();
		while(contents.hasNext()) {
			if(contents.next().getFormat().equals("Powerpoint")) {
				counter++;
			}
		}
		return counter;
	}
	
	//needs to be messed with, do it later
	
	@GetMapping("/metrics/modules/diff")
	public int getNumDiffMod() {
		return allModules.size();
	}
	
	
	
	
	// avg num resources
	@GetMapping("/metrics/powerpoints/all")
	public int getAvgRec() {
		int counter = 0;
		int size = 0;
		
		Iterator<Content> contents = allContents.iterator();
		while(contents.hasNext()) {
			size += contents.next().getLinks().size();
			counter++;
		}
		
		return size/counter;
	}
		
}//end class
