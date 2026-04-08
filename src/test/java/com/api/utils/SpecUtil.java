package com.api.utils;

import org.hamcrest.Matchers;

import com.api.constant.Roles;
import com.api.filters.SensitiveDataFilter;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {
	// static method

	//GET-DELETE - Method- No Parameter
	@Step("Setting BasueURI, ContentType as Application/Json and attaching the senstive data filter. ")
	public static RequestSpecification requestSpec() {
		// To take care of common request sections(methods)

		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager2.getProperty("BASE_URI"))
				.setContentType(ContentType.JSON).setAccept(ContentType.JSON)
//				.log(LogDetail.URI)
//				.log(LogDetail.METHOD)
//				.log(LogDetail.HEADERS)
//				.log(LogDetail.BODY)
				.addFilter(new SensitiveDataFilter())
				.addFilter(new AllureRestAssured())
				.build();

		return requestSpecification;

	}
	
	//POST-PUT-PATCH -takes body
	@Step("Setting BasueURI, ContentType as Application/Json and attaching the senstive data filter. ")
	public static RequestSpecification requestSpec(Object userCreds) {
		// To take care of common request sections(methods)

		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager2.getProperty("BASE_URI"))
				.setContentType(ContentType.JSON).setAccept(ContentType.JSON)
				.setBody(userCreds)
				.addFilter(new SensitiveDataFilter())
				.addFilter(new AllureRestAssured())
				.build();

		return requestSpecification;

	}
	
	// POST with Auth Token
	@Step("Setting BasueURI, ContentType as Application/Json and attaching the senstive data filter for a role")
	public static RequestSpecification requestSpecWithAuth(Roles role) {
		
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager2.getProperty("BASE_URI"))
				.setContentType(ContentType.JSON).setAccept(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.getToken(role))
				.addFilter(new SensitiveDataFilter())
				.addFilter(new AllureRestAssured())
				.build();

		return requestSpecification;
		
		
	}
	
	@Step("Setting BasueURI, ContentType as Application/Json and attaching the senstive data filter and attaching payload")	
public static RequestSpecification requestSpecWithAuth(Roles role, Object payload) {
		
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager2.getProperty("BASE_URI"))
				.setContentType(ContentType.JSON).setAccept(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.getToken(role))
				.setBody(payload)
				.addFilter(new SensitiveDataFilter())
				.addFilter(new AllureRestAssured())
				.build();

		return requestSpecification;
		
		
	}
	
	
	
	//Response Specification
	@Step("Validating Status code, response time and content type.")
	public static ResponseSpecification responseSpec_OK() {
		
		ResponseSpecification responseSpecification= new ResponseSpecBuilder()
			.expectContentType(ContentType.JSON)
			.expectStatusCode(200)
			.expectResponseTime(Matchers.lessThan(5000L))
//			.log(LogDetail.ALL)
			.build();
		return responseSpecification;
		
		
	}
	
	@Step("Validating Status code, response time and content type.")
public static ResponseSpecification responseSpec_JSON(int statusCode) {
		
		ResponseSpecification responseSpecification= new ResponseSpecBuilder()
			.expectContentType(ContentType.JSON)
			.expectStatusCode(statusCode)
			.expectResponseTime(Matchers.lessThan(5000L))
			.build();
		return responseSpecification;
		
		
	}
	
	@Step("Validating Status code, response time and content type.")
public static ResponseSpecification responseSpec_TEXT(int statusCode) {
	
	ResponseSpecification responseSpecification= new ResponseSpecBuilder()
		
		.expectStatusCode(statusCode)
		.expectResponseTime(Matchers.lessThan(5000L))
		.build();
	return responseSpecification;
	
	
}

	
	
	

}
