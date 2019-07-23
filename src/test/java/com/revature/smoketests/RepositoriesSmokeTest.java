package com.revature.smoketests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.transaction.annotation.Transactional;

import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.repositories.ContentRepository;
import com.revature.repositories.LinkRepository;
import com.revature.repositories.ModuleRepository;

import org.junit.jupiter.api.MethodOrderer;


/**
 * Simple testing for all of the repositories.
 * 
 * <p> Meant as a sanity test for the repositories. Vaguely based on code made by a previous sprint,
 * but altered for the sake of atomicity and removing the need for ordering.
 * 
 * @author wsm
 * @version 2.0
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = com.revature.cmsforce.CMSforceApplication.class)
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class RepositoriesSmokeTest {

	@Autowired
	ModuleRepository mr;

	@Autowired
	ContentRepository cr;

	@Autowired
	LinkRepository lr;
	
	@Autowired
	JdbcTemplate template;
		
	/**
	 * Overarching test for Module saving and deletion.
	 * 
	 * <p> Simply creates a new module, adds it, updates it, and then deletes it, confirming success after saves and deletion.
	 */
//	@Test
//	@Commit
	public void ModuleStandaloneTest()
	{
		Module m = new Module();
		m.setSubject("A valid testing subject");
		m.setId(0);
		m.setCreated(System.currentTimeMillis());
		m = mr.save(m);
		
		boolean savedProperly = mr.findBysubject(m.getSubject()).contains(m);
		
		String subj = "Another valid subject";
		m.setSubject(subj);
		
		m = mr.save(m);
		
		boolean mergedProperly = m.getSubject().equals(subj);
		
		mr.delete(m);
		
		Module result = mr.findById(m.getId());
		boolean deletedProperly = (result==null);
		
		assertTrue(savedProperly);
		assertTrue(mergedProperly);
		assertTrue(deletedProperly);
	}
	
	/**
	 * Overarching test for Content saving and deletion.
	 * 
	 * <p> Simply creates new content, adds it, updates it, and deletes it, confirming success after saves and deletion.
	 */
//	@Test
//	@Commit
	public void ContentStandaloneTest()
	{
		Content c = new Content();
		c.setDateCreated(System.currentTimeMillis());
		c.setLastModified(System.currentTimeMillis());
		c.setDescription("A valid description");
		c.setFormat("Code");
		c.setTitle("A valid title");
		c.setUrl("http://valid.valid");
		c.setLinks(null);
		
		c = cr.save(c);
		
		boolean savedProperly = cr.findByFormat("Code").contains(c);
		
		String title = "Another valid title";
		c.setTitle(title);
		
		c = cr.save(c);
		
		boolean mergedProperly = cr.findByTitle(title).contains(c);
		
		cr.delete(c);
		Set<Content> result = cr.findById(c.getId());
		boolean deletedProperly = (result == null || result.isEmpty());
		
		assertTrue(savedProperly);
		assertTrue(mergedProperly);
		assertTrue(deletedProperly);
	}
	
	/**
	 * Saving and Deletion test for links. Is dependent on Module and Content being functional.
	 * 
	 * <p> Adds and deletes links, confirming success after save and deletion. Adds and removes a module and a content in the process.
	 */
//	@Test
//	@Commit
	public void LinkSaveDelTest()
	{		
		//Add Content to link.
		Content c = new Content();
		c.setDateCreated(System.currentTimeMillis());
		c.setLastModified(System.currentTimeMillis());
		c.setDescription("A valid description");
		c.setFormat("Code");
		c.setTitle("A valid title");
		c.setUrl("http://valid.valid");
		c.setLinks(null);
		
		c = cr.save(c);
		
		//Add Module to link.
		Module m = new Module();
		m.setSubject("A valid testing subject");
		m.setId(0);
		m.setCreated(System.currentTimeMillis());
		m = mr.save(m);

		Link l = new Link();
		l.setContentId(c.getId());
		l.setModuleId(m.getId());
		l.setAffiliation("RelevantTo");
		
		l = lr.save(l);
		
		boolean savedProperly = lr.findByContentId(c.getId()).contains(l);
		
		lr.delete(l);
		
		Optional<Link> result = lr.findById(l.getId());
		boolean deletedProperly = ((result == null) || result.isPresent() == false);
		
		mr.delete(m);
		cr.delete(c);
		
		assertTrue(savedProperly);
		assertTrue(deletedProperly);
	}
	
}
