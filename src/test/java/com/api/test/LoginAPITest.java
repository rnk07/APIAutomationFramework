package com.api.test;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.services.AuthService;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

@Listeners(com.listeners.APITestListener.class)
public class LoginAPITest {
	private UserCredentials userCredentials;
	
	private AuthService authService;
	
	@BeforeMethod(description = "Creating payload for Login API.")
	public void setUp() {
		
		userCredentials = new UserCredentials("iamfd", "password");
		authService = new AuthService();
	}

	
	
	@Test(description = "Verifying if login API is working for iamfd user", groups = {"smoke","sanity","apiRegression"})
	public void loginAPITest() throws IOException{
		
		Response response = authService.login(userCredentials);
		
		response
			.then()
				.spec(SpecUtil.responseSpec_OK())
				.body("message", Matchers.matchesPattern("Success"))
				.and()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/loginAPIResponseSchema.json"));

	}
	
	
	
	
	

}
