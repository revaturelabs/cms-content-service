package com.revature.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.entities.Content;

@Repository
public interface ContentRepository extends CrudRepository<Content,Integer>{
	
	//Gets content by title
	Set<Content> findByTitle(String title);
	
	//Gets content by format
	Set<Content> findByFormat(String format);
	
	Set<Content> findById(int id);
}
