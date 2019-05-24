package com.revature.cmsforce;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.revature.entities.Content;
import com.revature.entities.ContentModule;
import com.revature.entities.Module;
import com.revature.repositories.ContentModuleRepository;
import com.revature.repositories.ContentRepository;
import com.revature.repositories.ModuleRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=com.revature.cmsforce.CMSforceApplication.class)
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
		assertEquals(allContents.size(),contents.size(), "Number of results is equal");
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
		assertEquals(allContents.size(),contents.size(),"Number of results is equal");
		
		
	}
	@Test
	public void getModuleBySubject() {
		Set<Module> modules = null;
		modules = mr.findBysubject("JavaScript");
		List<Module> allModules = new ArrayList<Module>();
		mr.findAll().forEach(allModules::add);
		allModules.stream()
			.filter((cont)->cont.getSubject().equals("JavaScript"))
		.collect(Collectors.toList());
		assertEquals(allModules.size(),modules.size(),"Number of results is equal");
		
		
	}
	@Test
	public void ContentModuleEntitySave() {
		ContentModule cm = new ContentModule();
		
		Module js = mr.findById(6).get();
		Content cont = cr.findById(5).get();
		List<Module> modules = new ArrayList<Module>();
		List<Content> contents = new ArrayList<Content>();
		modules.add(js);
		contents.add(cont);
		cm.setContents(contents);
		cm.setModules(modules);
		cmr.save(cm);
		
	}

}
