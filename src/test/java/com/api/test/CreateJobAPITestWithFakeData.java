package com.api.test;

import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.request.model.CreateJobPOJO;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.FakerDataGenerator;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITestWithFakeData {
	
	
	private CreateJobPOJO createJobPayload;
	
	@BeforeMethod
	public void setUp() {
		
		//Create Fake CreateJOB API Payload
		createJobPayload =FakerDataGenerator.generateFakeCreateJobData();
			
		
	}

	@Test(description = "Verify if Create Inwarranty Job API is working fine.",groups = {"somke","sanity","apiRegression"})
	public void createJobAPITest() {
		
		//Creating CreateJobPOJO object
		
		RestAssured
			.given()
				.spec(SpecUtil.requestSpecWithAuth(Roles.FD, createJobPayload))
		
				.when()
					.post("/job/create")
				.then()
				.spec(SpecUtil.responseSpec_OK())
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/createJobResponseSchema.json"))
				.body("message", Matchers.matchesPattern("Job created successfully. "))
				.body("data.mst_service_location_id", Matchers.equalTo(1))
				.body("data.job_number", Matchers.startsWith("JOB_"))
				;
			
	}

}
