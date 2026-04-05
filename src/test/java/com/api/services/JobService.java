package com.api.services;

import com.api.constant.Roles;
import com.api.request.model.CreateJobPOJO;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JobService {

	private static final String CREATE_JOB_ENDPOINT="/job/create";
	
	public Response createJob(Roles role, CreateJobPOJO createJobPayload) {
		
		
		Response response =RestAssured
		.given()
			.spec(SpecUtil.requestSpecWithAuth(Roles.FD, createJobPayload))
			
			.when()
				.post(CREATE_JOB_ENDPOINT);
		
		return response;
	}
	
	
}
