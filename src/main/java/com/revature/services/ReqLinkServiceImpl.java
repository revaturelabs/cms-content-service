package com.revature.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.ReqLink;
import com.revature.entities.Request;
import com.revature.repositories.ModuleRepository;
import com.revature.repositories.ReqLinkRepository;
import com.revature.repositories.RequestRepository;

@Service
public class ReqLinkServiceImpl implements ReqLinkService {

	@Autowired
	ReqLinkRepository rlr;
	
	@Autowired
	RequestRepository rr;
	
	@Autowired
	ModuleRepository mr;

	@Autowired
	SearchService searchService;

	/**
	 *  This method persists the ReqLink along with the Request and Module associated with the ReqLink
	 */
	@Override
	public ReqLink createReqLink(ReqLink reqLink) {
		reqLink.setRequest(rr.save(reqLink.getRequest()));
		reqLink.setModule(mr.save(reqLink.getModule()));
		return rlr.save(reqLink);
	}

	@Override
	public Set<ReqLink> getAllReqLinks() {
		Set<ReqLink> reqLinks = new HashSet<ReqLink>();
		rlr.findAll().forEach(reqLinks::add);
		return reqLinks;
	}

	@Override
	public ReqLink getReqLinkById(int id) {
		return rlr.findById(id);
	}

	@Override
	public ReqLink updateReqLink(ReqLink reqLink) {
		return rlr.save(reqLink);
	}

	/*
	 * Note From Tester - this method makes two database calls. suggest refactoring to just pass in the ReqLink object instead of its Id
	 */
	@Override
	public void deleteReqLinkById(int id) {
		rlr.delete(rlr.findById(id));
	}

	/**
	 * @param - String Title, String Format, List<Integer> moduleId
	 * this method returns a set of sets of ReqLink objects. each set of ReqLink Objects pertains to a Request.
	 * 
	 * Note From Tester - This method turns the String format into a Single Item List in order to pass it as a List to another method. It may be better to offer the option of initially passing a List of formats rather than having a single format turn into a list
	 */
	@Override
	public Set<Set<ReqLink>> filter(String title, String format, List<Integer> moduleIdsList) {
		
		List<String> formatList = new ArrayList<String>();
		formatList.add(format);
		Set<Request> filteredRequests = searchService.filterReq(title, formatList, moduleIdsList);
		// links will hold the final search result, each set within the set containing
		// links to a request
		Set<Set<ReqLink>> setOfSetOfReqLinks = new HashSet<>();
		// requestLinks will hold the set of reqLinks for a given request
		Set<ReqLink> requestLinks = new HashSet<ReqLink>();
		for (Request request : filteredRequests) {
			requestLinks = rlr.findByRequest(request);
			if (!requestLinks.isEmpty()) {
				setOfSetOfReqLinks.add(requestLinks);
			}
		}
		return setOfSetOfReqLinks;
	}
	
}
