package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.CurrModule;
import com.revature.repositories.CurrModuleRepository;
import com.revature.util.LogException;

@Service
public class CurrModuleServiceImpl implements CurrModuleService  {

	@Autowired
	CurrModuleRepository cmR;
	
	@Override
	@LogException
	public CurrModule createCurrModule(CurrModule currM) {
		return cmR.save(currM);
	}

	@Override
	@LogException
	public Set<CurrModule> getAllCurrModules() {
		Set<CurrModule> currMods = new HashSet<>();
		cmR.findAll().forEach(currMods::add);
		return currMods;
	}

	@Override
	public CurrModule getCurrModuleById(int id) {
		CurrModule cMod = cmR.findById(id);
		return cMod;
	}


	@Override
	public Iterable<CurrModule> updateCurrModule(Set<CurrModule> currM) {
		return cmR.saveAll(currM);
	}

	@Override
	public void deleteCurriculumModule(CurrModule currM) {
		if(currM != null) {
			cmR.delete(currM);
		}
	}

}
