package com.revature.services;

import java.util.Set;

import com.revature.entities.Curriculum;

public interface CurriculumService {

	public Curriculum createCurriculum(Curriculum curr);
	
	public Set<Curriculum> getAllCurriculums();
	
	public Curriculum getCurriculumByName(String name);
	 
	public Curriculum getCurriculumById(int id);
	
	public Curriculum updateCurriculum(Curriculum curr);
	
	public void deleteCurriculum(Curriculum curr);
	
	
}
