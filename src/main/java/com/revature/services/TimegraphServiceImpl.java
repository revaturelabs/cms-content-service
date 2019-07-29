package com.revature.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Content;
import com.revature.repositories.ContentRepository;
import com.revature.util.TimeGraphData;

@Service

/**
 * 
 * @authors Joey Gorombey / Ian Dewars
 *   
 *
 */
public class TimegraphServiceImpl implements TimegraphService {
		
	@Autowired
	ContentRepository cr;
	

	/**
	 * @param timeRange
	 * This method accepts a time range from the user and calculates a time at which the graphical representation
	 *  of content generation will begin. This calculation-- a "start time"-- is achieved by subtracting the passed in time range
	 *  value from the current time (in milliseconds).
	 *  
	 *  This method then retrieves all created content and counts each piece of content by incrementing a counter. 
	 *  This counter is stored in an integer belonging  to the TimeGraphData object (see com.revature.transients).
	 *  
	 *  While iterating through each piece of content, this method also checks the creation date for each piece of content.
	 *  If the creation date is within the calculated range-- presentTime - timeRange-- the method stores the creation date
	 *  of that content piece in an array of longs. The array and total number of content belong to the TimeGraphData object, a model
	 *  which is not persisted in the database (see com.revature.transients).
	 *  
	 * 
	 */
	@Override
	public TimeGraphData findByCreatedBetween(long timeRange) {

		// calculate second parameter-- earliest time at which the graph begins 	
		// earliest time = current time minus passed in time
		
		// make a second long and calculate milliseconds
		long currentTime = System.currentTimeMillis();
		long startTime = currentTime - timeRange;
		// call the dao method to get all content
		
		ArrayList<Content> returnedContents = (ArrayList<Content>) cr.findAll();
		// iterate through the set of contents and retrieve the longs from the set 
		List<Long> returnedDates = new ArrayList<>();
		
		TimeGraphData tgd = new TimeGraphData(new ArrayList<>(), 0);
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
				
		return tgd;
		
	}


	@Override
	public TimeGraphData getTimeGraphData(long fromTime, Set<Content> contents) {
		// calculate second parameter-- earliest time at which the graph begins 	
				// earliest time = current time minus passed in time
				
				// make a second long and calculate milliseconds
				long currentTime = System.currentTimeMillis();
				long startTime = currentTime - fromTime;
				
				// iterate through the set of contents and retrieve the longs from the set 
				List<Long> returnedDates = new ArrayList<>();
				
				TimeGraphData tgd = new TimeGraphData(new ArrayList<>(), 0);
				for (Content content : contents)
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
//				tgd.setNumContents(cr.findBydateCreatedBetween(1, startTime - 1).size());
				
				return tgd;
	}
}
