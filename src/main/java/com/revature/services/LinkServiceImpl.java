package com.revature.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.repositories.ContentRepository;
import com.revature.repositories.LinkRepository;

@Service
public class LinkServiceImpl implements LinkService {

	@Autowired
	LinkRepository lr;
	
	@Autowired
	ContentRepository cr;

	@Autowired
	SearchService searchService;

	@Override
	public Link createLink(Link link) {
		link.setContent(cr.save(link.getContent()));
		return lr.save(link);
	}

	@Override
	public Set<Link> getAllLinks() {
		Set<Link> links = new HashSet<Link>();
		lr.findAll().forEach(links::add);
		return links;
	}

	@Override
	public Link getLinkById(int id) {
		return lr.findById(id);
	}

	@Override
	public Link updateLink(Link link) {
		Link savedLink = lr.findById(lr.save(link).getId());
		return savedLink;
	}

	@Override
	public void deleteLinkById(int id) {
		Link link = lr.findById(id);
		if (link != null) {
			lr.delete(link);
		}
	}

	@Override
	public Set<Set<Link>> filter(String title, String format, ArrayList<Integer> moduleIdsList) {
		Set<Content> filteredContent = searchService.filter(title, format, moduleIdsList);
		// links will hold the final search result, each set within the set containing
		// links to a content
		Set<Set<Link>> setOfSetOfLinks = new HashSet<>();
		// contentLinks will hold the set of links for a given content
		Set<Link> contentLinks = new HashSet<Link>();
		for (Content content : filteredContent) {
			contentLinks = lr.findByContentId(content.getId());
			if (!contentLinks.isEmpty()) {
				setOfSetOfLinks.add(contentLinks);
			}
		}
		return setOfSetOfLinks;
	}

}
