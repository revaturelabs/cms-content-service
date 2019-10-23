
package com.revature.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.entities.ReqLink;
import com.revature.entities.Request;
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
	ModuleRepository rmr;
	@Autowired
	ReqLinkRepository rlr;

	@LogException
	@Override
	public Request createRequest(Request request) {
		
		if (request.getDateCreated() != null && request.getLastModified() != null) {
			if (request.getDateCreated() == 0L && request.getLastModified() == 0L) {
				request.setDateCreated(System.currentTimeMillis());
				request.setLastModified(System.currentTimeMillis());
			}
		}
		
		// save the content to the database
		if (request != null) {
			request = rr.save(request);
		}
		// return the saved content
		return request;
	}

	/**
	 * Get all the requests from the database and passes a set of requests objects
	 */
	@Override
	@LogException
	public Set<Request> getAllRequests() {
		Set<Request> requests = new HashSet<>();
		rr.findAll().forEach(requests::add);
		return requests;
	}

	/**
	 * get requests from the data base that match a passed in id then returns the
	 * requests with that id.
	 */
	@Override
	@LogException
	public Request getRequestsById(int id) {
		return rr.findById(id);
	}

	/**
	 * Description - Updates an existing requests
	 * 
	 * @param newContent - requests received from client request
	 * @return - the updated requests
	 * @throws - NullPointerException - if the newContent parameter is null or if
	 *           the requested requests to be updated doesn't exist in requests
	 *           Repository
	 */
	@Override
	@LogException
	public Request updateRequest(Request newRequest) {
		if (newRequest == null) {
			throw new InvalidRequestException("updateRequest, new request is null");
		}
		if (rr.findById(newRequest.getId()) == null) {
			throw new InvalidRequestIdException("updateRequest, newRequest does not have a valid id");
		}
		return rr.save(newRequest);
	}

	/**
	 * gets formats and cycles through all elements in DB to return how many times
	 * each format is used. Much faster than using a findByFormat
	 */
	@Override
	@LogException
	public Map<String, Integer> getRequestsByFormat(String[] formats) {
		Map<String, Integer> numList = new HashMap<>();
		ArrayList<Request> all = (ArrayList<Request>) rr.findAll();

		for (Request r : all) {
			if (numList.containsKey(r.getFormat()))
				numList.put(r.getFormat(), numList.get(r.getFormat()) + 1);
			else
				numList.put(r.getFormat(), 1);
		}
		return numList;
	}

	@Override
	@LogException
	public Map<String, Integer> getRequestsByFormat(Set<Request> requests) {
		Map<String, Integer> numList = new HashMap<>();

		for (Request r : requests) {
			if (numList.containsKey(r.getFormat()))
				numList.put(r.getFormat(), numList.get(r.getFormat()) + 1);
			else
				numList.put(r.getFormat(), 1);
		}
		return numList;
	}

	@Override
	public void deleteRequest(Request request) {
		if (request != null) {
			rr.delete(request);
		}
	}
	
	@Override
	public List<ReqLink> updateReqLinks(int id, List<ReqLink> reqLinks) {
		for (ReqLink reqLink : reqLinks) {
			rlr.save(reqLink);
		}
		return reqLinks;
	}

	@Override
	public List<ReqLink> createReqLinksByRequestId(int id, List<ReqLink> reqLinks) {
		List<ReqLink> savedReqLinks = new ArrayList<ReqLink>();
		for (ReqLink reqLink : reqLinks) {
			savedReqLinks.add(rlr.save(reqLink));
		}
		return savedReqLinks;
	}

	@Override
	public List<ReqLink> getReqLinksByRequestId(int id) {
		return rlr.findByRequestId(id);
	}
}

