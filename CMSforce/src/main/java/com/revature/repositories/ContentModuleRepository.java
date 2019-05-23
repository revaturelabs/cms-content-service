package com.revature.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.entities.ContentModule;

@Repository
public interface ContentModuleRepository extends CrudRepository<ContentModule,Integer> {


}
