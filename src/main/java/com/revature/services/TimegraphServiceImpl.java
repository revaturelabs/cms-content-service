package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Content;
import com.revature.repositories.ContentRepository;

@Service
public class TimegraphServiceImpl implements TimegraphService {
		
	@Autowired
	ContentRepository cr;

	@Override
	public Set<Long> findByCreatedBetween(long timeRange) {
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
			returnedDates.add(content.getDateCreated());
		}
		
		return returnedDates;
		
	}

}
