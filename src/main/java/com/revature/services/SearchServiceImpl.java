package com.revature.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;
import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.entities.ReqLink;
import com.revature.entities.Request;
import com.revature.repositories.ContentRepository;
import com.revature.repositories.ModuleRepository;
import com.revature.repositories.ReqLinkRepository;
import com.revature.repositories.RequestRepository;
import com.revature.util.LogException;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	ContentRepository cr;
	@Autowired
	RequestRepository rr;
	@Autowired
	ModuleRepository mr;
	@Autowired
	ContentService cs;
	@Autowired
	ModuleService ms;
	@Autowired
	RequestService rs;

	/**
	 * filterContentByTitle takes in a string value and returns a content object
	 */
	@Override
	@LogException
	public Set<Content> filterContentByTitle(String title) {
		return cr.findByTitle(title);
	}

	/**
	 * filderContentByFormat takes in a string value and returns a content object
	 */
	@Override
	@LogException
	public Set<Content> filterContentByFormat(String format) {
		return cr.findByFormat(format);
	}

	/**
	 * filterContentBySubjects takes in a list of integers that represent module
	 * ids. It then stores all the related links of that module in a temporary set.
	 * then it gets all the content that relates to the selected links. The method
	 * uses AND logic.
	 */
	@Override
	@LogException
	public Set<Content> filterContentBySubjectIds(List<Integer> moduleIds) {
		Set<Module> modules = new HashSet<Module>();
		Set<Link> links = new HashSet<Link>();
		Set<Content> tempContent = new HashSet<Content>();
		Set<Content> content = new HashSet<Content>();

		// get modules by moduleIds
		for (Integer id : moduleIds) {
			modules.add(ms.getModuleById(id));
		}
		// get all content of each module
		for (Module module : modules) {

			// get links of module, the links hold the content within them
			links = module.getLinks();

			// make sure tempContent is empty at the start of a new iteration
			tempContent.clear();

			// add content in links to tempContent so we can work with it
			for (Link link : links) {
				tempContent.add(link.getContent());
			}
			// if it is the first iteration, we just want to put tempContent into content
			if (content.isEmpty()) {
				content = tempContent;
			}
			// in order to only get content associated to ALL provided modules, we perform
			// an intersection on content and tempContent
			else {
				content = Sets.intersection(content, tempContent);

				// if content is empty after the intersection, then there is no content
				// associated with ALL provided modules
				if (content.isEmpty()) {
					return content;
				}
			}
		}
		return content;
	}

	/**
	 * getContentByModuleID takes in an int that is a module id and returns a set of
	 * content that have the inputed value as the moduleID.
	 */
	@Override
	@LogException
	public Set<Content> getContentByModuleId(int moduleId) {
		Set<Link> links = mr.findById(moduleId).getLinks();
		Set<Content> content = new HashSet<Content>();
		for (Link link : links) {
			content.add(link.getContent());
		}
		return content;
	}

	/**
	 * Filter takes a content title, content format and/or a list of Integers that
	 * represent module ids. It will then return a set of content that contains all
	 * content that matches all 3 inputs using AND logic. If an input is empty it is
	 * ignored and is not part of the logic.
	 */
	@Override
	@LogException
	public Set<Content> filter(String title, String format, List<Integer> moduleIds) {

		Set<Content> content = cs.getAllContent();

		if (!("".equals(title))) {
			content = Sets.intersection(content, this.filterContentByTitle(title));
		}
		if (!("".equals(format))) {
			content = Sets.intersection(content, this.filterContentByFormat(format));
		}
		if (!(moduleIds.isEmpty())) {
			content = Sets.intersection(content, this.filterContentBySubjectIds(moduleIds));
		}
		// this is an AND search, if you want to do an OR search, just use the
		// <set>.addAll() method instead of the Sets.intersection() method
		return content;

	}

	/**
	 * Filter takes a content title, content format and/or a list of Integers that
	 * represent module ids. It will then return a set of content that contains all
	 * content that matches all 3 inputs using AND logic. If an input is empty it is
	 * ignored and is not part of the logic.
	 */
	@Override
	@LogException
	public Set<Request> filterReq(String title, String format, List<Module> modules) {

		boolean orSearch = false; // Allows switching between AND and OR module search

		Set<Request> requests = null;
		Set<Request> copy = null;

		if (format != null && !format.equals("All") && !format.equals("")) {
			requests = rr.findByFormat(format);
		}

		if (title != null && !title.equals("")) {

			if (requests == null) {

				requests = rr.findByTitleContaining(title);

			} else {

				copy = new HashSet<Request>(requests);

				for (Request r : copy) {

					if (!r.getTitle().toLowerCase().contains(title.toLowerCase()))
						requests.remove(r);
				}
			}
		}

		if (requests == null) {
			requests = rs.getAllRequests();
		}

		if (modules != null && !modules.isEmpty()) {

			copy = new HashSet<Request>(requests);
			// Set<ReqLink> reqLinksInModules = rlr.findByReqModuleIdIn(modules);

			boolean inModule;

			for (Request r : copy) {

				List<Module> reqLinkModules = new ArrayList<Module>();

				for (ReqLink rl : r.getReqLinks()) {
					reqLinkModules.add(rl.getReqModule());
				}

				/**
				 * For inclusive (OR) search, you want inModule false to begin with, and to flip
				 * inModule the moment a search tag is detected, which tells the method that the
				 * content should be included in the search results
				 * 
				 * For exclusive (AND) search, you want inModule to start as true and to flip
				 * inModule to reject the content the moment a search tag is not found in the
				 * content.
				 */
				inModule = !orSearch;

				// This iterates through modules selected in the search box
				for (Module m : modules) {

					// When orSearch is true, any detected tag adds the content to the search true
					// == true
					// When false, the current content lacking a module is rejected false == false
					if (reqLinkModules.contains(m) == orSearch) {

						inModule = !inModule;
						break;
					}
				}

				// Removes content from the search if it does not belong there
				if (!inModule) {
					requests.remove(r);
				}
			}
		}

		return requests;
	}

	@Override
	public Set<Content> filterContent(Set<Content> contents, Map<String, Object> filters) {

		Set<Content> filteredContent = new HashSet<Content>();
		String title = filters.get("title").toString();
		String format = filters.get("format").toString();
		ArrayList<Integer> moduleIdsList = new ArrayList<Integer>();
		Set<Integer> givenModIds = new HashSet<Integer>();

		//turn the string of integers we recieved into an ArrayList of integers
		StringTokenizer st = new StringTokenizer(filters.get("modules").toString(), ",");
		while (st.hasMoreTokens()) {
			moduleIdsList.add(Integer.parseInt(st.nextToken()));
		}

		//Step through each content provided to see if they are what we are looking for
		for (Content content : contents) {
			//if a search parameter is left "blank", then it is supposed to be disregarded in the search
			if ((title.equals(content.getTitle()) || title.equals("")) && (format.equals(content.getFormat()) || format.equals(""))) {
				//make sure givenModIds starts empty
				givenModIds.clear();
				//extract the ids of the modules of the current content
				for (Link link : content.getLinks()) {
					givenModIds.add(link.getId());
				}
				//check if the current content contains all of the mod id's in the filter
				boolean hasAllModIds = true;
				for (Integer i : moduleIdsList) {
					if (!(givenModIds.contains(i))) {
						hasAllModIds = false;
						break;
					}
				}
				if (hasAllModIds) {
					filteredContent.add(content);
				}
			}
		}

		// this is an AND search, if you want to do an OR search, just use the
		// <set>.addAll() method instead of the Sets.intersection() method
		return filteredContent;
	}

}
