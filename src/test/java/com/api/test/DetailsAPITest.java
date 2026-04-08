package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.request.model.Details;
import com.api.services.DashBoardService;
import com.api.utils.SpecUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

@Listeners(com.listeners.APITestListener.class)
@Epic("Job Management")
@Feature("Job Details Feature")
public class DetailsAPITest {

	private DashBoardService dashBoardService;
	private Details detailsPayload;
	
	@BeforeMethod(description = "Initlizing Dashboard service and creating detail payload.")
	public void setUp()
	{
		dashBoardService = new DashBoardService();
		detailsPayload = new Details("created_today");
		
		
	}
	
	@Story("Job Details Should display valid Details.")
	@Description("Job details should be display fine via API.")
	@Test(description = "Verfiy if Details api working.",groups = {"smoke","sanity"})
	public void detailsAPITest() {
		
		dashBoardService.details(Roles.FD, detailsPayload)
			.then()
			.spec(SpecUtil.responseSpec_TEXT(200))
			.body("message",Matchers.equalTo("Success"));
			
		
	}
	
	
	
	
}
