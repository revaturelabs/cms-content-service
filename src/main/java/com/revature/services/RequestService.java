package com.revature.services;
import java.util.Map;
import java.util.Set;

import com.revature.entities.Request;
public interface RequestService {
	
    public Request createRequests(Request requests);
    
    public Set<Request> getAllRequests();
    
    public Request getRequestsById(int id);
    
    public Map<String, Integer> getRequestsByFormat(String[] format);   
    
    public Map<String, Integer> getRequestsByFormat(Set<Request> contents);   
    
    public Request updateRequests(Request newRequests);
    
    public void deleteRequests(Request requests);
}
