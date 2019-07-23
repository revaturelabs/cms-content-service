package com.revature.services;


import java.util.List;

import com.revature.entities.TimeGraphData;

public interface TimegraphService {
	
	public TimeGraphData getGraphData(long fromTime, String format, List<Integer> selectedModules);

}
