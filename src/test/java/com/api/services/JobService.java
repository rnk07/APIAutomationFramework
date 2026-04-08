package com.api.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constant.Roles;
import com.api.request.model.CreateJobPOJO;
import com.api.request.model.UserCredentials;
import com.api.utils.SpecUtil;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JobService {

	private static final String CREATE_JOB_ENDPOINT="/job/create";
	private static final String JOB_SEARCH_ENDPOINT="/job/search";
	private static final Logger LOGGER = LogManager.getLogger(JobService.class);
	
	@Step("Creating Job with Create job API")
	public Response createJob(Roles role, CreateJobPOJO createJobPayload) {
		
		LOGGER.info("Making request to {} with role {} and payload {}",CREATE_JOB_ENDPOINT,role, createJobPayload);	
		Response response =RestAssured
		.given()
			.spec(SpecUtil.requestSpecWithAuth(Roles.FD, createJobPayload))
			
			.when()
				.post(CREATE_JOB_ENDPOINT);
		
		return response;
	}
	
	

@Step("Making Search API Request.")
public Response searchJob(Roles role, Object createJobPayload) {
		
	LOGGER.info("Job search request to {} with role {} and payload {}",JOB_SEARCH_ENDPOINT,role, createJobPayload);	
		Response response =RestAssured
		.given()
			.spec(SpecUtil.requestSpecWithAuth(Roles.FD, createJobPayload))
			
			.when()
				.post(JOB_SEARCH_ENDPOINT);
		
		return response;
	}



	
}
