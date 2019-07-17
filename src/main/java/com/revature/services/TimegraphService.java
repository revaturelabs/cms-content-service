package com.revature.services;

import java.util.Set;



public interface TimegraphService {
	
	public Set<Long> findByCreatedBetween(long fromTime);

}
