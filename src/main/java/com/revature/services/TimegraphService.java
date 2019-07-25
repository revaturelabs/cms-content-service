package com.revature.services;

import java.util.Set;

import com.revature.entities.Content;
import com.revature.util.TimeGraphData;

public interface TimegraphService {
	
	public TimeGraphData findByCreatedBetween(long timeRange);
	
	public TimeGraphData findByCreatedBetween(long timeRange, Set<Content> allContent);

}
