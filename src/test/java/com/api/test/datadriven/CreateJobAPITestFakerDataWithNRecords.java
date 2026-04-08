package com.api.test.datadriven;

import org.hamcrest.Matchers;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.request.model.CreateJobPOJO;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

@Listeners(com.listeners.APITestListener.class)
public class CreateJobAPITestFakerDataWithNRecords {

	

	
	@Test(description = "Verifying if Create job API generating N number of job",
			groups = {"smoke","sanity","datadriven"},
			dataProviderClass = com.dataProviders.DataProviderUtils.class,
			dataProvider  ="createJobAPIFakerDataProvider")
	
	public void createJobAPITest(CreateJobPOJO createJobPayload) {

		//Creating CreateJobPOJO object

		RestAssured
		.given()
			.spec(SpecUtil.requestSpecWithAuth(Roles.FD,createJobPayload))
			.when()
			.post("/job/create")
				.then()
				.spec(SpecUtil.responseSpec_OK())
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/createJobResponseSchema.json"))
				.body("message", Matchers.matchesPattern("Job created successfully. "))
				.body("data.mst_service_location_id", Matchers.equalTo(1))
				.body("data.job_number", Matchers.startsWith("JOB_"));
	
	}
	
	
	
	
}
