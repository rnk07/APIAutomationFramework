package com.api.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.services.UserService;
import com.api.utils.SpecUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.module.jsv.JsonSchemaValidator;

@Listeners(com.listeners.APITestListener.class)
@Epic("User Management")
@Feature("Getting User Details")
public class UserDetailAPITest {
	private UserService userService;

	
	@BeforeMethod(description = "Initilizing User Service.")
	public void setUp() {

		userService = new UserService();
	}
	
	@Story("User Details Should be Displayed")
	@Description("Verify if user details displayed via api")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "Verify if User Detail API is working fine.",groups = {"somke","sanity","apiRegression"})
	 public void userDetailAPITest() {
		
		
		userService.getDetail(Roles.FD)
		.then()
			.spec(SpecUtil.responseSpec_OK())
			.and()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/userDetailsResponseSchema.json"));
			
		
	}
	
	
}
