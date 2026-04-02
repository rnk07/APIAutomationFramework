package com.api.test;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import com.api.utils.ConfigManager2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
	@Test
	public void loginAPITest() throws IOException{
		
		UserCredentials userCredentials = new UserCredentials("iamfd", "password");

		
	RestAssured
	.given()
		.baseUri(ConfigManager2.getProperty("BASE_URI")) //Use of utils method
		.and()
		.contentType(ContentType.JSON)
		.and()
		.accept(ContentType.JSON)
		.and()
		.body(userCredentials)
		.log().uri()
		.log().method()
		.log().headers()
		.log().body()
	.when()
		.post("login")
	.then()
//		.log().all()
		.statusCode(200)
		.time(Matchers.lessThan(5000L))
		.log().body()
		.and()
		.body("message", Matchers.matchesPattern("Success"))
		.and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/loginAPIResponseSchema.json"));
	
		
		
		
	
		
		
		
	}
	
	

}
