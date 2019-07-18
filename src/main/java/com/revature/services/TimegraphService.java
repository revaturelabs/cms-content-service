package com.revature.services;


import com.revature.entities.TimeGraphData;

public interface TimegraphService {
	
	public TimeGraphData findByCreatedBetween(long fromTime);

}
