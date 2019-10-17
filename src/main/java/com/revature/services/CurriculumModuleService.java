package com.revature.services;

import java.util.Set;

import com.revature.entities.CurriculumModule;

public interface CurriculumModuleService {
	
	public CurriculumModule createCurrModule(CurriculumModule currM);
	
	public Set<CurriculumModule> getAllCurrModules();
	
	public CurriculumModule getCurrModuleById(int id);
	
	
	public Iterable<CurriculumModule> updateCurrModule(Set<CurriculumModule> currM);
	
	public void deleteCurriculumModule(CurriculumModule currM);
}
