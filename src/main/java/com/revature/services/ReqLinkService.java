package com.revature.services;

import java.util.List;
import java.util.Set;

import com.revature.entities.ReqLink;

public interface ReqLinkService {

	public ReqLink createReqLink(ReqLink reqLink);

	public Set<ReqLink> getAllReqLinks();

	public ReqLink getReqLinkById(int id);

	public ReqLink updateReqLink(ReqLink reqLink);

	public void deleteReqLinkById(int id);

	public Set<Set<ReqLink>> filter(String title, String format, List<Integer> moduleIdsList);

}
