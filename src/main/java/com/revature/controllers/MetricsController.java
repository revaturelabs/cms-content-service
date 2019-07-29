package com.revature.controllers;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.Content;
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
		
	
	
	@PostMapping("/{timeFrame}")
	public MetricsData getMetrics(@PathVariable("timeFrame") long timeRange, 
								  @RequestBody Map<String, Object> filters) {
		Set<Content> allContents = contentService.getAllContent();
		Set<Content> filtContents = searchService.filterContent(allContents, filters);
		
		//formats for codeCount
		Map<String, Integer> contentFormats = contentService.getContentByFormat(filtContents);
		
		// numDiffMods
		Set<Module> modules = (Set<Module>) moduleService.getAllModules();
		int modSize = modules.size(); //num diff modules
		
		
		// avg size
		@SuppressWarnings("unchecked")
		ArrayList<Integer> idsIn = (ArrayList<Integer>) filters.get("modules");
		double avgMods = 0;
		if(idsIn != null && !idsIn.isEmpty()) {
			avgMods = moduleService.getAverageByModuleIds(idsIn);
		} else {
			avgMods = moduleService.getAverageByAllModules();
		}
		
		// count number of each format type
		Integer numCode = 0;
		if(contentFormats.containsKey("Code"))
			numCode = contentFormats.get("Code");
		
		Integer numDoc = 0;
		if(contentFormats.containsKey("Document"))
			numDoc = contentFormats.get("Document");
		
		Integer numPpt = 0;
		if(contentFormats.containsKey("Powerpoint"))
			numPpt = contentFormats.get("Powerpoint");

		TimeGraphData timeGraphData = timegraphService.getTimeGraphData(timeRange, filtContents);
		
		MetricsData gatheredMetrics = new MetricsData(
				numCode, numDoc, numPpt, 
				modSize, avgMods, timeGraphData);
		 
		 return gatheredMetrics;
	}
}
