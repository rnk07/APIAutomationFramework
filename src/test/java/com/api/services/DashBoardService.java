package com.api.services;

import com.api.constant.Roles;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DashBoardService {
	
	private static final String COUNT_ENDPOINT = "/dashboard/count";
	private static final String DETAIL_ENDPOINT = "/dashboard/details";
	
	public Response getCount(Roles role) {
		
		Response response = RestAssured
		.given()
			.spec(SpecUtil.requestSpec())
			.spec(SpecUtil.requestSpecWithAuth(role))
			
		.when()
			.get(COUNT_ENDPOINT);
		
		return response;
	}
	
public Response getCountwithNoAuth() {
		
		Response response = RestAssured
		.given()
			.spec(SpecUtil.requestSpec())
		.when()
			.get(COUNT_ENDPOINT);
		
		return response;
	}

public Response details(Roles role, Object payload) {
	Response response = RestAssured
			.given()
				.spec(SpecUtil.requestSpecWithAuth(role))
				.body(payload)
			.when()
				.post(DETAIL_ENDPOINT);
			
			return response;
}



}
