package com.revature.services;

import java.util.HashSet;
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
		
		//get the tags for the new submitted content
		Set<Link> links = content.getLinks();
		
		
		if (links == null) { //if the submitted content has no tags then throw an error
			throw new NullPointerException();
		}
		
		content.setLinks(null);
		content = cr.save(content); //add content to repository
		
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
	//Need an update content method here - Mdo
	@Override
	@LogException
	public Content updateContent(Content newContent) {
		System.out.println("before exceptions");
		//check if requested update to content is a null object
		if(newContent == null)
			throw new NullPointerException();
		
		//check if newContent has an id
		if(Character.isDigit(newContent.getId()))
			throw new NumberFormatException();
		System.out.println("after exceptions");
		//These will be used for when we're adding tags to a content
		//basically when we're adding a tag(module) it has an id of '0'
		//So we need to assign them a generated id from the repo.
		//newLinks will contain tags that were just added and have no id
		Set<Link> newLinks = new HashSet<>();
		System.out.println("before sorting new links");
		System.out.println(newContent);
		for(Link link : newContent.getLinks()) {
			if(link.getId() == 0) {
				newLinks.add(link);
				newContent.getLinks().remove(link); //remove the new links so that they can be compared for other field changes
			}
		}
		System.out.println("after sorting new links");
		Content oldContent = this.getContentById(newContent.getId());
		
		
		System.out.println("Apples old: " + oldContent);
		System.out.println("Apples new:" + newContent);
		
		if(!newContent.equals(oldContent)){
			System.out.println("Johnny Appleseed");
			//get the existing content
			
			//check if content exists in content repo
			if(oldContent == null)
				throw new NullPointerException();
			
			//add new updated content
			cr.save(newContent);
		}
		
		//This if statement determines whether or not we're adding or removing tags
		//it does so by checking of newLinks is empty
		//if it is then we are removing or just updating other content fields
		//if it is not empty then we are adding tags.
		if(!newLinks.isEmpty()) {
			//add the new tags
			lr.saveAll(newLinks);
			//return newContent;
		}
		
		
		
		
		return this.getContentById(newContent.getId());
	}
	
	
	
}
