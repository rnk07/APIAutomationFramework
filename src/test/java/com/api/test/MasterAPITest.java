package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class MasterAPITest {

	@Test
	public void masterAPITest() {
		
		RestAssured
			.given()
				.baseUri(ConfigManager2.getProperty("BASE_URI"))
				.and()
				.contentType(ContentType.TEXT)
				.and()
				.header("Authorization",AuthTokenProvider.getToken(Roles.FD))
				.and()
				.log().uri()
				.log().method()
				.log().headers()
			.when()
				.post("master")
			.then()
				.log().all()
				.statusCode(200)
				.time(Matchers.lessThan(4000L))
				.body("message", Matchers.matchesPattern("Success"))
				.body("data", Matchers.notNullValue())
				.body("$", Matchers.hasKey("message"))
				.body("$", Matchers.hasKey("data"))
				.body("data", Matchers.hasKey("mst_oem"))
				.body("data.mst_oem.size()",Matchers.equalTo(2))
				.body("data.mst_oem.id", Matchers.everyItem(Matchers.notNullValue()))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/masterAPIResponseSchema-FD.json"));
	}
	
	@Test
	public void invalidTokenMasterAPITest() {
		RestAssured
		.given()
			.baseUri(ConfigManager2.getProperty("BASE_URI"))
			.and()
			.contentType(ContentType.TEXT)
			.and()
			.header("Authorization","")
			.and()
			.log().uri()
			.log().method()
			.log().headers()
		.when()
			.post("master")
		.then()
			.log().all()
			.statusCode(401);
	}
	
	
	
	
	
	
}
