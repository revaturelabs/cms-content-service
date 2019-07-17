package com.revature.services;

import java.util.Set;

import org.springframework.stereotype.Service;

public interface TimegraphService {
	
	public Set<Long> findByCreatedBetween(long fromTime);

}
