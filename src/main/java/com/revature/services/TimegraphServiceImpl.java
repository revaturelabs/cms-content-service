package com.revature.services;

import java.util.Collections;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.entities.Content;
import com.revature.repositories.ContentRepository;

public class TimegraphServiceImpl implements TimegraphService {
	
	@Autowired
	ContentRepository cr;

	@Override
	public Set<Long> findByCreatedBetween(long fromTime) {
		// calculate second parameter-- earliest time 	
		// earliest time = current time minus passed in time
		
		// make a second long and calculate milliseconds
		long currentTime = System.currentTimeMillis();
		long startTime = currentTime - fromTime;
		
		// call the dao method with startTime and currentTime as parameters
		
		Set<Content> returnedContents = cr.findBydateCreatedBetween(currentTime, startTime);
		
		// iterate through the set of contents and retrieve the longs from the set 
		Set<Long> returnedDates = Collections.emptySet();
		
		for (Content content : returnedContents)
		{
			returnedDates.add(content.getDateCreated());
		}
		
		return returnedDates;
		
	}

}
