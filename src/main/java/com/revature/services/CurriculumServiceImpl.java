package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Curriculum;
import com.revature.repositories.CurriculumRepository;
import com.revature.util.LogException;

/**
 * 
 * @author Java Batch 1908
 *
 *         Service Implementation of CurriculumService
 */
@Service
public class CurriculumServiceImpl implements CurriculumService {

	@Autowired
	CurriculumRepository curriculumRepository;

	@Override
	@LogException
	/**
	 * Service method to create new curriculum record in database
	 * 
	 * @param curriculum - Curriculum Object to create
	 * @return curriculum - Curriculum Object that be created
	 * 
	 */
	public Curriculum createCurriculum(Curriculum curriculum) {
		return curriculumRepository.save(curriculum);
	}

	@Override
	@LogException
	/**
	 * Service method to get all curriculums available in the database
	 *  
	 * @return Set<Curriculum> - set of Curriculum Objects that are obtained
	 * 
	 */
	public Set<Curriculum> getAllCurriculums() {
		Set<Curriculum> curriculums = new HashSet<>();
		curriculumRepository.findAll().forEach(curriculums::add);
		return curriculums;
	}

	@Override
	@LogException
	/**
	 * Service method to get a Curriculum Object by Curriculum name
	 * 
	 * @param name - Curriculum name
	 * @return curriculum - obtained Curriculum Object with given name 
	 * 
	 */
	public Curriculum getCurriculumByName(String name) {
		Curriculum curriculum = curriculumRepository.findByName(name);
		return curriculum;

	}

	/**
	 * Service method to update Curriculum Objects
	 * 
	 * @param curriculum - Curriculum Object to update
	 * @return curriculum - Updated Curriculum Object
	 */
	@Override
	public Curriculum updateCurriculum(Curriculum curriculum) {
		return curriculumRepository.save(curriculum);
	}

	/**
	 * Service method to delete Curriculum Object
	 * 
	 * @param curriculum - Curriculum Object to delete 
	 */
	@Override
	public void deleteCurriculum(Curriculum curriculum) {
		if (curriculum != null) {
			curriculumRepository.delete(curriculum);
		}
	}

	/**
	 * Service method to get a Curriculum Object by Curriculum id
	 * 
	 * @param id - Curriculum Id
	 * @return curriculum - obtained Curriculum Object with given Id
	 */
	@Override
	public Curriculum getCurriculumById(int id) {
		Curriculum curriculum = curriculumRepository.findById(id);
		return curriculum;
	}

}