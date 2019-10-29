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

	/**
	 * @param Link Object
	 * @return Link Object
	 * 
	 */
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
	/**
	 * @param Link Object
	 * @return link Object
	 * 
	 * Note From Tester: Save handles both creation and update, hence why save is used in both methods. These two methods can be combined in order to reduce redundancy
	 * Note From Tester: This method can be refactored to a single line -> return lr.save(Link) //since Save returns the persited object anyways.
	 */
	@Override
	public Link updateLink(Link link) {
		Link savedLink = lr.findById(lr.save(link).getId());
		return savedLink;
	}

	/**
	 * @param int id
	 * 
	 * this method finds the link by Id in order to check if it exists and then deletes it if it does
	 * 
	 * Note From Tester: the delete method only throws an exception if the entity itself is null, not if the entity doesnt exist in the database. Thus, it is save to refactor this method to accept a Link parameter instead of a Link Id and then perform lr.delete(link). If there is no other check to make sure the link parameter is not null then keep the if block to check for a null entity. otherwise if enforced elsewhere then the if block is unecessary;
	 */
	@Override
	public void deleteLinkById(int id) {
		Link link = lr.findById(id);
		if (link != null) {
			lr.delete(link);
		}
	}

	/**
	 * @param String title, String Format, ArrayList<Integer> moduleIds
	 * @return Set<Set<Link>> setOfSetsOflinks
	 */
	@Override
	public Set<Set<Link>> filter(String title, String format, ArrayList<Integer> moduleIdsList) {
		
		ArrayList<String> formatList = new ArrayList<>();
		formatList.add(format);
		Set<Content> filteredContent = searchService.filter(title, formatList, moduleIdsList);
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
