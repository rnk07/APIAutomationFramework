package com.api.test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.request.model.CreateJobPOJO;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.DateTimeUtil;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {

	@Test
	public void createJobAPITest() {
		
		//Creating CreateJobPOJO object
		
		
		Customer customer = new Customer("Ronak", "Test", "123456789", "", "ronak@test.com", "");
		CustomerAddress customerAddress = new CustomerAddress("5 ", "John Street", "Gandhi Bag", "Inrobit", "Mumbai", "395005","India","Pune");
		CustomerProduct customerProduct = new CustomerProduct(DateTimeUtil.getTimeWithDaysAgo(10), "15669059666577", "15669059666577", "15669059666577", DateTimeUtil.getTimeWithDaysAgo(10), 1, 1);
		
		Problems problems = new Problems(1, "Charger Issue");
		List<Problems>  problemsList = new ArrayList<Problems>();
		problemsList.add(problems) ;
		
		CreateJobPOJO createJobPayload = new CreateJobPOJO(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsList);
		
		
		
		RestAssured
			.given()
				.spec(SpecUtil.requestSpecWithAuth(Roles.FD, createJobPayload))
				.body(createJobPayload)
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
