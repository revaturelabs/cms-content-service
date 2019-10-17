package com.revature.controllertests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.*;
import java.util.Set;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.cmsforce.CMSforceApplication;
import com.revature.controllers.CurriculumModuleController;
import com.revature.entities.Curriculum;
import com.revature.entities.CurriculumModule;
import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.entities.ReqLink;
import com.revature.services.CurriculumModuleService;

/** Test class for CRUD implementation of curriculum controller test. 
 */

@SpringBootTest(classes = CMSforceApplication.class)
public class CurriculumModuleControllerTest {
	
	private MockMvc mvc;
    private ObjectMapper objMapper = new ObjectMapper();
    
    @InjectMocks
    private CurriculumModuleController cmc;
    
    @Mock
    private CurriculumModuleService cms;
    
    @BeforeClass 
    public void setup() {
    	cmc = new CurriculumModuleController();
    	mvc = MockMvcBuilders.standaloneSetup(cmc).build();
    }
    
    @BeforeMethod
    public void reinitMocks()
    {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void CreateCurriculumModule() throws Exception{
    	Curriculum curriculum = new Curriculum(1,"Java Full Stack");
    	Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
                					new HashSet<Module>(), new HashSet<Module>());
    	
    	CurriculumModule curriculumModule = new CurriculumModule(1,curriculum.getId(),module,1);
    	
    	Mockito.when(cms.createCurriculumModule(curriculumModule)).thenReturn(curriculumModule);

        ResultActions result = mvc.perform( post("/curriculummodules")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objMapper.writeValueAsString(curriculumModule)));

        CurriculumModule actual = objMapper.readValue(result.andReturn().getResponse()
                .getContentAsString(), CurriculumModule.class);


        Mockito.verify(cms).createCurriculumModule(curriculumModule);
        Assert.assertEquals(actual, curriculumModule, "Failed to retrieve expected curriculum module");

    }
    
    @Test
    public void GetCurriculumModuleById() throws  Exception {
    	Curriculum curriculum = new Curriculum(1,"Java Full Stack");
    	Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
                					new HashSet<Module>(), new HashSet<Module>());
    	
    	CurriculumModule curriculumModule = new CurriculumModule(1,curriculum.getId(),module,1);

    	Mockito.when(cms.getCurriculumModuleById(curriculumModule.getId())).thenReturn(curriculumModule);

        ResultActions result = mvc.perform( get("/curriculummodules/" + curriculumModule.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        CurriculumModule actual = objMapper.readValue(result.andReturn().getResponse()
                .getContentAsString(), CurriculumModule.class);

        Mockito.verify(cms).getCurriculumModuleById(curriculumModule.getId());
        Assert.assertEquals(actual, curriculumModule, "Failed to retrieve expected curriculum Module");
    }
	
    @Test
    public void GetAllCurriculumModules() throws Exception {
    	Curriculum curriculum = new Curriculum(1,"Java Full Stack");
    	Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
                					new HashSet<Module>(), new HashSet<Module>());
    	
    	CurriculumModule curriculumModule = new CurriculumModule(1,curriculum.getId(),module,1);
    	
    	Set<CurriculumModule> allCurriculumModules = new HashSet<CurriculumModule>();
        allCurriculumModules.add(curriculumModule);
        
        Mockito.when(cms.getAllCurriculumModules()).thenReturn(allCurriculumModules);

        ResultActions result = mvc.perform( get("/curriculummodules")
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        Set<CurriculumModule> actual = objMapper.readValue(result.andReturn().getResponse()
                .getContentAsString(), new TypeReference<Set<CurriculumModule>>() { });

        Mockito.verify(cms).getAllCurriculumModules();
        Assert.assertEquals(actual, allCurriculumModules, "Failed to retrieve expected curriculum modules");
    }
    
    @Test
    public void UpdateCurriculumModule() throws Exception {
    	Curriculum curriculum = new Curriculum(1,"Java Full Stack");
    	Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
                					new HashSet<Module>(), new HashSet<Module>());
    	
    	CurriculumModule curriculumModule = new CurriculumModule(1,curriculum.getId(),module,1);
    	Set<CurriculumModule> thisCurriculumModule = new HashSet<CurriculumModule>();
    	thisCurriculumModule.add(curriculumModule);
    	List<CurriculumModule> thisCurriculumModule2 = new ArrayList<CurriculumModule>();
    	thisCurriculumModule2.add(curriculumModule);
    	
    	
    	Mockito.when(cms.updateCurriculumModule(thisCurriculumModule)).thenReturn(thisCurriculumModule2);

        ResultActions result = mvc.perform( put("/curriculummodules/update")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objMapper.writeValueAsString(thisCurriculumModule)));

        result.andExpect(status().isOk());

    }
    
    @Test
    public void DeleteCurriculumModule() throws Exception {
    	Curriculum curriculum = new Curriculum(1,"Java Full Stack");
    	Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
                					new HashSet<Module>(), new HashSet<Module>());
    	
    	CurriculumModule curriculumModule = new CurriculumModule(1,curriculum.getId(),module,1);
    	
        Mockito.doNothing().when(cms).deleteCurriculumModule(curriculumModule);

        ResultActions result = mvc.perform( delete("/curriculummodules/" + curriculumModule.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        result.andExpect(status().isOk());
    }
}

