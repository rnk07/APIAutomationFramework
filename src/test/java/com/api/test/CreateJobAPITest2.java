package com.api.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.api.constant.Model;
import com.api.constant.OEM;
import com.api.constant.Platform_Id;
import com.api.constant.Problem;
import com.api.constant.Product;
import com.api.constant.Roles;
import com.api.constant.Service_Location;
import com.api.constant.Warranty_Status;
import com.api.request.model.CreateJobPOJO;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.DateTimeUtil;
import com.api.utils.SpecUtil;
import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest2 {
	private final static String COUNTRY = "India";
	private Customer customer;
	private CustomerAddress customerAddress;
	private CustomerProduct customerProduct;
	private Problems problems ;
	private List<Problems>  problemsList;
	private CreateJobPOJO createJobPayload;
	
	@BeforeMethod
	public void setUp() {
		
		//Create Fake CreateJOB API Payload
				Faker faker = new Faker(new Locale("en-IND")); //India specific fake Data
			
				String firstName =faker.name().firstName();
				String lastName =faker.name().lastName();
				String mobileNumber = faker.numerify("99########");
				String altMobileNumber = faker.numerify("99########");
				String emailAddress = faker.internet().emailAddress();
				String altEmailAddress = faker.internet().emailAddress();
				
				Customer customer = new Customer(firstName, lastName, mobileNumber, altMobileNumber, emailAddress, altEmailAddress);
				System.out.println(customer);
				
				//Customer Address Object Creation
				String flatNumber = faker.address().buildingNumber();
				String apartmentName = faker.address().streetName();
				String streetName = faker.address().streetName();
				String landmarkName = faker.address().streetName();
				String areaName = faker.address().streetName();
				String pincode =  faker.numerify("#####");
				
				String state = faker.address().state();
				
				CustomerAddress customerAddress = new CustomerAddress(flatNumber, apartmentName, streetName, landmarkName, areaName, pincode, COUNTRY, state);
				System.out.println(customerAddress);
				
				//Customer Product Object Creation

				
				String dop = DateTimeUtil.getTimeWithDaysAgo(15);
				String imeiserialNumber = faker.numerify("###############");
				String popUrl = faker.internet().url();
				
				CustomerProduct customerProduct = new CustomerProduct(dop, imeiserialNumber, imeiserialNumber, imeiserialNumber, popUrl, 1, 1);
			
				System.out.println(customerProduct);
			
			
				//Problems Object Creation
				
				String remark = faker.lorem().sentence(5);
				Random random = new Random();
				int id =random.nextInt(15);
				
				Problems problem = new Problems(id+1, remark); 
				System.out.println(problem);
			
				List<Problems> problemList = new ArrayList<Problems>();
				
				problemList.add(problem);
				
				createJobPayload = new CreateJobPOJO(0, 2, 1, 1, customer, customerAddress, customerProduct, problemList);
				
			
		
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
