package com.revature.services;
import java.util.Map;
import java.util.Set;

import com.revature.entities.Requests;
public interface RequestService {
	
    public Requests createRequests(Requests requests);
    
    public Set<Requests> getAllRequests();
    
    public Requests getRequestsById(int id);
    
    public Map<String, Integer> getRequestsByFormat(String[] format);   
    
    public Map<String, Integer> getRequestsByFormat(Set<Requests> contents);   
    
    public Requests updateRequests(Requests newRequests);
    
    public void deleteRequests(Requests requests);
}
