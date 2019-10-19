package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Curriculum;
import com.revature.repositories.CurriculumRepository;
import com.revature.util.LogException;

@Service
public class CurriculumServiceImpl implements CurriculumService{

	@Autowired
	CurriculumRepository curriculumRepository;
	
	@Override
	@LogException
	public Curriculum createCurriculum(Curriculum curriculum) {
		return curriculumRepository.save(curriculum);
	}

	@Override
	@LogException
	public Set<Curriculum> getAllCurriculums() {
		Set<Curriculum> curriculums = new HashSet<>();
		curriculumRepository.findAll().forEach(curriculums::add);
		return curriculums;
	}

	@Override
	@LogException
	public Curriculum getCurriculumByName(String name) {
		Curriculum curriculum = curriculumRepository.findByName(name);
		return curriculum;
	}

	@Override
	public Curriculum updateCurriculum(Curriculum curriculum) {
		return curriculumRepository.save(curriculum);
	}

	@Override
	public void deleteCurriculum(Curriculum curriculum) {
		if(curriculum != null) {
			curriculumRepository.delete(curriculum);
		}
	}

	@Override
	public Curriculum getCurriculumById(int id) {
		Curriculum curriculum = curriculumRepository.findById(id);
		return curriculum;
	}

}
