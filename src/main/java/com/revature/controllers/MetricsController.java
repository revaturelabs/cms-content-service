package com.revature.controllers;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.Module;
import com.revature.services.ContentService;
import com.revature.services.ModuleService;
import com.revature.services.SearchService;
import com.revature.services.TimegraphService;
import com.revature.util.MetricsData;
import com.revature.util.TimeGraphData;


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
	@Autowired
	TimegraphService timegraphService;
		
	
	
	@PostMapping("/obtain/{timeFrame}")
	public MetricsData getMetrics(@PathVariable("timeFrame") long timeRange, 
								  @RequestBody Map<String, Object> ids) {
		System.out.println(ids	);
		//formats for codeCount
		String[] formats = new String[] {"Code", "Document", "Powerpoint"};
		ArrayList<Integer> contentFormats = contentService.getContentByFormat(formats);

		
		//numDiffMods
		Set<Module> modules = (Set<Module>) moduleService.getAllModules();
		int modSize = modules.size(); //num diff modules
		
		
		//avg size
		@SuppressWarnings("unchecked")
		ArrayList<Integer> idsIn = (ArrayList<Integer>) ids.get("modules");
		double avgMods =  moduleService.getAverageByModuleIds(idsIn);
		
		
		TimeGraphData timeGraphData = timegraphService.findByCreatedBetween(timeRange);
		
		return new MetricsData(
				contentFormats.get(0),contentFormats.get(1), contentFormats.get(2), 
				modSize, avgMods, timeGraphData );
	}
	
	
	
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
		return modules.size();
	}
	
	
	/*
	 * Return average count of resources covered by each Module object from DB
	 * @returns average number of links
	 * */
	@PostMapping("/averageRecs")
	public double getAvgRec(@RequestBody Map<String, Object> ids) {
		System.out.println(ids);
		@SuppressWarnings("unchecked")
		ArrayList<Integer> idsIn = (ArrayList<Integer>) ids.get("modules");
		return moduleService.getAverageByModuleIds(idsIn);
	}
}
