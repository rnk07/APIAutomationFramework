package com.api.services;

import com.api.request.model.UserCredentials;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AuthService {
	//It will hold APIs which belong to Auth
	
	private static final String LOGIN_ENDPOINT = "login";
	
	public Response login(UserCredentials userCredentials) {
		

		Response response =RestAssured
			.given()
				.spec(SpecUtil.requestSpec(userCredentials))
			.when()
				.post(LOGIN_ENDPOINT);
		
		return response;
	}
	

}
