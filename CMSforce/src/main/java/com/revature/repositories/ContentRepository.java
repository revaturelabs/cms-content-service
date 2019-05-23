package com.revature.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.entities.Content;

@Repository
public interface ContentRepository extends CrudRepository<Content,Integer>{
	Set<Content> findByTitle(String title);
	Set<Content> findByFormat(String Format);
}
