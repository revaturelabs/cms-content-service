package com.revature.controllers;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.Module;
import com.revature.services.ContentService;
import com.revature.services.ModuleService;
import com.revature.services.SearchService;


@CrossOrigin
@RestController
@RequestMapping("/metrics")
public class MetricsController {
	// reports mapping for metrics
	@Autowired
	ModuleService moduleService;
	@Autowired
	ContentService contentService;
	@Autowired
	SearchService searchService;
		
	/*
	 * Returns the number of Contents with format set to code
	 * @return count of code formats 
	 * */
	@GetMapping("/codeCount")
	public ArrayList<Integer> getCountCodeEx(){
		String[] formats = new String[] {"Code", "Document", "Powerpoint"};
		return contentService.getContentByFormat(formats);

//		int counter = 0;
//		int docCount = 0;
//		int powerCount = 0;
//		ArrayList<Integer> totals = contentService.getContentByFormat(["Code", "Document", "Powerpoint"]);

//		for(Integer i : totals) {
//			String form = c.getFormat();
//			if(form.equals("Code")) {
//				counter++;
//			}
//			else if(form.equals("Document")) {
//				docCount++;
//			}
//			else if(form.equals("Powerpoint")) {
//				powerCount++;
//			}
//		}
		// return new int [] = {code, docCount, powerCount};
	}
	
//	
//	
//	
//	/*
//	 * Returns the number of Contents with format set to document
//	 * @return count of document formats 
//	 * */
//	@GetMapping("/documentCount")
//	public int getCountDocEx(){
//		int counter = 0;
//		ArrayList<Content> contents = (ArrayList<Content>) contentService.getAllContent();
//		for(Content c : contents) {
//			if(c.getFormat().equals("Document")) {
//				counter++;
//			}
//		}
//		return counter;
//	}
//	
//	
//	
//	
//	/*
//	 * Returns the number of Contents with format set to powerpoint
//	 * @return count of powerpoint formats 
//	 * */
//	@GetMapping("/ppCount")
//	public int getCountPPEx(){
//		int counter = 0;
//		ArrayList<Content> contents = (ArrayList<Content>) contentService.getAllContent();
//		for(Content c : contents) {
//			if(c.getFormat().equals("Powerpoint")) {
//				counter++;
//			}
//		}
//		return counter;
//	}
//	
//	
	
	/*
	 * Returns the number of different modules in DB
	 * @return number of modules 
	 * */
	@GetMapping("/numDiffMods")
	public int getNumDiffMod() {
		Set<Module> modules = (Set<Module>) moduleService.getAllModules();
		System.out.println(modules);
		return modules.size();
	}
	
	
	
	
	/*
	 * Return average count of resources covered by each Module object from DB
	 * @returns average number of links
	 * */
	@PostMapping("/averageRecs")
//	public int getAvgRec(@RequestBody int[] ids) {
	public double getAvgRec(@RequestBody Map<String, Object> ids) {
		System.out.println(ids.toString());
		
		@SuppressWarnings("unchecked")
		ArrayList<Integer> idsIn = (ArrayList<Integer>) ids.get("modules");
		
		return moduleService.getAverageByModuleIds(idsIn);
		
		
//		Set<Content> content = searchService.filterContentBySubjects(moduleIds);
		
		//System.out.println(content);
		
		
//		ArrayList<Content> contents = (ArrayList<Content>) contentService.getAllContent();
//		int counter = contents.size();
//		int size = 0;
//		if(counter != 0) {
//			for(Content c : contents) {
//				size += c.getLinks().size();
//			}
//		}
//		return size/counter;
	}
		
}//end class
