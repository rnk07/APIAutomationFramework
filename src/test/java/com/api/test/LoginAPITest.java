package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
	@Test
	public void loginAPITest(){
		
		UserCredentials userCredentials = new UserCredentials("iamfd", "password");
		
	RestAssured
	.given()
		.baseUri("http://64.227.160.186:9000/v1")
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
