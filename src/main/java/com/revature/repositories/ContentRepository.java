package com.revature.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.entities.Content;
import com.revature.entities.ContentMinusLinks;

@Repository
public interface ContentRepository extends CrudRepository<Content,Integer>{
	
	//Gets content by title
	Set<Content> findByTitle(String title);
	
	//Gets content by format
	Set<Content> findByFormat(String format);
	
	Set<Content> findById(int id);
	
	// return set of contents here; we will iterate through that set in timegraph service impl. 
	Set<Content> findByDateCreatedBetween(long startTime, long currentTime);
	
	//Gets content by title
	Set<Content> findByTitleContaining(String title);
	
	List<ContentMinusLinks> findAllContentBy();
	
}
