package com.revature.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Content;
import com.revature.repositories.ContentRepository;
import com.revature.transients.TimeGraphData;

@Service
public class TimegraphServiceImpl implements TimegraphService {
		
	@Autowired
	ContentRepository cr;
	

	@Override
	public TimeGraphData findByCreatedBetween(long timeRange) {

		// calculate second parameter-- earliest time 	
		// earliest time = current time minus passed in time
		
		// make a second long and calculate milliseconds
		long currentTime = System.currentTimeMillis();
		long startTime = currentTime - timeRange;
		// call the dao method to get all content
		
		ArrayList<Content> returnedContents = (ArrayList<Content>) cr.findAll();
		// iterate through the set of contents and retrieve the longs from the set 
		Set<Long> returnedDates = new HashSet<>();
		
		TimeGraphData tgd = new TimeGraphData(new HashSet<>(), 0);
		for (Content content : returnedContents)
		{
			// array of longs is here
			
			if (content.getDateCreated() < startTime)
			{
				int currentCount = tgd.getNumContents();
				tgd.setNumContents(currentCount += 1);
			}
			
			if (content.getDateCreated() >= startTime && content.getDateCreated() <= currentTime)
			{
				returnedDates.add(content.getDateCreated());
			}
		}
		
		tgd.setReturnedLongs(returnedDates);
		
		
		// make another crud call to select all content from T zero to T < start time
//		tgd.setNumContents(cr.findBydateCreatedBetween(1, startTime - 1).size());
		
		return tgd;
		
	}

}
