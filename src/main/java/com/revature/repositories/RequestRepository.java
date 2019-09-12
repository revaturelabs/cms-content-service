package com.revature.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.entities.Requests;
import com.revature.entities.RequestsMinusReqLinks;

@Repository
public interface RequestRepository extends CrudRepository<Requests,Integer>{
	
	//Gets content by title
	Set<Requests> findByTitle(String title);
	
	//Gets content by format
	Set<Requests> findByFormat(String format);
	
	Set<Requests> findById(int id);
	
	// return set of contents here; we will iterate through that set in timegraph service impl. 
	Set<Requests> findByDateCreatedBetween(long startTime, long currentTime);
	
	//Gets content by title
	Set<Requests> findByTitleContaining(String title);
	
	List<RequestsMinusReqLinks> findAllRequestsBy();
	
}
