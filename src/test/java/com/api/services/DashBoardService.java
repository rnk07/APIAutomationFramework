package com.api.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constant.Roles;
import com.api.utils.SpecUtil;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DashBoardService { 
	
	private static final String COUNT_ENDPOINT = "/dashboard/count";
	private static final String DETAIL_ENDPOINT = "/dashboard/details";
	private static final Logger LOGGER = LogManager.getLogger(DashBoardService.class);
	
	@Step("Making count API Request for role.")
	public Response getCount(Roles role) {
		LOGGER.info("Making request to the {} for the role {}", COUNT_ENDPOINT,role);
		
		Response response = RestAssured
		.given()
			.spec(SpecUtil.requestSpec())
			.spec(SpecUtil.requestSpecWithAuth(role))
			
		.when()
			.get(COUNT_ENDPOINT);
		
		return response;
	}
	
	@Step("Making count API Request without auth token.")	
public Response getCountwithNoAuth() {
	LOGGER.info("Making request to the {} with no auth token", COUNT_ENDPOINT);	
	
		Response response = RestAssured
		.given()
			.spec(SpecUtil.requestSpec())
		.when()
			.get(COUNT_ENDPOINT);
		
		return response;
	}
	
	@Step("Making Details API Request")
public Response details(Roles role, Object payload) {
	LOGGER.info("Making request to the {} with role{} and the payload {}", DETAIL_ENDPOINT,role,payload);
	
	Response response = RestAssured
			.given()
				.spec(SpecUtil.requestSpecWithAuth(role))
				.body(payload)
			.when()
				.post(DETAIL_ENDPOINT);
			
			return response;
}



}
