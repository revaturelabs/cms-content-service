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
	CurriculumRepository cr;
	
	@Override
	@LogException
	public Curriculum createCurriculum(Curriculum curr) {
		return cr.save(curr);
	}

	@Override
	@LogException
	public Set<Curriculum> getAllCurriculums() {
		Set<Curriculum> currs = new HashSet<>();
		cr.findAll().forEach(currs::add);
		return currs;
	}

	@Override
	@LogException
	public Curriculum getCurriculumByName(String name) {
		Curriculum curr = cr.findByName(name);
		return curr;
	}

	@Override
	public Curriculum updateCurriculum(Curriculum curr) {
		return cr.save(curr);
	}

	@Override
	public void deleteCurriculum(Curriculum curr) {
		if(curr != null) {
			cr.delete(curr);
		}
	}

	@Override
	public Curriculum getCurriculumById(int id) {
		Curriculum curr = cr.findById(id);
		return curr;
	}

}
