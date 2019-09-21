package com.revature.services;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.revature.entities.ReqLink;
import com.revature.entities.Request;
public interface RequestService {
	
    public Request createRequest(Request request);
    
    public Set<Request> getAllRequests();
    
    public Request getRequestsById(int id);
    
    public Map<String, Integer> getRequestsByFormat(String[] format);   
    
    public Map<String, Integer> getRequestsByFormat(Set<Request> contents);   
    
    public Request updateRequest(Request newRequest);
    
    public void deleteRequest(Request request);

	public List<ReqLink> createReqLinksByRequestId(int id, List<ReqLink> reqLinks);

	public List<ReqLink> updateReqLinks(int id, List<ReqLink> reqLinks);

	public List<ReqLink> getReqLinksByRequestId(int id);
}
