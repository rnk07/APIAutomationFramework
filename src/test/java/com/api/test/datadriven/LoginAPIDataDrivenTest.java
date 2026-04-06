package com.api.test.datadriven;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.services.AuthService;
import com.api.utils.SpecUtil;
import com.dataProviders.api.bean.UserBean;

import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPIDataDrivenTest {
	private  AuthService authService;

	@BeforeMethod(description = "Initilizing The Auth Service")
	public void setup() {
		authService = new AuthService();

	}
	
	
	@Test(description = "Verifying if login API is working for iamfd user",
			groups = {"smoke","sanity","datadriven"},
			dataProviderClass = com.dataProviders.DataProviderUtils.class,
			dataProvider  ="loginAPIDataProvider")
	public void loginAPITest(UserBean userbean){
		

		
		
		authService.login(userbean)
			.then()
				.spec(SpecUtil.responseSpec_OK())
				.body("message", Matchers.matchesPattern("Success"))
				.and()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/loginAPIResponseSchema.json"));
	
		
	}
	
	

}
