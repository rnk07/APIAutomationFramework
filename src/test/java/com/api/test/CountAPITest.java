package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager2;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CountAPITest {

	@Test(description = "Verify if Count Job API is working fine.",groups = {"somke","sanity","apiRegression"})
	public void verfyCountAPIResonse() {
		
		RestAssured
			.given()
				.spec(SpecUtil.requestSpec())
				.spec(SpecUtil.requestSpecWithAuth(Roles.FD))
				
			.when()
				.get("/dashboard/count")
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
		RestAssured
		.given()
			.spec(SpecUtil.requestSpec())
		.when()
			.get("/dashboard/count")
		.then()
			.spec(SpecUtil.responseSpec_TEXT(401));
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
