package com.revature.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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
	 * Returns the number of Contents with format set to code
	 * @return count of code formats 
	 * */
	@GetMapping("/codeCount")
	public int[] getCountCodeEx(){
		int counter = 0;
		int docCount = 0;
		int powerCount = 0;
		ArrayList<Content> contents = (ArrayList<Content>) contentService.getAllContent();
		for(Content c : contents) {
			String form = c.getFormat();
			if(form.equals("Code")) {
				counter++;
			}
			else if(form.equals("Document")) {
				docCount++;
			}
			else if(form.equals("Powerpoint")) {
				powerCount++;
			}
		}
		return new int[] {counter, docCount, powerCount};
	}
	
	
	
	
	/*
	 * Returns the number of Contents with format set to document
	 * @return count of document formats 
	 * */
	@GetMapping("/documentCount")
	public int getCountDocEx(){
		int counter = 0;
		ArrayList<Content> contents = (ArrayList<Content>) contentService.getAllContent();
		for(Content c : contents) {
			if(c.getFormat().equals("Document")) {
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
		ArrayList<Content> contents = (ArrayList<Content>) contentService.getAllContent();
		for(Content c : contents) {
			if(c.getFormat().equals("Powerpoint")) {
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
		ArrayList<Module> modules = (ArrayList<Module>) moduleService.getAllModules();
		return modules.size();
	}
	
	
	
	
	/*
	 * Return average count of links in each Content object from DB
	 * @returns average number of links
	 * */
	@GetMapping("/averageRecs")
	public int getAvgRec() {
		ArrayList<Content> contents = (ArrayList<Content>) contentService.getAllContent();
		int counter = contents.size();
		int size = 0;
		if(counter != 0) {
			for(Content c : contents) {
				size += c.getLinks().size();
			}
		}
		return size/counter;
	}
		
}//end class
