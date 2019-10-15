package com.revature.testingutils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.entities.ReqLink;
/**
 * This is a utility class meant to reduce the repetition caused by
 * creating Content instances throughout unit testing. This class 
 * provides a basic Content instance and it's relationship with a
 * Link object instance.
 */
public class ContentFactory {
	private static final int MAX_CHAR_LENGTH = 254;
	public static final String title = "TestTitle",
							    format = "TestFormat",
							    description = "Test description",
							    url = "http://test.com";
	public static final Integer id = 117,
								moduleId = 1,
								linkId = 1;
	private static String badString="badbadbad";
	public static String getBadString() {
		for(int i=0;i<MAX_CHAR_LENGTH;i++) {
			badString =badString+"BAD";
		}
		return badString;
	}
	public static int getMaxCharLength() {
		return MAX_CHAR_LENGTH;
	}
	/**
	 * Returns a new instance of a content object with the default
	 * constant values provided in this class
	 * @return - new instance of a content object
	 */
	public static Content getContent () {
		//generate link set
		Set<Link> links = new HashSet<Link> ();
		Link link = new Link (linkId, null, null,"link-affiliation");
		links.add(link);
		Module module = new Module(moduleId, "test_module", 1L, links, new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		//generate content object
		Content content = new Content (id, title, format, description, url, 1L, 1L, links);
		link.setContent(content);
		link.setModule(module);
		return content;
	}
	public static String badS = getBadString();
	public static Content contentForExceptions(int condition) {
		//generate link set
				Set<Link> links = new HashSet<Link> ();
				Link link = new Link (linkId, null, null,"link-affiliation");
				links.add(link);
				Module module = new Module(moduleId, "test_module", 1L, links, new HashSet<ReqLink>(),
						new HashSet<Module>(), new HashSet<Module>());
				String[] parameters = {title, format, description, url};
				switch (condition) {
				case 0: parameters[0]=badS;
						break;
				case 1:	parameters[1]=badS;
						break;
				case 2:	parameters[2]=badS;
						break;
				case 3: parameters[3]=badS;
						break;		
				}
				Content content = new Content (id, parameters[0], parameters[1], parameters[2], parameters[3], 1L, 1L, links);
				link.setContent(content);
				link.setModule(module);
				System.out.println(content.toString());
				return content;
				
	}
	public static Module moduleForException() {
		Module badModule = new Module();
		badModule.setSubject(badS);
		return badModule;
	}
	/**
	 * Returns a list of modules meant to be related to the default link
	 * data.
	 * @return - List of module ids related to the sample content
	 */
	public static List<Integer> getModules () {
		List<Integer> modules = new ArrayList<Integer> ();
		modules.add(moduleId);
		return modules;
	}
}
