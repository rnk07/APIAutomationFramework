package com.api.services;

import com.api.constant.Roles;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DashBoardService {
	
	private static final String COUNT_ENDPOINT = "/dashboard/count";
	
	public Response getCount(Roles role) {
		
		Response response = RestAssured
		.given()
			.spec(SpecUtil.requestSpec())
			.spec(SpecUtil.requestSpecWithAuth(role))
			
		.when()
			.get("/dashboard/count");
		
		return response;
	}
	
public Response getCountwithNoAuth() {
		
		Response response = RestAssured
		.given()
			.spec(SpecUtil.requestSpec())
		.when()
			.get("/dashboard/count");
		
		return response;
	}

}
