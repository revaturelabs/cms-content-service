package com.revature.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.entities.ReqLink;
import com.revature.entities.Request;
import com.revature.repositories.ReqLinkRepository;

@Service
public class ReqLinkServiceImpl implements ReqLinkService {

	@Autowired
	ReqLinkRepository rlr;

	@Autowired
	SearchService searchService;

	@Override
	public ReqLink createReqLink(ReqLink reqLink) {
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

	@Override
	public void deleteReqLinkById(int id) {
		rlr.delete(rlr.findById(id));
	}

	@Override
	public Set<Set<ReqLink>> filter(String title, String format, ArrayList<Integer> moduleIdsList) {

		Set<Request> filteredRequests = searchService.filterReq(title, format, moduleIdsList);
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
