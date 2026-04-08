package com.api.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constant.Roles;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class MasterService {

	private static final String MASTE_ENDPOINT= "master";
	private static final Logger LOGGER = LogManager.getLogger(MasterService.class);
	
	
	public Response master(Roles role) {
	
		LOGGER.info("Making master request to {} with role {}.",MASTE_ENDPOINT,role);
		Response response =RestAssured
		.given()
			.spec(SpecUtil.requestSpecWithAuth(role))
		.when()
			.post(MASTE_ENDPOINT);
		return response;
	}
	public Response masterWithNoAuth() {
		
		LOGGER.info("Making master request to {} with no auth.",MASTE_ENDPOINT);
		Response response =RestAssured
		.given()
			.spec(SpecUtil.requestSpec())
		.when()
			.post(MASTE_ENDPOINT);
		return response;
	}
}
