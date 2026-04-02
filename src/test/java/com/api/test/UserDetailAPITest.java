package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailAPITest {

	@Test
	public void userDetailAPITest() {
		
		Header authHeader = new Header("Authorization", AuthTokenProvider.getToken(Roles.FD));
		RestAssured
		.given()
			.baseUri(ConfigManager2.getProperty("BASE_URI"))
			.and()
			.header(authHeader)
			.and()
			.accept(ContentType.JSON)
			.and()
			.log().uri()
			.log().method()
			.log().body()
			.log().headers()
		.when()
			.get("userdetails")
		.then()
			.statusCode(200)
			.time(Matchers.lessThan(5000L))
			.and()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/userDetailsResponseSchema.json"));
			
		
	}
	
	
}
