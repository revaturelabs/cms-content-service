package com.revature.services;


import com.revature.transients.TimeGraphData;

public interface TimegraphService {
	
	public TimeGraphData findByCreatedBetween(long fromTime);

}
