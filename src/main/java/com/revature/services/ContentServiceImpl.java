package com.revature.services;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.repositories.LinkRepository;
import com.revature.repositories.ContentRepository;
import com.revature.repositories.ModuleRepository;
import com.revature.util.LogException;

@Service
@Transactional
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	ContentRepository cr;
	@Autowired 
	LinkRepository lr;
	@Autowired
	ModuleRepository mr;

	/**
	 * createContent first inserts the content to the database
	 * then iterates over the set of links that are within the content
	 * and replaces the fk of content id with the correct content id
	 * then adds the set of links to the link table. 
	 */
	@LogException
	@Override
	public Content createContent(Content content) {
		
		Set<Link> links = content.getLinks();
		
		
		if (links == null) {
			throw new NullPointerException();
		}
		
		content.setLinks(null);
		content = cr.save(content);
		
		for(Link link : links) {
			link.setContentId(content.getId());
		}
		
		lr.saveAll(links);
		
		content.setLinks(links);		

		return content;
	}
	

	/**
	 * Get all the content from the database and passes a set of content objects
	 */
	@Override
	@LogException
	public Set<Content> getAllContent() {
			Set<Content> contents = new HashSet<>();
			cr.findAll().forEach(contents :: add);
			return contents;
		}
	
	/**
	 * get content from the data base that match a passed in id
	 * then returns the content with that id.
	 */
	@Override
	@LogException
	public Content getContentById(int id) {	
			return cr.findById(id).iterator().next();		
	}
	
	/**
	 * Description - Updates an existing content
	 * @param newContent - content received from client request
	 * @return - the updated content
	 * @throws - NullPointerException - if the newContent parameter is null or if the requested content to be updated doesn't exist in content Repository
	 */
	@Override
	@LogException
	public Content updateContent(Content newContent) {
		
		if(newContent == null)
			throw new NullPointerException();
		
		if(Character.isDigit(newContent.getId()))
			throw new NumberFormatException();
		
		Set<Link> oldLinks = new HashSet<>();
		Set<Link> newLinks = new HashSet<>();
		
		for(Link link : newContent.getLinks()) {
			if(link.getId() == 0) {
				newLinks.add(link);
			} else {
				oldLinks.add(link);
			}
		}
		
		newContent.setLinks(oldLinks);
		
		if(!newLinks.isEmpty()) {

			for(Link l : lr.saveAll(newLinks)) {
				oldLinks.add(l);
			}
			newContent.setLinks(oldLinks);
		}
		
		Content oldContent = this.getContentById(newContent.getId());
		
		if(oldContent == null)
			throw new NullPointerException();
		
		
		return cr.save(newContent);
	}
	
	
	
}
