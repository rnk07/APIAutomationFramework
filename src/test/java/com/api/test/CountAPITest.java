package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager2;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CountAPITest {

	@Test
	public void verfyCountAPIResonse() {
		
		RestAssured
			.given()
				.baseUri(ConfigManager2.getProperty("BASE_URI"))
				.and()
				.header("Authorization",AuthTokenProvider.getToken(Roles.FD))
				.log().uri()
				.log().method()
				.log().headers()
			.when()
				.get("/dashboard/count")
			.then()
				.log().all()
				.statusCode(200)
				.body("message", Matchers.matchesPattern("Success"))
				.time(Matchers.lessThan(4000L))
				.body("data", Matchers.notNullValue())
				.body("data.size()",Matchers.equalTo(3))
				.body("data.count", Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
				.body("data.label", Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/countAPIResponseSchema-FD.json"))
				.body("data.key", Matchers.containsInAnyOrder("pending_for_delivery","pending_fst_assignment","created_today"));

	}
	
	@Test
	public void countAPITest_MissingAuthToken() {
		RestAssured
		.given()
			.baseUri(ConfigManager2.getProperty("BASE_URI"))
			.and()
			.log().uri()
			.log().method()
			.log().headers()
		.when()
			.get("/dashboard/count")
		.then()
			.log().all()
			.statusCode(401);
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
