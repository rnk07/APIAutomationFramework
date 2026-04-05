package com.api.test.datadriven;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.utils.ConfigManager2;
import com.api.utils.SpecUtil;
import com.dataProviders.api.bean.UserBean;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPIDataDrivenTest {
	

	
	
	
	
	@Test(description = "Verifying if login API is working for iamfd user",
			groups = {"smoke","sanity","datadriven"},
			dataProviderClass = com.dataProviders.DataProviderUtils.class,
			dataProvider  ="loginAPIDataProvider")
	public void loginAPITest(UserBean userbean){
		

		
		RestAssured
			.given()
				.spec(SpecUtil.requestSpec(userbean))
			.when()
				.post("login")
			.then()
				.spec(SpecUtil.responseSpec_OK())
				.body("message", Matchers.matchesPattern("Success"))
				.and()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/loginAPIResponseSchema.json"));
	
		
	}
	
	

}
