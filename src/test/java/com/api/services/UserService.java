package com.api.services;

import com.api.constant.Roles;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserService {

	private static final String USERDETAILS_ENDPOINT = "/userdetails";
	
	public Response getDetail(Roles role) {
		
			
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
