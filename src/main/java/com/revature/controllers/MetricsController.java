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
	}
	
	
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
	public double getAvgRec(@RequestBody Map<String, Object> ids) {
		@SuppressWarnings("unchecked")
		ArrayList<Integer> idsIn = (ArrayList<Integer>) ids.get("modules");
		return moduleService.getAverageByModuleIds(idsIn);
	}
}
