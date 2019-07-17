package com.revature.smoketests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.repositories.LinkRepository;
import com.revature.repositories.ContentRepository;
import com.revature.repositories.ModuleRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = com.revature.cmsforce.CMSforceApplication.class)
@SpringBootTest
class RepositoriesSmokeTestsJohn {

	@Autowired
	ModuleRepository mr;

	@Autowired
	ContentRepository cr;

	@Autowired
	LinkRepository lr;

	@Test
	public void ModuleEntitySaveTest() {
		Module m = new Module();

		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		Random random = new Random();
		int targetStringLength = random.nextInt(20);
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();

		m.setSubject(generatedString);
		mr.save(m);
	}

	@Test
	public void ContentEntitySaveTest() {
		Content c = new Content();
		c.setDescription("Description");
		c.setFormat("format");
		c.setTitle("Title");
		c.setUrl("URI");
		cr.save(c);

	}

	@Test
	public void getContentByTitleTest() {
		Set<Content> contents = null;
		contents = cr.findByTitle("Title");
		List<Content> allContents = new ArrayList<Content>();
		cr.findAll().forEach(allContents::add);
		allContents.stream().filter((cont) -> cont.getTitle().equals("Title")).collect(Collectors.toList());
		assertEquals(allContents.size(), contents.size(), "Number of results is equal");
	}

	@Test
	public void getContentByFormatTest() {
		Set<Content> contents = null;
		contents = cr.findByFormat("format");
		List<Content> allContents = new ArrayList<Content>();
		cr.findAll().forEach(allContents::add);
		allContents.stream().filter((cont) -> cont.getFormat().equals("format")).collect(Collectors.toList());
		assertEquals(allContents.size(), contents.size(), "Number of results is equal");

	}

	@Test
	public void getModuleBySubjectTest() {
		Set<Module> allModules = new HashSet<Module>();
		mr.findAll().forEach(allModules::add);

		Iterator<Module> iter = allModules.iterator();
		Module first = iter.next();

		Set<Module> modules = mr.findBysubject(first.getSubject());

		assertEquals(modules.size(), 1);

	}

	@Test
	public void LinkEntitySaveTest() {
		Link link = new Link();

		Module js = mr.findById(6);
		Content cont = cr.findById(5).iterator().next();
		Set<Module> modules = new HashSet<Module>();
		Set<Content> contents = new HashSet<Content>();
		modules.add(js);
		contents.add(cont);
		// cm.setContents(contents);
		// cm.setModules(modules);
		lr.save(link);

	}

}
