package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.TimeGraphData;
import com.revature.services.TimegraphService;

@CrossOrigin(origins = "*", allowCredentials="true")
@Transactional
@RestController

public class TimegraphController {
	
	@Autowired
	TimegraphService timegraphService;
	
	/**
	 * getContentBetweenTimes defines the API end point which calls the appropriate service method.
	 * @param timeRange
	 * timeRange represents a select time range that the client selects to restrict the graph time axis.
	 * 
	 * @return
	 * A timeGraphData wrapper object see (com.revature.transients)
	 */
	@PostMapping("/timegraph/{timeFrame}")
	TimeGraphData getContentBetweenTimes(@PathVariable("timeFrame") long timeRange, @RequestBody Map<String, Object> selectedFilters)
	{
		// instance of timegraph service calls repository method and calculates earliest possible time
		List<Integer> selectedModules = (ArrayList<Integer>) selectedFilters.get("modules");
		
		return timegraphService.getGraphData(timeRange, selectedFilters.get("format").toString(), selectedModules);
	}
	
	
	
	

}
