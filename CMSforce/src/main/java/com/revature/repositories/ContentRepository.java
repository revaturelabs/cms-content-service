package com.revature.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.entities.Content;

@Repository
public interface ContentRepository extends CrudRepository<Content,Integer>{

}
