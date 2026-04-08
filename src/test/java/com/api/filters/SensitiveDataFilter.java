package com.api.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class SensitiveDataFilter implements Filter 	 {

	
	private static final Logger LOGGER = LogManager.getLogger(SensitiveDataFilter.class); 
	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		
		
		System.out.println("---------Hello from Filter------------");
		redactRequestPayload(requestSpec);
		Response response = ctx.next(requestSpec, responseSpec);
		System.out.println("---------I got the response Filter------------");
		redactResponsePayload(response);
		return response;
	}
	
	
	//Create a method which Hide/Redact password from the response payload
	
	
		public void redactRequestPayload(FilterableRequestSpecification requestSpec) {
			String requestPayload =requestSpec.getBody().toString();
			requestPayload =requestPayload.replaceAll("\"password\"\s*:\s*\"[^\"]+\"", "\"password\":\"[REDACTED]\"");
			LOGGER.info("REQUEST PAYLOAD: {}",requestPayload);
		}
	
		public void redactResponsePayload(Response response) {
			String responseBody =response.asPrettyString();
			responseBody =responseBody.replaceAll("\"token\"\s*:\s*\"[^\"]+\"", "\"token\":\"[REDACTED]\"");
			LOGGER.info("RESPONSE BOODY: {}",responseBody);
		}

}
