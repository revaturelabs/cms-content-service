package com.revature.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.entities.ReqLink;
import com.revature.entities.Requests;
import com.revature.exceptions.InvalidRequestException;
import com.revature.exceptions.InvalidRequestIdException;
import com.revature.repositories.ModuleRepository;
import com.revature.repositories.ReqLinkRepository;
import com.revature.repositories.RequestRepository;
import com.revature.util.LogException;

@Service
@Transactional
public class RequestServiceImpl implements RequestService {
	
	@Autowired
	RequestRepository rr;
	@Autowired 
	ReqLinkRepository rlr;
	@Autowired
	ModuleRepository rmr;

	@LogException
	@Override
	public Requests createRequests(Requests requests) {
		
		Set<ReqLink> reqLinks = requests.getReqLinks();
		
		
		if (reqLinks == null) {
			throw new NullPointerException();
		}
		
		requests.setReqLinks(null);
		requests = rr.save(requests);
		
		for(ReqLink reqLink : reqLinks) {
			reqLink.setRequestId(requests.getId());
		}
		
		rlr.saveAll(reqLinks);
		
		requests.setReqLinks(reqLinks);
		
		if(requests.getDateCreated() == 0L && requests.getLastModified() == 0L) {
		requests.setDateCreated(System.currentTimeMillis());
		
		requests.setLastModified(System.currentTimeMillis());
		}
		
		return requests;
	}
	

	/**
	 * Get all the requests from the database and passes a set of requests objects
	 */
	@Override
	@LogException
	public Set<Requests> getAllRequests() {
			Set<Requests> requests = new HashSet<>();
			rr.findAll().forEach(requests :: add);
			return requests;
		}
	
	/**
	 * get requests from the data base that match a passed in id
	 * then returns the requests with that id.
	 */
	@Override
	@LogException
	public Requests getRequestsById(int id) 
	{	
		if(rr.findById(id).iterator().hasNext())
		{
			return rr.findById(id).iterator().next(); 
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * Description - Updates an existing requests
	 * @param newContent - requests received from client request
	 * @return - the updated requests
	 * @throws - NullPointerException - if the newContent parameter is null or if the requested requests to be updated doesn't exist in requests Repository
	 */
	@Override
	@LogException
	public Requests updateRequests(Requests newRequests) {
		
		if(newRequests == null)
			throw new InvalidRequestException("updateRequests, newRequests is null");
		
		if(Character.isDigit(newRequests.getId()))
			throw new InvalidRequestIdException("updateRequests, newRequests does not have a valid Id");
		
		Set<ReqLink> oldReqLinks = new HashSet<>();
		Set<ReqLink> newReqLinks = new HashSet<>();
		
		for(ReqLink reqLink : newRequests.getReqLinks()) {
			if(reqLink.getId() == 0) {
				newReqLinks.add(reqLink);
			} else {
				oldReqLinks.add(reqLink);
			}
		}
		
		newRequests.setReqLinks(oldReqLinks);
		
		if(!oldReqLinks.isEmpty()) {
			for(ReqLink z : rlr.saveAll(newReqLinks)) {
				oldReqLinks.add(z);
			}
			newRequests.setReqLinks(oldReqLinks);
		}
		
		Requests oldRequests = this.getRequestsById(newRequests.getId());
		
		if(oldRequests == null)
			throw new InvalidRequestIdException("updateRequests, newRequests is null");

		return rr.save(newRequests);
	}
	
	/**
	 * gets formats and cycles through all elements in DB to return
	 * how many times each format is used. 
	 * Much faster than using a findByFormat
	 * */
	@Override
	@LogException
	public Map<String, Integer> getRequestsByFormat(String[] formats) {
		Map<String, Integer> numList = new HashMap<>();
		ArrayList<Requests> all = (ArrayList<Requests>) rr.findAll();
		
		for(Requests r : all) {
			if(numList.containsKey(r.getFormat()))
				numList.put(r.getFormat(), numList.get(r.getFormat()) + 1);
			else
				numList.put(r.getFormat(), 1);
		}
		return numList;
	}
	
	@Override
	@LogException
	public Map<String, Integer> getRequestsByFormat(Set<Requests> requests) {
		Map<String, Integer> numList = new HashMap<>();
		
		for(Requests r : requests) {
			if(numList.containsKey(r.getFormat()))
				numList.put(r.getFormat(), numList.get(r.getFormat()) + 1);
			else
				numList.put(r.getFormat(), 1);
		}
		return numList;
	}


	@Override
	public void deleteRequests(Requests requests) {
		if(requests != null) {
			rr.delete(requests);
		}
	}
}
