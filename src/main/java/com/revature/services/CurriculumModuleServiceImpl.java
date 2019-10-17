package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.CurriculumModule;
import com.revature.repositories.CurriculumModuleRepository;
import com.revature.util.LogException;

@Service
public class CurriculumModuleServiceImpl implements CurriculumModuleService  {

	@Autowired
	CurriculumModuleRepository cmR;
	
	@Override
	@LogException
	public CurriculumModule createCurrModule(CurriculumModule currM) {
		return cmR.save(currM);
	}

	@Override
	@LogException
	public Set<CurriculumModule> getAllCurrModules() {
		Set<CurriculumModule> currMods = new HashSet<>();
		cmR.findAll().forEach(currMods::add);
		return currMods;
	}

	@Override
	public CurriculumModule getCurrModuleById(int id) {
		CurriculumModule cMod = cmR.findById(id);
		return cMod;
	}


	@Override
	public Iterable<CurriculumModule> updateCurrModule(Set<CurriculumModule> currM) {
		return cmR.saveAll(currM);
	}

	@Override
	public void deleteCurriculumModule(CurriculumModule currM) {
		if(currM != null) {
			cmR.delete(currM);
		}
	}

}
