package com.revature.services;

import java.util.Set;

import com.revature.entities.CurriculumModule;

public interface CurriculumModuleService {
	
	public CurriculumModule createCurriculumModule(CurriculumModule currM);
	
	public Set<CurriculumModule> getAllCurriculumModules();
	
	public CurriculumModule getCurriculumModuleById(int id);
		
	public Iterable<CurriculumModule> updateCurriculumModule(Set<CurriculumModule> currM);
	
	public void deleteCurriculumModule(CurriculumModule currM);
}
