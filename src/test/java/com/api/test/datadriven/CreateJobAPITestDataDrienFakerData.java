package com.api.test.datadriven;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.request.model.CreateJobPOJO;
import com.api.services.JobService;
import com.api.utils.FakerDataGenerator;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;


public class CreateJobAPITestDataDrienFakerData {
	
	
	private CreateJobPOJO createJobPayload;
	private JobService jobService;
	
	@BeforeMethod(description = "Creating Creating create job payload and initilizing Create job service.")
	public void setUp() {
		
		//Create Fake CreateJOB API Payload
		createJobPayload =FakerDataGenerator.generateFakeCreateJobData();
		jobService  = new JobService();
		
	}

	@Test(description = "Verify if Create Inwarranty Job API is working fine.",groups = {"somke","sanity","apiRegression"})
	public void createJobAPITest() {
		
		//Creating CreateJobPOJO object
		
		jobService.createJob(Roles.FD, createJobPayload)
				.then()
				.spec(SpecUtil.responseSpec_OK())
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/createJobResponseSchema.json"))
				.body("message", Matchers.matchesPattern("Job created successfully. "))
				.body("data.mst_service_location_id", Matchers.equalTo(1))
				.body("data.job_number", Matchers.startsWith("JOB_"))
				;
			
	}

}