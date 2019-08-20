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

import com.revature.entities.Content;
import com.revature.entities.ContentMinusLinks;
import com.revature.entities.Link;
import com.revature.exceptions.InvalidContentException;
import com.revature.exceptions.InvalidContentId;
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
	 * and replaces the fk of content id with the correct content id,
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
		
		if(content.getDateCreated() == 0L && content.getLastModified() == 0L) {
		content.setDateCreated(System.currentTimeMillis());
		
		content.setLastModified(System.currentTimeMillis());
		}
		
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
	 * Ignores the additional DB call to get the list of links associated with a content object
	 */
	public Set<Content> getAllContentMinusLinks(){		
		Set<Content> contents = new HashSet<>();
		List<ContentMinusLinks> con = cr.findAllContentBy();
		
		for(ContentMinusLinks c: con) {
			contents.add(new Content(c.getId(), c.getTitle(), c.getFormat(), c.getDescription(), c.getUrl(), null, c.getDateCreated(), c.getLastModified()));
		}
		return contents;
	}
	
	/**
	 * get content from the data base that match a passed in id
	 * then returns the content with that id.
	 */
	@Override
	@LogException
	public Content getContentById(int id) 
	{	
		if(cr.findById(id).iterator().hasNext())
		{
			return cr.findById(id).iterator().next(); 
		}
		else
		{
			return null;
		}
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
			throw new InvalidContentException("updateContent, newContent is null");
		
		if(Character.isDigit(newContent.getId()))
			throw new InvalidContentId("updateContent, newContent does not have a valid Id");
		
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
			throw new InvalidContentException("updateContent, oldContent is null");

		return cr.save(newContent);
	}
	
	/**
	 * gets formats and cycles through all elements in DB to return
	 * how many times each format is used. 
	 * Much faster than using a findByFormat
	 * */
	@Override
	@LogException
	public Map<String, Integer> getContentByFormat(String[] formats) {
		Map<String, Integer> numList = new HashMap<>();
		ArrayList<Content> all = (ArrayList<Content>) cr.findAll();
		
		for(Content c : all) {
			if(numList.containsKey(c.getFormat()))
				numList.put(c.getFormat(), numList.get(c.getFormat()) + 1);
			else
				numList.put(c.getFormat(), 1);
		}
		return numList;
	}
	
	@Override
	@LogException
	public Map<String, Integer> getContentByFormat(Set<Content> contents) {
		Map<String, Integer> numList = new HashMap<>();
		
		for(Content c : contents) {
			if(numList.containsKey(c.getFormat()))
				numList.put(c.getFormat(), numList.get(c.getFormat()) + 1);
			else
				numList.put(c.getFormat(), 1);
		}
		return numList;
	}


	@Override
	public void deleteContent(Content content) {
		if(content != null) {
			cr.delete(content);
		}
	}
}
