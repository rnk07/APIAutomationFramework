package com.api.utils;

import org.hamcrest.Matchers;

import com.api.constant.Roles;
import com.api.pojo.UserCredentials;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class AuthTokenProvider {
	
	private AuthTokenProvider() {
		
	}

	public static String getToken(Roles role) {
		
		//Make req for login api and extract token 
		//print on console
		UserCredentials userCredentials = null;

		if (role == Roles.FD) {
			
			userCredentials = new UserCredentials("iamfd", "password");
		
		} else if (role == Roles.SUP) {
			
			userCredentials = new UserCredentials("iamsup", "password");
		
		} else if (role == Roles.ENG) {
			
			userCredentials = new UserCredentials("iameng", "password");
		
		} else if (role == Roles.QC) {
			
			userCredentials = new UserCredentials("iamqc", "password");
		
		}
		
		
		String token =RestAssured
			.given()
				.baseUri(ConfigManager2.getProperty("BASE_URI"))
				.contentType(ContentType.JSON)
				.body(userCredentials)
			.when()
				.post("login")
			.then()
				.log().ifValidationFails()
				.statusCode(200)
				.body("message", Matchers.matchesPattern("Success"))
				.extract()
				.body()
				.jsonPath().getString("data.token");
		
		
		return token;
		
		
		

	}

}
