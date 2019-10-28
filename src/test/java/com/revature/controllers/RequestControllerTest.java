package com.revature.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.JSONEntities.JSONRequest;
import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.entities.ReqLink;
import com.revature.services.SearchService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.Assert;
import org.testng.annotations.*;

import com.revature.cmsforce.CMSforceApplication;
import com.revature.controllers.RequestController;
import com.revature.entities.Request;
import com.revature.services.RequestService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest(classes = CMSforceApplication.class)
public class RequestControllerTest extends AbstractTestNGSpringContextTests {
	private MockMvc mvc;
	private ObjectMapper objMapper = new ObjectMapper();

	@InjectMocks
	private RequestController rc;

	@Mock
	private RequestService rs;
	@Mock
	private SearchService ss;
	private Request request;

	@BeforeClass
	public void setup() {
		rc = new RequestController();
		mvc = MockMvcBuilders.standaloneSetup(rc).build();
	}

	@BeforeMethod
	public void reinitMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeTest
	public void preTestSetup() {
		request = null;
	}

	/**
	 * This method tests { @link com.revature.controllers.RequestController#createRequest(JSONRequest) createRequest(JSONRequest) }
	 * This takes a JSONRequest and should return the created request.
	 * This mocks RequestService.
	 * @throws Exception
	 */
	@Test
	public void testGivenValidDataCreateRequest() throws Exception {
		Set<ReqLink> reqLinks = new HashSet<>();
		ReqLink reqLink = new ReqLink();
		reqLinks.add(reqLink);
		request = new Request(0, "test title", "code", "test description", null, 1L, 1L, reqLinks);
		Mockito.when(rs.createRequest(request)).thenReturn(request);

		ResultActions result = mvc.perform(post("/requests").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objMapper.writeValueAsString(rc.requestToJSONRequest(request))));

