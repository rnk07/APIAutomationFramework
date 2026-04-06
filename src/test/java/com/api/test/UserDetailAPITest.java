package com.api.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.services.UserService;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailAPITest {
	private UserService userService;

	
	@BeforeMethod(description = "Initilizing User Service.")
	public void setUp() {

		userService = new UserService();
	}
	
	@Test(description = "Verify if User Detail API is working fine.",groups = {"somke","sanity","apiRegression"})
	 public void userDetailAPITest() {
		
		
		userService.getDetail(Roles.FD)
		.then()
			.spec(SpecUtil.responseSpec_OK())
			.and()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/userDetailsResponseSchema.json"));
			
		
	}
	
	
}
