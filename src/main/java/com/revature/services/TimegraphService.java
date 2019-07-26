package com.revature.services;

import com.revature.util.TimeGraphData;

public interface TimegraphService {
	
	public TimeGraphData findByCreatedBetween(long fromTime);

}