		result.andExpect(status().isOk());
	}

	/**
	 * This method tests { @link com.revature.controllers.RequestController#createReqLinks(List, int) createReqLinks(List, int) }
	 * This takes a set of ReqLinks and an ID, and should return a set of the created ReqLinks.
	 * This tests if the response status is OK
	 * This mocks RequestService.
	 * @throws Exception
	 */
	@Test
	public void testGivenValidDataCreateReqLinksStatusOK() throws Exception {
		request = new Request(0, "test title", "code", "test description", null, 1L, 1L, new HashSet<ReqLink>());
		Module module = new Module(0, "module_subject", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		ReqLink reqLink = new ReqLink(0, request, module, "reqLink_affiliation");
		List<ReqLink> reqLinks = new ArrayList<ReqLink>();
		reqLinks.add(reqLink);

		Mockito.when(rs.createReqLinksByRequestId(request.getId(), reqLinks)).thenReturn(reqLinks);

		ResultActions result = mvc.perform(post("/requests/" + request.getId() + "/req-links")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objMapper.writeValueAsString(reqLinks)));

		result.andExpect(status().isOk());
	}

	/**
	 * This method tests { @link com.revature.controllers.RequestController#createReqLinks(List, int) createReqLinks(List, int) }
	 * This takes a set of ReqLinks and an ID, and should return a set of the created ReqLinks.
	 * This mocks RequestService.
	 * @throws Exception
	 */
	@Test
	public void testGivenValidDataCreateReqLinksTestReturn() throws Exception {
		request = new Request(0, "test title", "code", "test description", null, 1L, 1L, new HashSet<ReqLink>());
		Module module = new Module(0, "module_subject", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		ReqLink reqLink = new ReqLink(0, request, module, "reqLink_affiliation");
		List<ReqLink> reqLinks = new ArrayList<ReqLink>();
		reqLinks.add(reqLink);

		Mockito.when(rs.createReqLinksByRequestId(request.getId(), reqLinks)).thenReturn(reqLinks);

		ResultActions result = mvc.perform(post("/requests/" + request.getId() + "/req-links")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objMapper.writeValueAsString(reqLinks)));

		List<ReqLink> actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				new TypeReference<List<ReqLink>>() {
				});
		Assert.assertEquals(actual, reqLinks, "Failed to retrieve expected request links");
	}

	/**
	 * This method tests { @link com.revature.controllers.RequestController#getAllRequest() getAllRequest() }
	 * This takes no argument and should return a set of Requests.
	 * This tests if the response status is OK
	 * This mocks RequestService.
	 * @throws Exception
	 */
	@Test
	public void testGetAllRequestsStatusOK() throws Exception {
		Set<Request> reqs = new HashSet<Request>();
		request = new Request(0, "test title", "code", "test description", null, 1L, 1L, new HashSet<ReqLink>());
		reqs.add(request);
		Mockito.when(rs.getAllRequests()).thenReturn(reqs);
		ResultActions result = mvc.perform(get("/requests").contentType(MediaType.APPLICATION_JSON_VALUE));
		Set<JSONRequest> jsonReqs = new HashSet<JSONRequest>();
		jsonReqs.add(rc.requestToJSONRequest(request));
		result.andExpect(status().isOk());
	}

	/**
	 * This method tests { @link com.revature.controllers.RequestController#getAllRequest() getAllRequest() }
	 * This takes no argument and should return a set of Requests.
	 * This mocks RequestService.
	 * @throws Exception
	 */
	@Test
	public void testGetAllRequestsTestReturn() throws Exception {
		Set<Request> reqs = new HashSet<Request>();
		request = new Request(0, "test title", "code", "test description", null, 1L, 1L, new HashSet<ReqLink>());
		reqs.add(request);

		Mockito.when(rs.getAllRequests()).thenReturn(reqs);

		ResultActions result = mvc.perform(get("/requests").contentType(MediaType.APPLICATION_JSON_VALUE));

		Set<JSONRequest> ret = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				new TypeReference<Set<JSONRequest>>() {
				});

		Set<JSONRequest> jsonReqs = new HashSet<JSONRequest>();
		jsonReqs.add(rc.requestToJSONRequest(request));
		Assert.assertEquals(ret, jsonReqs, "Failed to retrieve expected requests");
	}

	/**
	 * This method tests { @link com.revature.controllers.RequestController#getRequestById(int) getRequestById(int) }
	 * This takes an ID and should return a Request.
	 * This tests if the response status is OK
	 * This mocks RequestService.
	 * @throws Exception
	 */
	@Test
	public void testGetRequestByIdStatusOk() throws Exception {
		request = new Request(0, "test title", "code", "test description", null, 1L, 1L, new HashSet<ReqLink>());
		Mockito.when(rs.getRequestsById(request.getId())).thenReturn(request);
		ResultActions result = mvc
				.perform(get("/requests/" + request.getId()).contentType(MediaType.APPLICATION_JSON_VALUE));
		result.andExpect(status().isOk());
	}

	/**
	 * This method tests { @link com.revature.controllers.RequestController#getRequestById(int) getRequestById(int) }
	 * This takes an ID and should return a Request.
	 * This mocks RequestService.
	 * @throws Exception
	 */
	@Test
	public void testGetRequestByIdTestReturn() throws Exception {
		request = new Request(0, "test title", "code", "test description", null, 1L, 1L, new HashSet<ReqLink>());
		Mockito.when(rs.getRequestsById(request.getId())).thenReturn(request);

		ResultActions result = mvc
				.perform(get("/requests/" + request.getId()).contentType(MediaType.APPLICATION_JSON_VALUE));

		JSONRequest actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				JSONRequest.class);
		Assert.assertEquals(actual, rc.requestToJSONRequest(request), "Failed to retrieve expected request");
	}

	/**
	 * This method tests { @link com.revature.controllers.RequestController#getReqLinksByRequestId(int) getReqLinksByRequestId(int) }
	 * This takes an ID and should return a set of ReqLinks.
	 * This tests if the response status is OK
	 * This mocks RequestService.
	 * @throws Exception
	 */
	@Test
	public void testGetReqLinksByRequestIdStatusOk() throws Exception {
		request = new Request(0, "test title", "code", "test description", null, 1L, 1L, new HashSet<ReqLink>());
		Module module = new Module(0, "module_subject", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		ReqLink reqLink = new ReqLink(0, request, module, "reqLink_affiliation");
		List<ReqLink> reqLinks = new ArrayList<ReqLink>();
		reqLinks.add(reqLink);

		Mockito.when(rs.getReqLinksByRequestId(request.getId())).thenReturn(reqLinks);

		ResultActions result = mvc.perform(
				get("/requests/" + request.getId() + "/req-links").contentType(MediaType.APPLICATION_JSON_VALUE));
		result.andExpect(status().isOk());
	}

	/**
	 * This method tests { @link com.revature.controllers.RequestController#getReqLinksByRequestId(int) getReqLinksByRequestId(int) }
	 * This takes an ID and should return a set of ReqLinks.
	 * This mocks RequestService.
	 * @throws Exception
	 */
	@Test
	public void testGetReqLinksByRequestIdTestReturn() throws Exception {
		request = new Request(0, "test title", "code", "test description", null, 1L, 1L, new HashSet<ReqLink>());
		Module module = new Module(0, "module_subject", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		ReqLink reqLink = new ReqLink(0, request, module, "reqLink_affiliation");
		List<ReqLink> reqLinks = new ArrayList<ReqLink>();
		reqLinks.add(reqLink);

		Mockito.when(rs.getReqLinksByRequestId(request.getId())).thenReturn(reqLinks);

		ResultActions result = mvc.perform(
				get("/requests/" + request.getId() + "/req-links").contentType(MediaType.APPLICATION_JSON_VALUE));

		List<ReqLink> actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				new TypeReference<List<ReqLink>>() {
				});

		Assert.assertEquals(actual, reqLinks, "Failed to retrieve expected request links");
	}

	private Set<JSONRequest> convertToJSONRequests(Set<Request> requests) {
		Set<JSONRequest> results = new HashSet<JSONRequest>();
		for (Request req : requests) {
			results.add(rc.requestToJSONRequest(req));
		}

		return results;
	}

	/**
	 * This method tests { @link com.revature.controllers.RequestController#getSearchResults(String, List, List) getSearchResults(String, List, List) }
	 * This takes a title, list of format, and list of module IDs, and should return a set of JSONRequests.
	 * This tests if the response status is OK
	 * This mocks RequestService.
	 * @throws Exception
	 */
	@Test
	public void testGetSearchResultsByTitleStatusOk() throws Exception {
		String title = "test title";
		String format = "";
		String modules = "";
		request = new Request(1, "test title", "code", "test description", null, 1L, 1L, new HashSet<ReqLink>());
		Request request2 = new Request(2, "test title", "document", "test description", null, 1L, 1L,
				new HashSet<ReqLink>());
		Set<Request> matches = new HashSet<Request>();
		matches.add(request);
		matches.add(request2);

		ArrayList<Integer> modList = new ArrayList<Integer>();
		ArrayList<String> formatList = new ArrayList<String>();

		Mockito.when(ss.filterReq(title, formatList, modList)).thenReturn(matches);

		ResultActions result = mvc.perform(get("/requests").contentType(MediaType.APPLICATION_JSON_VALUE)
				.param("title", title).param("format", format).param("modules", modules));

		result.andExpect(status().isOk());
	}
	
	/**
	 * This method tests { @link com.revature.controllers.RequestController#getSearchResults(String, List, List) getSearchResults(String, List, List) }
	 * This takes a title, list of format, and list of module IDs, and should return a set of JSONRequests.
	 * This tests the search by title.
	 * This mocks RequestService.
	 * @throws Exception
	 */
	@Test
	public void testGetSearchResultsByTitle() throws Exception {
		String title = "test title";
		String format = "";
		String modules = "";
		request = new Request(1, "test title", "code", "test description", null, 1L, 1L, new HashSet<ReqLink>());
		Request request2 = new Request(2, "test title", "document", "test description", null, 1L, 1L,
				new HashSet<ReqLink>());
		Set<Request> matches = new HashSet<Request>();
		matches.add(request);
		matches.add(request2);

		ArrayList<Integer> modList = new ArrayList<Integer>();
		ArrayList<String> formatList = new ArrayList<String>();

		Mockito.when(ss.filterReq(title, formatList, modList)).thenReturn(matches);

		ResultActions result = mvc.perform(get("/requests").contentType(MediaType.APPLICATION_JSON_VALUE)
				.param("title", title).param("format", format).param("modules", modules));

		Set<JSONRequest> actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				new TypeReference<Set<JSONRequest>>() {
				});

		Mockito.verify(ss).filterReq(title, formatList, modList);
		Assert.assertEquals(actual, convertToJSONRequests(matches), "Failed to retrieve expected search");
	}

	/**
	 * This method tests { @link com.revature.controllers.RequestController#getSearchResults(String, List, List) getSearchResults(String, List, List) }
	 * This takes a title, list of format, and list of module IDs, and should return a set of JSONRequests.
	 * This tests of the status is OK
	 * This mocks RequestService.
	 * @throws Exception
	 */
	@Test
	public void testGetSearchResultsByModulesStatusO() throws Exception {
		String title = "";
		String format = "";
		String modules = "1,2";
		request = new Request(1, "test title 1", "code", "a description", null, 1L, 1L, new HashSet<ReqLink>());
		Request request2 = new Request(2, "test title 2", "document", "another description", null, 1L, 1L,
				new HashSet<ReqLink>());
		Set<Request> matches = new HashSet<Request>();
		matches.add(request);
		matches.add(request2);

		ArrayList<Integer> modList = new ArrayList<Integer>();
		ArrayList<String> formatList = new ArrayList<String>();
		modList.add(1);
		modList.add(2);

		Mockito.when(ss.filterReq(title, formatList, modList)).thenReturn(matches);

		ResultActions result = mvc.perform(get("/requests").contentType(MediaType.APPLICATION_JSON_VALUE)
				.param("title", title).param("format", format).param("modules", modules));
		result.andExpect(status().isOk());
	}
	
	/**
	 * This method tests { @link com.revature.controllers.RequestController#getSearchResults(String, List, List) getSearchResults(String, List, List) }
	 * This takes a title, list of format, and list of module IDs, and should return a set of JSONRequests.
	 * This tests the search by Module IDs
	 * This mocks RequestService.
	 * @throws Exception
	 */
	@Test
	public void testGetSearchResultsByModulesTestReturn() throws Exception {
		String title = "";
		String format = "";
		String modules = "1,2";
		request = new Request(1, "test title 1", "code", "a description", null, 1L, 1L, new HashSet<ReqLink>());
		Request request2 = new Request(2, "test title 2", "document", "another description", null, 1L, 1L,
				new HashSet<ReqLink>());
		Set<Request> matches = new HashSet<Request>();
		matches.add(request);
		matches.add(request2);

		ArrayList<Integer> modList = new ArrayList<Integer>();
		modList.add(1);
		modList.add(2);
		
		ArrayList<String> formatList = new ArrayList<String>();

		Mockito.when(ss.filterReq(title, formatList, modList)).thenReturn(matches);

		ResultActions result = mvc.perform(get("/requests").contentType(MediaType.APPLICATION_JSON_VALUE)
				.param("title", title).param("format", format).param("modules", modules));

		Set<JSONRequest> actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				new TypeReference<Set<JSONRequest>>() {
				});

		Mockito.verify(ss).filterReq(title, formatList, modList);
		Assert.assertEquals(actual, convertToJSONRequests(matches), "Failed to retrieve expected search");
	}

	/**
	 * This method tests { @link com.revature.controllers.RequestController#updateRequest(Integer, Request) updateRequest(Integer, Request) }
	 * This takes an ID and a Request and should return the updated Request
	 * This mocks RequestService.
	 * @throws Exception
	 */
	@Test
	public void testUpdateRequest() throws Exception {
		request = new Request(0, "test title", "code", "test description", null, 1L, 1L, new HashSet<ReqLink>());
		Mockito.when(rs.getRequestsById(request.getId())).thenReturn(request);
		Mockito.when(rs.updateRequest(request)).thenReturn(request);

		ResultActions result = mvc.perform(put("/requests/" + request.getId())
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objMapper.writeValueAsString(request)));

		Request actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), Request.class);
		Assert.assertEquals(actual, request, "Failed to update request");
	}

	/**
	 * This method tests { @link com.revature.controllers.RequestController#updateRequest(Integer, Request) updateRequest(Integer, Request) }
	 * This takes an ID and a Request.
	 * This tests if the given ID is null and should return a 405 status.
	 * This mocks RequestService.
	 * @throws Exception
	 */
	@Test
	public void testUpdateRequestWithInvalidId() throws Exception {
		request = new Request(0, "test title", "code", "test description", null, 1L, 1L, new HashSet<ReqLink>());
		Mockito.when(rs.getRequestsById(request.getId())).thenReturn(null);

		ResultActions result = mvc.perform(put("/requests/" + request.getId())
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objMapper.writeValueAsString(request)));

		result.andExpect(status().is(405));
	}

	/**
	 * This method tests { @link com.revature.controllers.RequestController#updateReqLinks(int, List) updateReqLinks(int, List) }
	 * This takes an ID and a set of ReqLinks, and should return the updated set of ReqLinks.
	 * This tests if the status is OK.
	 * This mocks RequestService.
	 * @throws Exception
	 */
	@Test
	public void testUpdateReqLinksStatusOk() throws Exception {
		request = new Request(0, "test title", "code", "test description", null, 1L, 1L, new HashSet<ReqLink>());
		Module module = new Module(0, "module_subject", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		ReqLink reqLink = new ReqLink(0, request, module, "reqLink_affiliation");
		List<ReqLink> reqLinks = new ArrayList<ReqLink>();
		reqLinks.add(reqLink);

		Mockito.when(rs.updateReqLinks(request.getId(), reqLinks)).thenReturn(reqLinks);

		ResultActions result = mvc.perform(put("/requests/" + request.getId() + "/links")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objMapper.writeValueAsString(reqLinks)));
		result.andExpect(status().isOk());
	}
	
	/**
	 * This method tests { @link com.revature.controllers.RequestController#updateReqLinks(int, List) updateReqLinks(int, List) }
	 * This takes an ID and a set of ReqLinks, and should return the updated set of ReqLinks.
	 * This mocks RequestService.
	 * @throws Exception
	 */
	@Test
	public void testUpdateReqLinksTestReturn() throws Exception {
		request = new Request(0, "test title", "code", "test description", null, 1L, 1L, new HashSet<ReqLink>());
		Module module = new Module(0, "module_subject", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		ReqLink reqLink = new ReqLink(0, request, module, "reqLink_affiliation");
		List<ReqLink> reqLinks = new ArrayList<ReqLink>();
		reqLinks.add(reqLink);

		Mockito.when(rs.updateReqLinks(request.getId(), reqLinks)).thenReturn(reqLinks);

		ResultActions result = mvc.perform(put("/requests/" + request.getId() + "/links")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objMapper.writeValueAsString(reqLinks)));

		List<ReqLink> actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				new TypeReference<List<ReqLink>>() {
				});

		Assert.assertEquals(actual, reqLinks, "Failed to retrieve expected request links");
	}

	/**
	 * This method tests { @link com.revature.controllers.RequestController#deleteRequest(int) deleteRequest(int)  }
	 * This takes an ID and should delete the request.
	 * This mocks RequestService.
	 * @throws Exception
	 */
	@Test
	public void testDeleteRequest() throws Exception {
		request = new Request(0, "test title", "code", "test description", null, 1L, 1L, new HashSet<ReqLink>());
		Mockito.doNothing().when(rs).deleteRequest(request);
		Mockito.when(rs.getRequestsById(request.getId())).thenReturn(request);

		ResultActions result = mvc
				.perform(delete("/requests/" + request.getId()).contentType(MediaType.APPLICATION_JSON_VALUE));

		result.andExpect(status().isOk());
	}
}
