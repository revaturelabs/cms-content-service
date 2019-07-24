package com.revature.services;


import java.util.List;

import com.revature.util.TimeGraphData;

public interface TimegraphService {
	
	public TimeGraphData getGraphData(long fromTime, String format, List<Integer> selectedModules);

}
