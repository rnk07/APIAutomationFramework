package com.api.test;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.utils.ConfigManager2;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	private UserCredentials userCredentials;
	
	@BeforeMethod(description = "Creating payload for Login API.")
	public void setUp() {
		
		userCredentials = new UserCredentials("iamfd", "password");
	}

	
	
	@Test(description = "Verifying if login API is working for iamfd user", groups = {"smoke","sanity","apiRegression"})
	public void loginAPITest() throws IOException{
		

		
		RestAssured
			.given()
				.spec(SpecUtil.requestSpec(userCredentials))
			.when()
				.post("login")
			.then()
				.spec(SpecUtil.responseSpec_OK())
				.body("message", Matchers.matchesPattern("Success"))
				.and()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/loginAPIResponseSchema.json"));
	
		
	}
	
	

}
