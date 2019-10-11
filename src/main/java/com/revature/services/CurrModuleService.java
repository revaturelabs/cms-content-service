package com.revature.services;

import java.util.Set;

import com.revature.entities.CurrModule;

public interface CurrModuleService {
	
	public CurrModule createCurrModule(CurrModule currM);
	
	public Set<CurrModule> getAllCurrModules();
	
	public CurrModule getCurrModuleById(int id);
	
	public Set<CurrModule> getCurrModulesByName(String name);
	
	public CurrModule updateCurrModule(CurrModule currM, int importance);
	
	public void deleteCurriculumModule(CurrModule currM);
}
