package com.revature.services;

import java.util.Set;

import com.revature.entities.Content;
import com.revature.util.TimeGraphData;

public interface TimegraphService {
	
	public TimeGraphData findByCreatedBetween(long fromTime);
	
	public TimeGraphData getTimeGraphData(long fromTime, Set<Content> contents);

}
