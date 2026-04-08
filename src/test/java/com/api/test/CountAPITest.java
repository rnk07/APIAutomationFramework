package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.services.DashBoardService;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;


@Listeners(com.listeners.APITestListener.class)
public class CountAPITest {
	private DashBoardService  dashboardService; 
	
	
	@BeforeMethod(description = "Initilizing Dashboard Service")
	public void setUp() {
		
		  dashboardService = new DashBoardService();
	}
	
	
	@Test(description = "Verify if Count Job API is working fine.",groups = {"somke","sanity","apiRegression"})
	public void verfyCountAPIResonse() {
		
		
		
		dashboardService.getCount(Roles.FD)
			.then()
				.spec(SpecUtil.responseSpec_OK())
				.body("message", Matchers.matchesPattern("Success"))
				.body("data", Matchers.notNullValue())
				.body("data.size()",Matchers.equalTo(3))
				.body("data.count", Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
				.body("data.label", Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/countAPIResponseSchema-FD.json"))
				.body("data.key", Matchers.containsInAnyOrder("pending_for_delivery","pending_fst_assignment","created_today"));

	}
	
	@Test(description = "Verify if Create Job API is givine 401 error for invalid token.",groups = {"somke","apiNegative","apiRegression"})
	public void countAPITest_MissingAuthToken() {
		dashboardService.getCountwithNoAuth()
		.then()
			.spec(SpecUtil.responseSpec_TEXT(401));
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
