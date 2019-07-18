package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Content;
import com.revature.entities.TimeGraphData;
import com.revature.repositories.ContentRepository;

@Service
public class TimegraphServiceImpl implements TimegraphService {
		
	@Autowired
	ContentRepository cr;
	
	@Autowired
	TimeGraphData tgd;

	@Override
	public TimeGraphData findByCreatedBetween(long timeRange) {
		System.out.println("Reached service layer");
		System.out.println("From: "+ timeRange);
		// calculate second parameter-- earliest time 	
		// earliest time = current time minus passed in time
		
		// make a second long and calculate milliseconds
		long currentTime = System.currentTimeMillis() / 1000L;
		long startTime = currentTime - timeRange;
		System.out.println("Current: "+currentTime);
		System.out.println("Start: "+startTime);
		// call the dao method with startTime and currentTime as parameters
		
		Set<Content> returnedContents = cr.findBydateCreatedBetween(startTime, currentTime);
		System.out.println(returnedContents.toString());
		// iterate through the set of contents and retrieve the longs from the set 
		Set<Long> returnedDates = new HashSet<>();
		
		for (Content content : returnedContents)
		{
			// array of longs is here
			returnedDates.add(content.getDateCreated());
		}
		
		tgd.setReturnedLongs(returnedDates);
		
		// make another crud call to select all content from T zero to T < start time
		tgd.setNumContents(cr.findBydateCreatedBetween(1, startTime - 1).size());
		
		return tgd;
		
	}

}
