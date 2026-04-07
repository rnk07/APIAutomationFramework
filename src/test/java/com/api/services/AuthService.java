package com.api.services;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.request.model.UserCredentials;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AuthService {
	//It will hold APIs which belong to Auth
	
	private static final Logger LOGGER = LogManager.getLogger(AuthService.class);
	
	private static final String LOGIN_ENDPOINT = "/login";
	
	public Response login(Object userCredentials) {
		
		LOGGER.info("Making login request for the payload {}",((UserCredentials)userCredentials).username());		
		Response response =RestAssured
			.given()
				.spec(SpecUtil.requestSpec(userCredentials))
			.when()
				.post(LOGIN_ENDPOINT);
		
		return response;
	}
	

}
