package com.revature.controllers;

/**
 * For documentation on the controllers check out some documentation on swaggerhub:
 * https://app.swaggerhub.com/apis-docs/pacquito/CMS-Controllers/0.1
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

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
	@Autowired
	ModuleService moduleService;
	@Autowired
	ContentService contentService;
	@Autowired
	SearchService searchService;
	@Autowired
	TimegraphService timegraphService;

	@PostMapping("/{timeFrame}")
	public MetricsData getMetrics(@PathVariable("timeFrame") long timeRange, @RequestBody Map<String, Object> filter) {
		Set<Content> contents;
		Set<Content> filtContents;
		List<Integer> idsIn = new ArrayList<>();

		// turn the string of integers we recieved into an ArrayList of integers
		Object moduleObject = filter.get("modules");
		StringTokenizer st = new StringTokenizer(moduleObject != null ? moduleObject.toString() : "", ",");
		while (st.hasMoreTokens()) {
			idsIn.add(Integer.parseInt(st.nextToken()));
		}

		Set<Module> modulesIdsIn = new HashSet<Module>();
		for (Integer id : idsIn) {
			modulesIdsIn.add(moduleService.getModuleById(id));
		}

		contents = contentService.getAllContent();
		filtContents = searchService.filterContent(contents, filter);

		Map<String, Integer> contentFormats = contentService.getFormatCount(filtContents);

		Set<Module> modules = moduleService.getAllModules();
		int modSize = modules.size();

		double avgMods = 0;
		if (!idsIn.isEmpty()) {
			avgMods = moduleService.getAverageByModules(modulesIdsIn);
		} else {
			avgMods = moduleService.getAverageByAllModules();
		}

		Integer numCode = 0;
		if (contentFormats.containsKey("Code"))
			numCode = contentFormats.get("Code");

		Integer numDoc = 0;
		if (contentFormats.containsKey("Document"))
			numDoc = contentFormats.get("Document");

		Integer numPpt = 0;
		if (contentFormats.containsKey("Powerpoint"))
			numPpt = contentFormats.get("Powerpoint");

		TimeGraphData timeGraphData = timegraphService.getTimeGraphData(timeRange, filtContents);

		MetricsData gatheredMetrics = new MetricsData(numCode, numDoc, numPpt, modSize, avgMods, timeGraphData);

		return gatheredMetrics;
	}
}
