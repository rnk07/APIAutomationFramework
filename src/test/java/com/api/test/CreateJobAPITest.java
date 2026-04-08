package com.api.test;

import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
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
import com.api.services.JobService;
import com.api.utils.DateTimeUtil;
import com.api.utils.SpecUtil;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

@Listeners(com.listeners.APITestListener.class)
public class CreateJobAPITest {
	
	private Customer customer;
	private CustomerAddress customerAddress;
	private CustomerProduct customerProduct;
	private Problems problems ;
	private List<Problems>  problemsList;
	private CreateJobPOJO createJobPayload;
	
	private JobService jobService;
	
	@BeforeMethod(description = "Creating Job api request payload initilizing job service.")
	public void setUp() {
		
		 customer = new Customer("Ronak", "Test", "123456789", "", "ronak@test.com", "");
		 customerAddress = new CustomerAddress("5 ", "John Street", "Gandhi Bag", "Inrobit", "Mumbai", "395005","India","Pune");
		 customerProduct = new CustomerProduct(DateTimeUtil.getTimeWithDaysAgo(10), "99019058966577", "99019058966577", "99019058966577", DateTimeUtil.getTimeWithDaysAgo(10), Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
		
		 problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "Charger Issue");
		problemsList = new ArrayList<Problems>();
		problemsList.add(problems) ;
		
		 createJobPayload = new CreateJobPOJO(Service_Location.SERVICE_LOCATION_A.getCode(), Platform_Id.FRONT_DESK.getCode(), Warranty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customerAddress, customerProduct, problemsList);
		
		  jobService = new JobService();
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
