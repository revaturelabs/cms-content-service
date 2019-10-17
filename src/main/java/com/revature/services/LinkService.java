package com.revature.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.netflix.ribbon.proxy.annotation.Content;
import com.revature.entities.Link;

public interface LinkService {

	public Link createLink(Link link);

	public Set<Link> getAllLinks();

	public Link getLinkById(int id);

	public Link updateLink(Link link);

	public void deleteLinkById(int id);

	public Set<Set<Link>> filter(String title, String format, ArrayList<Integer> moduleIdsList);

}
