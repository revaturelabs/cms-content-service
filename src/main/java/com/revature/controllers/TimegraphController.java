package com.revature.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.Content;
import com.revature.services.TimegraphService;

@CrossOrigin(origins = "*", allowCredentials="true")
@RestController
@Transactional
public class TimegraphController {
	
	@Autowired
	TimegraphService timegraphService;
	
	@GetMapping("/timegraph")
	Set<Long> getContentBetweenTimes(long fromTime)
	{
		// instance of timegraph service calls repository method and calculates earliest possible time
		
		return timegraphService.findByCreatedBetween(fromTime);
	}
	
	
	
	

}
