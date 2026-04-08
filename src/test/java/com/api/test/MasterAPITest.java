package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.services.MasterService;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;


@Listeners(com.listeners.APITestListener.class)
public class MasterAPITest {
	private MasterService master;
	
	@BeforeMethod(description = "Initilizing Master Service.")
	public void setUp() {
		 master = new MasterService();
		
	}
	
	
	
	@Test(description = "Verify if Master API is working fine.",groups = {"smoke","sanity","apiRegression"})
	public void masterAPITest() {
		
		
		
		master.master(Roles.FD)
			.then()
				.spec(SpecUtil.responseSpec_OK())
				.body("message", Matchers.matchesPattern("Success"))
				.body("data", Matchers.notNullValue())
				.body("$", Matchers.hasKey("message"))
				.body("$", Matchers.hasKey("data"))
				.body("data", Matchers.hasKey("mst_oem"))
				.body("data.mst_oem.size()",Matchers.equalTo(2))
				.body("data.mst_oem.id", Matchers.everyItem(Matchers.notNullValue()))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/masterAPIResponseSchema-FD.json"));
	}
	
	@Test(description = "Verify if Master API gives 401 error for invalid token.",groups = {"smoke","apiNegative","apiRegression"})
	public void invalidTokenMasterAPITest() {
		master.masterWithNoAuth()
		.then()
			.spec(SpecUtil.responseSpec_TEXT(401));
	}
	
	
	
	
	
	
}
