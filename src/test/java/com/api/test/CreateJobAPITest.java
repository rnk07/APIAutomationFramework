package com.api.test;

import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.pojo.CreateJobPOJO;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager2;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateJobAPITest {

	@Test
	public void createJobAPITest() {
		
		//Creating CreateJobPOJO object
		
		
		Customer customer = new Customer("Ronak", "Test", "123456789", "", "ronak@test.com", "");
		CustomerAddress customerAddress = new CustomerAddress("5 ", "John Street", "Gandhi Bag", "Inrobit", "Mumbai", "395005","India","Pune");
		CustomerProduct customerProduct = new CustomerProduct("2026-03-25T04:00:00.000Z", "11498059616542", "11498059616542", "11498059616542", "2026-03-25T04:00:00.000Z", 1, 1);
		
		Problems problems = new Problems(1, "Charger Issue");
		Problems[] problemsArray = new Problems[1];
		problemsArray[0] = problems;
		
		CreateJobPOJO createJobPayload = new CreateJobPOJO(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsArray);
		
		
		
		RestAssured
			.given()
				.spec(SpecUtil.requestSpecWithAuth(Roles.FD, createJobPayload))
				.body(createJobPayload)
				.when()
					.post("/job/create")
				.then()
				.spec(SpecUtil.responseSpec_OK());
			
	}

}
