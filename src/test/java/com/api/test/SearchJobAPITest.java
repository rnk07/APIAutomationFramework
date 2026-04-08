package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.request.model.SearchJob;
import com.api.services.JobService;
import com.api.utils.SpecUtil;

@Listeners(com.listeners.APITestListener.class)
public class SearchJobAPITest {

	private JobService jobService;
	private String jobNumber="JOB_239832";
	private SearchJob searchPayload;
	
	@BeforeMethod(description = "Initilizing Job Service and job search payload creation.")
	public void setUp() {
		jobService = new JobService();
		searchPayload = new SearchJob(jobNumber);
		
	}
	
	@Test(description = "Verify if job search api is working fine or not.",groups= {"smoke","sanity"})
	public void jobSearchAPITest() {
		
		
				jobService.searchJob(Roles.FD, searchPayload)
				.then()
				.spec(SpecUtil.responseSpec_OK())
				.body("message", Matchers.matchesPattern("Success"));
		
		
	}
	
	
	
}
