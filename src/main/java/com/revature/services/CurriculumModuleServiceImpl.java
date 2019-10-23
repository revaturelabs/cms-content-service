package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.CurriculumModule;
import com.revature.repositories.CurriculumModuleRepository;
import com.revature.util.LogException;

@Service
/**
 * 
 * @author Java Batch 1908
 * 
 *          Service Implementation for CurriculumModuleService
 *
 */
public class CurriculumModuleServiceImpl implements CurriculumModuleService  {

	@Autowired
	CurriculumModuleRepository curriculumModuleRepository;
	
	@Override
	@LogException
	/**
	 * Service method to create a CurriculumModule record in database
	 * 
	 * @param curriculumModule - CurriculumModule Object to create
	 * @return curriculumModule -created CurriculumModule Object 
	 * 
	 */
	public CurriculumModule createCurriculumModule(CurriculumModule curriculumModule) {
		return curriculumModuleRepository.save(curriculumModule);
	}

	@Override
	@LogException
	/**
	 *  Service method to get all CurriculumModule Objects available in database 
	 *  
	 * @return Set<CcurriculumModule> - a set of obtained CurriculumModule Objects 
	 *	 
	 */
	public Set<CurriculumModule> getAllCurriculumModules() {
		Set<CurriculumModule> curriculumModules = new HashSet<>();
		curriculumModuleRepository.findAll().forEach(curriculumModules::add);
		return curriculumModules;
	}

	@Override
	/**
	 *   
	 * Service method to get a CurriculumModule record with the given id 
	 * 
	 * @param id - CurriculumModule id
	 * @return curriculumModule - obtained CurriculumModule Object with given id
	 */
	public CurriculumModule getCurriculumModuleById(int id) {
		CurriculumModule curriculumModule = curriculumModuleRepository.findById(id);
		return curriculumModule;
	}

	
	@Override
	/**
	 * Service method to update CurriculumModule objects
	 * 
	 * @param curriculumModules - a set of CurriculumModule Objects to update
	 * @return curriculumModules - updated collection of CurriculumModule Objects 
	 */
	public Iterable<CurriculumModule> updateCurriculumModule(Set<CurriculumModule> curriculumModule) {
		return curriculumModuleRepository.saveAll(curriculumModule);
	}

	@Override
	/**
	 * Service method to delete Curriculum Object
	 * 
	 * @param curriculumModule - CurriculumModule Object to delete
	 *
	 */
	public void deleteCurriculumModule(CurriculumModule curriculumModule) {
		if(curriculumModule != null) {
			curriculumModuleRepository.delete(curriculumModule);
		}
	}

	
}
