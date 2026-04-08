package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.request.model.Details;
import com.api.services.DashBoardService;
import com.api.utils.SpecUtil;

@Listeners(com.listeners.APITestListener.class)
public class DetailsAPITest {

	private DashBoardService dashBoardService;
	private Details detailsPayload;
	
	@BeforeMethod(description = "Initlizing Dashboard service and creating detail payload.")
	public void setUp()
	{
		dashBoardService = new DashBoardService();
		detailsPayload = new Details("created_today");
		
		
	}
	
	@Test(description = "Verfiy if Details api working.",groups = {"smoke","sanity"})
	public void detailsAPITest() {
		
		dashBoardService.details(Roles.FD, detailsPayload)
			.then()
			.spec(SpecUtil.responseSpec_TEXT(200))
			.body("message",Matchers.equalTo("Success"));
			
		
	}
	
	
	
	
}
