package com.revature.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.revature.services.TimegraphService;

@CrossOrigin(origins = "*", allowCredentials="true")
@Transactional
@RestController

public class TimegraphController {
	
	@Autowired
	TimegraphService timegraphService;
	
	@GetMapping("/timegraph/{timeFrame}")
	Set<Long> getContentBetweenTimes(@PathVariable("timeFrame") long timeRange)
	{
		// instance of timegraph service calls repository method and calculates earliest possible time
		
		return timegraphService.findByCreatedBetween(timeRange);
	}
	
	
	
	

}
