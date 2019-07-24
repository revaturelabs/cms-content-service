package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.TimeGraphData;
import com.revature.services.TimegraphService;

@CrossOrigin(origins = "*", allowCredentials="true")
@Transactional
@RestController
public class TimegraphController {
	
	@Autowired
	TimegraphService timegraphService;
	
	@GetMapping("/timegraph/{timeFrame}")
	TimeGraphData getContentBetweenTimes(@PathVariable("timeFrame") long timeRange)
	{
		// instance of timegraph service calls repository method and calculates earliest possible time
		
		return timegraphService.findByCreatedBetween(timeRange);
	}
	
	
	
	

}
