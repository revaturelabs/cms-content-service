package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.revature.services.TimegraphService;
import com.revature.util.TimeGraphData;

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
	@GetMapping("/timegraph/{timeFrame}")
	TimeGraphData getContentBetweenTimes(@PathVariable("timeFrame") long timeRange)
	{
		// instance of timegraph service calls repository method and calculates earliest possible time
		
		return timegraphService.findByCreatedBetween(timeRange);
	}
	
	
	
	

}
