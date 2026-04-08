package com.api.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.model.CreateJobPOJO;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

import io.qameta.allure.Step;

public class FakerDataGenerator {
	private final static String COUNTRY = "India";
	private final static int MST_SERVICE_LOCATION_ID=0;
	private final static int MST_PLATFORM_ID=2;
	private final static int MST_WARRANTY_STATUS_ID=1;
	private final static int MST_OEM_ID=1;
	private final static int PRODUCT_ID=1;
	private final static int MST_MODEL_ID=1;
	
	private final static Random RANDOM = new Random();
	private final static int validProblemId[]= {1,2,3,4,5,6,7,8,9,10,11,12,15,16,17,19,20,22,24,26,27,28,29};
	
	private static Faker faker =  new Faker(new Locale("en-IND")); //India specific fake Data
	
	private FakerDataGenerator() {
		
		
	}
	
	@Step("Generating Fake Create job Data")
	public static CreateJobPOJO generateFakeCreateJobData() {
		
		Customer customer  = generateFakeCustomerData();
		CustomerAddress customerAddress = generateCustomerAddress();
		CustomerProduct customerProduct = generateCustomerProduct();
		List<Problems> problemList = generateCustomerFakeProblemData();
	
		CreateJobPOJO payload = new CreateJobPOJO(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID, MST_WARRANTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problemList);
		
	return payload;
		
	}
	
	@Step("Generating Multiple Fake Create job Data")
	public static Iterator<CreateJobPOJO> generateFakeCreateJobData(int count) {

		List<CreateJobPOJO> payloadList = new ArrayList<CreateJobPOJO>();

		for (int i = 1; i <= count; i++) {

			Customer customer = generateFakeCustomerData();
			CustomerAddress customerAddress = generateCustomerAddress();
			CustomerProduct customerProduct = generateCustomerProduct();
			List<Problems> problemList = generateCustomerFakeProblemData();

			CreateJobPOJO payload = new CreateJobPOJO(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID, MST_WARRANTY_STATUS_ID,
					MST_OEM_ID, customer, customerAddress, customerProduct, problemList);
			payloadList.add(payload);
		}
		return payloadList.iterator();

	}
	
	
	@Step("Generating Fake Problems List For Create Job Payload")
	private static List<Problems> generateCustomerFakeProblemData() {
	//Problems Object Creation
		
		int totalProblemCount = RANDOM.nextInt(3)+1;
		int randomIndex ;
		Problems problem;
		List<Problems> problemList = new ArrayList<Problems>();
		
		for (int i = 1; i <= totalProblemCount; i++) {

			String remark = faker.lorem().sentence(2);
			randomIndex = RANDOM.nextInt(validProblemId.length);
			problem = new Problems(validProblemId[randomIndex], remark);
			System.out.println(problem);

			problemList.add(problem);
		}
		
		return problemList;
	}

	@Step("Generating Fake Customer Product Info")
	private static CustomerProduct generateCustomerProduct() {
		//Customer Product Object Creation

		
				String dop = DateTimeUtil.getTimeWithDaysAgo(15);
				String imeiserialNumber = faker.numerify("###############");
				String popUrl = faker.internet().url();
				
				CustomerProduct customerProduct = new CustomerProduct(dop, imeiserialNumber, imeiserialNumber, imeiserialNumber, popUrl, PRODUCT_ID, MST_MODEL_ID);
			
				System.out.println(customerProduct);
		return customerProduct;
	}
	@Step("Generating Fake Customer  Data")
	private static CustomerAddress generateCustomerAddress() {
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
		return customerAddress;
	}

	@Step("Generating Fake Customer Data")
	private static Customer generateFakeCustomerData() {
		String firstName =faker.name().firstName();
		String lastName =faker.name().lastName();
		String mobileNumber = faker.numerify("99########");
		String altMobileNumber = faker.numerify("99########");
		String emailAddress = faker.internet().emailAddress();
		String altEmailAddress = faker.internet().emailAddress();
		
		Customer customer = new Customer(firstName, lastName, mobileNumber, altMobileNumber, emailAddress, altEmailAddress);
		System.out.println(customer);
		
		return customer;
	}
	
	
	

}
