package com.revature.smoketests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
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
	 * Tests if the module gets saved correctly.
	 */
	@Test
	@Rollback
	public void ModuleStandaloneSaveTest()
	{
		Module m = new Module();
		m.setSubject("A valid testing subject");
		m.setId(0);
		m.setCreated(new Timestamp(System.currentTimeMillis()));
		m = mr.save(m);
		
		boolean savedProperly = mr.findBysubject(m.getSubject()).contains(m);
				
		assertTrue(savedProperly);
	}
	
	/**
	 * Tests if the module gets updated properly.
	 */
	@Test
	@Rollback
	public void ModuleStandaloneUpdateTest()
	{
		Module m = new Module();
		m.setSubject("A valid testing subject");
		m.setId(0);
		m.setCreated(new Timestamp(System.currentTimeMillis()));
		m = mr.save(m);
		
		String subj = "Another valid subject";
		m.setSubject(subj);
		
		m = mr.save(m);

		boolean mergedProperly = m.getSubject().equals(subj);
		assertTrue(mergedProperly);
	}
	
	/**
	 * Tests if the module gets deleted properly.
	 */
	@Test
	@Rollback
	public void ModuleStandaloneDeleteTest()
	{
		Module m = new Module();
		m.setSubject("A valid testing subject");
		m.setId(0);
		m.setCreated(new Timestamp(System.currentTimeMillis()));
		m = mr.save(m);

		mr.delete(m);
		
		Module result = mr.findById(m.getId());
		boolean deletedProperly = (result==null);

		assertTrue(deletedProperly);
	}
		
	/**
	 * Tests if content is created properly.
	 */
	@Test
	@Rollback
	public void ContentStandaloneCreateTest()
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
		assertTrue(savedProperly);
	}
	
	/**
	 * Tests if content is deleted properly.
	 */
	@Test
	@Rollback
	public void ContentStandaloneDeleteTesT()
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
		cr.delete(c);
		Set<Content> result = cr.findById(c.getId());
		boolean deletedProperly = (result == null || result.isEmpty());
		
		assertTrue(deletedProperly);
	}
	
	/**
	 * Test if content is updated proerply.
	 */
	@Test
	@Rollback
	public void ContentStandaloneUpdateTest()
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
		
		String title = "Another valid title";
		c.setTitle(title);
		
		c = cr.save(c);
		
		boolean mergedProperly = cr.findByTitle(title).contains(c);
		assertTrue(mergedProperly);
	}
	
	/**
	 * Tests link saving.
	 */
	@Test
	@Rollback
	public void LinkSaveTest()
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
		
		Module m = new Module();
		m.setSubject("A valid testing subject");
		m.setId(0);
		m.setCreated(new Timestamp(System.currentTimeMillis()));
		m = mr.save(m);

		Link l = new Link();
		l.setContentId(c.getId());
		l.setModuleId(m.getId());
		l.setAffiliation("RelevantTo");
		
		l = lr.save(l);
		
		boolean savedProperly = lr.findByContentId(c.getId()).contains(l);
		assertTrue(savedProperly);
	}
	
	/**
	 * Tests link deletion.
	 */
	@Test
	@Rollback
	public void LinkDelTest()
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
		
		Module m = new Module();
		m.setSubject("A valid testing subject");
		m.setId(0);
		m.setCreated(new Timestamp(System.currentTimeMillis()));
		m = mr.save(m);

		Link l = new Link();
		l.setContentId(c.getId());
		l.setModuleId(m.getId());
		l.setAffiliation("RelevantTo");
		
		l = lr.save(l);
		
		lr.delete(l);
		
		Optional<Link> result = lr.findById(l.getId());
		boolean deletedProperly = ((result == null) || result.isPresent() == false);
		
		mr.delete(m);
		cr.delete(c);
		
		assertTrue(deletedProperly);
	}
}
