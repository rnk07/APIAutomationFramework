package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager2;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class MasterAPITest {

	@Test(description = "Verify if Master API is working fine.",groups = {"smoke","sanity","apiRegression"})
	public void masterAPITest() {
		
		RestAssured
			.given()
				.spec(SpecUtil.requestSpec())
				.spec(SpecUtil.requestSpecWithAuth(Roles.FD))
			.when()
				.post("master")
			.then()
				.spec(SpecUtil.responseSpec_OK())
				.body("message", Matchers.matchesPattern("Success"))
				.body("data", Matchers.notNullValue())
				.body("$", Matchers.hasKey("message"))
				.body("$", Matchers.hasKey("data"))
				.body("data", Matchers.hasKey("mst_oem"))
				.body("data.mst_oem.size()",Matchers.equalTo(2))
				.body("data.mst_oem.id", Matchers.everyItem(Matchers.notNullValue()))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/masterAPIResponseSchema-FD.json"));
	}
	
	@Test(description = "Verify if Master API gives 401 error for invalid token.",groups = {"smoke","apiNegative","apiRegression"})
	public void invalidTokenMasterAPITest() {
		RestAssured
		.given()
			.spec(SpecUtil.requestSpec())
		.when()
			.post("master")
		.then()
			.spec(SpecUtil.responseSpec_TEXT(401));
	}
	
	
	
	
	
	
}
