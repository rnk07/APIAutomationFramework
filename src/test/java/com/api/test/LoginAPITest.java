package com.api.test;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import com.api.utils.ConfigManager2;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
	@Test
	public void loginAPITest() throws IOException{
		
	UserCredentials userCredentials = new UserCredentials("iamfd", "password");

		
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
