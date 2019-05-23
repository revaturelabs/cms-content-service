package com.revature.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.entities.Content;
import com.revature.entities.ContentModule;

@Repository
public interface ContentModuleRepository extends CrudRepository<ContentModule,Integer> {
	Set<ContentModule> findByfkContent(int fkContent);
	Set<ContentModule> findByfkModule(int fkModule);

}
