package com.api.test;

import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailAPITest {

	@Test
	public void userDetailAPITest() {
		
		
		RestAssured
		.given()
			.spec(SpecUtil.requestSpec())
			.and()
			.spec(SpecUtil.requestSpecWithAuth(Roles.FD))
			.and()
			
		.when()
			.get("userdetails")
		.then()
			.spec(SpecUtil.responseSpec_OK())
			.and()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/userDetailsResponseSchema.json"));
			
		
	}
	
	
}
