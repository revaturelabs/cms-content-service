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
import com.revature.entities.Curriculum;
import com.revature.entities.CurriculumModule;
import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.entities.ReqLink;
import com.revature.entities.Request;
import com.revature.repositories.ContentRepository;
import com.revature.repositories.ModuleRepository;
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
	@Autowired
	CurriculumService crs;
	@Autowired
	CurriculumModuleService cms;

	/**
	 * filterContentByTitle takes in a string value and returns a set of content object(s)
	 */
	@Override
	@LogException
	public Set<Content> filterContentByTitle(String title) {
		return cr.findByTitle(title);
	}

	/**
	 * filderContentByFormat takes in a string value and returns a set of content object(s)
	 */
	@Override
	@LogException
	public Set<Content> filterContentByFormat(String format) {
		
		
		if(format.equals("Flagged")) {
			
			
			Set<Content> allContent = cs.getAllContent();
			Set<Content> response = new HashSet<Content>();
			
			
			
			for(Content content: allContent) {
				
				
				if(content.getFormat().isEmpty()) {
					
					response.add(content);
				}
			}
			
			return response;
		}
		else {
			
			return cr.findByFormat(format);
		}
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
	public Set<Content> filter(String title, List<String> formatList, List<Integer> moduleIds, List<Integer> curriculaIds) {
		
		Set<Content> content = cs.getAllContent();

		if (!("".equals(title))) {
			content = Sets.intersection(content, this.filterContentByTitle(title));
		}
		
		if (!(formatList.isEmpty())) {
			
			Set<Content> formatContent = new HashSet<>();
			
			for(String format : formatList) {
				
				formatContent.addAll(this.filterContentByFormat(format));
				
			}
			
			content = Sets.intersection(content, formatContent);
		}
		
		if (!(moduleIds.isEmpty())) {
			
			content = Sets.intersection(content, this.filterContentBySubjectIds(moduleIds));
			
		}
		if(curriculaIds != null && !(curriculaIds.isEmpty())) {
			
			content = Sets.intersection(content, this.filterContentByCurriculaIds(curriculaIds));
			
			for(Content c : content) {
				
				if(c != null && c.getLinks().isEmpty()) {
					content.remove(c);
				}
			}
		}
		
		
		// this is an AND search, if you want to do an OR search, just use the
		// <set>.addAll() method instead of the Sets.intersection() method
		return content;

	}
	
	/**
	 * This Method is used to return a set of content that has been filtered through criteria that is passed in as arguments.
	 * the arguments can be empty. This method can filter by title, formats, and moduleId
	 */
    public Set<Content> filter(String title, List<String> formatList, List<Integer> moduleIds) {
		
		Set<Content> content = cs.getAllContent();

		if (!("".equals(title))) {
			content = Sets.intersection(content, this.filterContentByTitle(title));
		}
		
		if (!(formatList.isEmpty())) {
			
			Set<Content> formatContent = new HashSet<>();
			
			for(String format : formatList) {
				
				formatContent.addAll(this.filterContentByFormat(format));
				
			}
			
			content = Sets.intersection(content, formatContent);
		}
		
		if (!(moduleIds.isEmpty())) {
			
			content = Sets.intersection(content, this.filterContentBySubjectIds(moduleIds));
			
		}
		
		// this is an AND search, if you want to do an OR search, just use the
		// <set>.addAll() method instead of the Sets.intersection() method
		return content;

	}
	
    /**
     * @param curriculaIds (A List of Integers)
     * @return A set of content that has been filtered by curriculaIds
     */
	public Set<Content> filterContentByCurriculaIds(List<Integer> curriculaIds) {
		
		Set<Curriculum> curricula = new HashSet<Curriculum>();
		
		Set<CurriculumModule> curriculumModules = new HashSet<CurriculumModule>();
		
		Set<Module> modules = new HashSet<Module>();
		
		Set<Link> links = new HashSet<Link>();
		
		Set<Content> content = new HashSet<Content>();
		
		Set<CurriculumModule> allCurriculumModules = cms.getAllCurriculumModules();
		
		for(Integer id : curriculaIds) {
			
			curricula.add(crs.getCurriculumById(id));
		}
		
		for (Curriculum curriculum : curricula ) {
			
			for(CurriculumModule curriculumModule : allCurriculumModules) {
				
				if(curriculum.getId() == curriculumModule.getCurriculum()) {
					
					curriculumModules.add(curriculumModule);
				}
			}
			
		}
		for(CurriculumModule curriculumModule : curriculumModules) {
			
			modules.add(curriculumModule.getModule());
			
		}
		for(Module module : modules) {
			
			links.addAll(module.getLinks());
			
		}
		for(Link link : links) {
			
			content.add(link.getContent());
		}
	
		return content;
	}

    /**
     * @param Set<Content> contents, Map<String, Object> filters
     * @return a Set of Content
     */
	@Override
	public Set<Content> filterContent(Set<Content> contents, Map<String, Object> filters) {

		Set<Content> filteredContent = new HashSet<Content>();
		String title = filters.get("title").toString();
		
		String format = filters.get("format").toString();
		
		ArrayList<Integer> moduleIdsList = new ArrayList<Integer>();
		Set<Integer> givenModIds = new HashSet<Integer>();
		// turn the string of integers we recieved into an ArrayList of integers
		StringTokenizer st = new StringTokenizer(filters.get("modules").toString(), ",");
		while (st.hasMoreTokens()) {
			moduleIdsList.add(Integer.parseInt(st.nextToken()));
		}
		// Step through each content provided to see if they are what we are looking for
		for (Content content : contents) {
			// if a search parameter is left "blank", then it is supposed to be disregarded
			// in the search
			
			if ((title.equals(content.getTitle()) || title.equals(""))
					&& (format.equals(content.getFormat()) || format.equals("") || format.equals("All"))) {
				// make sure givenModIds starts empty
				givenModIds.clear();
				// extract the ids of the modules of the current content
				for (Link link : content.getLinks()) {
					givenModIds.add(link.getModule().getId());
				}
				// check if the current content contains all of the mod id's in the filter
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

	/**
	 * 
	 */
	@Override
	public Set<Request> filterRequestByTitle(String title) {
		return rr.findByTitle(title);
	}

	@Override
	public Set<Request> filterRequestByFormat(String format) {
		return rr.findByFormat(format);
	}

	@Override
	public Set<Request> filterRequestBySubjectIds(List<Integer> moduleIds) {
		Set<Module> modules = new HashSet<Module>();
		Set<ReqLink> reqLinks = new HashSet<ReqLink>();
		Set<Request> tempRequests = new HashSet<Request>();
		Set<Request> requests = new HashSet<Request>();

		// get modules by moduleIds
		for (Integer id : moduleIds) {
			modules.add(ms.getModuleById(id));
		}
		// get all requests of each module
		for (Module module : modules) {

			// get links of module, the reqLinks hold the request within them
			reqLinks = module.getReqLinks();

			// make sure tempRequest is empty at the start of a new iteration
			tempRequests.clear();

			// add requests in reqLinks to tempRequests so we can work with it
			for (ReqLink reqLink : reqLinks) {
				tempRequests.add(reqLink.getRequest());
			}
			// if it is the first iteration, we just want to put tempRequests into requests
			if (requests.isEmpty()) {
				requests = tempRequests;
			}
			// in order to only get requests associated to ALL provided modules, we perform
			// an intersection on requests and tempRequests
			else {
				requests = Sets.intersection(requests, tempRequests);

				// if requests is empty after the intersection, then there is no request
				// associated with ALL provided modules
				if (requests.isEmpty()) {
					return requests;
				}
			}
		}
		return requests;
	}

	/**
	 * Filter takes a content title, content format and/or a list of Integers that
	 * represent module ids. It will then return a set of content that contains all
	 * content that matches all 3 inputs using AND logic. If an input is empty it is
	 * ignored and is not part of the logic.
	 */
	@Override
	@LogException
	public Set<Request> filterReq(String title, List<String> formatList, List<Integer> moduleIds) {
		Set<Request> requests = rs.getAllRequests();

		if (!("".equals(title))) {
			requests = Sets.intersection(requests, this.filterRequestByTitle(title));
		}
		if (!(formatList.isEmpty())) {
			
			Set<Request> formatRequest = new HashSet<>();
			
			for(String format : formatList) {
				formatRequest.addAll(this.filterRequestByFormat(format));
			}
			
			requests = Sets.intersection(requests, formatRequest);
			
			
		}
		if (!(moduleIds.isEmpty())) {
			requests = Sets.intersection(requests, this.filterRequestBySubjectIds(moduleIds));
		}
		// this is an AND search, if you want to do an OR search, just use the
		// <set>.addAll() method instead of the Sets.intersection() method
		return requests;
	}
}
