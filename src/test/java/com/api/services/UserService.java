package com.api.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constant.Roles;
import com.api.request.model.UserCredentials;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserService {

	private static final String USERDETAILS_ENDPOINT = "/userdetails";
	private static final Logger LOGGER = LogManager.getLogger(UserService.class);
	public Response getDetail(Roles role) {
		
		LOGGER.info("Making get detail to {} with role {}",USERDETAILS_ENDPOINT,role);	
		Response response = RestAssured
		.given()
			.spec(SpecUtil.requestSpec())
			.and()
			.spec(SpecUtil.requestSpecWithAuth(role))
			.and()
			
		.when()
			.get(USERDETAILS_ENDPOINT);
		
		return response;
	}
	
	
	
}
