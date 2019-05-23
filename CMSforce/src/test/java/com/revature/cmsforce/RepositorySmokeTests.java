package com.revature.cmsforce;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.entities.Content;
import com.revature.entities.ContentModule;
import com.revature.entities.Module;
import com.revature.repositories.ContentModuleRepository;
import com.revature.repositories.ContentRepository;
import com.revature.repositories.ModuleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositorySmokeTests {

	@Autowired
	ModuleRepository mr;
	
	@Autowired
	ContentRepository cr;
	
	@Autowired
	ContentModuleRepository cmr;
	
	@Test
	public void ModuleEntitySave() {
		Module m = new Module();
		m.setSubject("JavaScript");
		mr.save(m);
	}
	@Test
	public void ContentEntitySave() {
		Content c = new Content();
		c.setDescription("Description");
		c.setFormat("format");
		c.setTitle("Title");
		c.setUrl("URI");
		cr.save(c);
		
	}
	@Test
	public void getContentByTitle() {
		Set<Content> contents = null;
		contents = cr.findByTitle("Title");
		List<Content> allContents=new ArrayList<Content>();
		cr.findAll().forEach(allContents::add);
		allContents.stream()
			.filter((cont)->cont.getTitle().equals("Title"))
			.collect(Collectors.toList());
		assertEquals("Number of results is equal",allContents.size(),contents.size());
	}
	@Test
	public void getContentByFormat() {
		Set<Content> contents = null;
		contents = cr.findByFormat("format");
		List<Content> allContents = new ArrayList<Content>();
		cr.findAll().forEach(allContents::add);
		allContents.stream()
			.filter((cont)->cont.getFormat().equals("format"))
		.collect(Collectors.toList());
		assertEquals("Number of results is equal",allContents.size(),contents.size());
		
		
	}
	@Test
	public void ContentModuleEntitySave() {
		ContentModule cm = new ContentModule();
		
		Module js = mr.findOne(6);
		Content cont = cr.findOne(5);
		List<Module> modules = new ArrayList<Module>();
		List<Content> contents = new ArrayList<Content>();
		modules.add(js);
		contents.add(cont);
		cm.setContents(contents);
		cm.setModules(modules);
		cmr.save(cm);
		
	}

}
