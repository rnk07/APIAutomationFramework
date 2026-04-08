package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.services.MasterService;
import com.api.utils.SpecUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.module.jsv.JsonSchemaValidator;


@Listeners(com.listeners.APITestListener.class)
@Epic("Job Management")
@Feature("Master API Feature")
public class MasterAPITest {
	private MasterService master;
	
	@BeforeMethod(description = "Initilizing Master Service.")
	public void setUp() {
		 master = new MasterService();
		
	}
	
	
	@Story("Master API should bring OEM Details.")
	@Description("Verify if Master API shows valid details")
	@Severity(SeverityLevel.BLOCKER)
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
	
	
	@Story("Master API show 401 error for invalid token")
	@Description("Verify if Master API shows 401 error for invalid token")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Verify if Master API gives 401 error for invalid token.",groups = {"smoke","apiNegative","apiRegression"})
	public void invalidTokenMasterAPITest() {
		master.masterWithNoAuth()
		.then()
			.spec(SpecUtil.responseSpec_TEXT(401));
	}
	
	
	
	
	
	
}
