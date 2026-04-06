package com.api.services;

import java.util.Iterator;

import com.api.constant.Roles;
import com.api.request.model.CreateJobPOJO;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.Validatable;
import io.restassured.response.ValidatableResponse;

public class JobService {

	private static final String CREATE_JOB_ENDPOINT="/job/create";
	private static final String JOB_SEARCH_ENDPOINT="/job/search";
	
	public Response createJob(Roles role, CreateJobPOJO createJobPayload) {
		
		
		Response response =RestAssured
		.given()
			.spec(SpecUtil.requestSpecWithAuth(Roles.FD, createJobPayload))
			
			.when()
				.post(CREATE_JOB_ENDPOINT);
		
		return response;
	}
	
	

	
public Response searchJob(Roles role, Object createJobPayload) {
		
		
		Response response =RestAssured
		.given()
			.spec(SpecUtil.requestSpecWithAuth(Roles.FD, createJobPayload))
			
			.when()
				.post(JOB_SEARCH_ENDPOINT);
		
		return response;
	}



	
}
