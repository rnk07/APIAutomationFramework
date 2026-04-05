package com.api.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.management.relation.Role;

import org.hamcrest.Matchers;

import com.api.constant.Roles;
import com.api.request.model.UserCredentials;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class AuthTokenProvider {
	
	private AuthTokenProvider() {
		
	}

	private static Map<Roles,String> tokenCache = new ConcurrentHashMap();

	
	public static String getToken(Roles role) {
		
		if(tokenCache.containsKey(role)) {
			
			return tokenCache.get(role);
		}
		
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
		
		tokenCache.put(role, token);
		return token;
		
		
		

	}

}
