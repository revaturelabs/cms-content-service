package com.revature.services;

import java.util.ArrayList;
import java.util.Set;

import com.revature.entities.ReqModule;

public interface RequestModuleService {

	public Set<ReqModule> getAllReqModules();

	public ReqModule getReqModuleById(int id);

	public ReqModule createReqModule(ReqModule request);

	public double getAverageByReqModuleIds(ArrayList<Integer> ids);

	public double getAverageByAllReqModules();

	public void deleteReqModule(ReqModule request);
	
	public Set<ReqModule> getAllReqModulesByRoot();
	
	public Set<ReqModule> getChildrenByReqModuleId(int id);
	
	public void setChildToParent(int parentId, int childId);

	public void deleteReqModuleWithAllContent(ReqModule request);

	public void deleteReqModuleWithSpecificContent(ReqModule request);
}
