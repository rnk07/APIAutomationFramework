package com.api.services;

import com.api.constant.Roles;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class MasterService {

	private static final String MASTE_ENDPOINT= "master";
	
	public Response master(Roles role) {
		Response response =RestAssured
		.given()
			.spec(SpecUtil.requestSpec())
			.spec(SpecUtil.requestSpecWithAuth(role))
		.when()
			.post(MASTE_ENDPOINT);
		return response;
	}
	public Response masterWithNoAuth() {
		Response response =RestAssured
		.given()
			.spec(SpecUtil.requestSpec())
		.when()
			.post(MASTE_ENDPOINT);
		return response;
	}
}
