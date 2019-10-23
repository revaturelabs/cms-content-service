package com.revature.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.entities.Request;

@Repository
public interface RequestRepository extends CrudRepository<Request,Integer>{
	
	//Gets content by title
	Set<Request> findByTitle(String title);
	
	//Gets content by format
	Set<Request> findByFormat(String format);
	
	Request findById(int id);
	
	// return set of contents here; we will iterate through that set in timegraph service impl. 
	Set<Request> findByDateCreatedBetween(long startTime, long currentTime);
	
	//Gets content by title
	Set<Request> findByTitleContaining(String title);
	
}
