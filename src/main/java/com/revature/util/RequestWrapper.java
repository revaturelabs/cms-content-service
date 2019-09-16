package com.revature.util;

import java.util.Arrays;

import com.revature.entities.Request;
import com.revature.entities.ReqLink;

public class RequestWrapper { //Bean used to attach an array of reqLinks to requests
	private Request requests;
	private ReqLink[] reqLinks;
	
	public RequestWrapper() {
		super();
	}

	public RequestWrapper(Request requests, ReqLink[] reqLinks) {
		super();
		this.requests = requests;
		this.reqLinks = reqLinks;
	}

	public Request getRequests() {
		return requests;
	}

	public void setRequests(Request requests) {
		this.requests = requests;
	}

	public ReqLink[] getLinks() {
		return reqLinks;
	}

	public void setLinks(ReqLink[] reqLinks) {
		this.reqLinks = reqLinks;
	}

	@Override
	public String toString() {
		return "RequestWrapper [requests=" + requests + ", reqLinks=" + Arrays.toString(reqLinks) + "]";
	}
}
