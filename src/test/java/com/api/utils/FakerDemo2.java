package com.api.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.model.CreateJobPOJO;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

public class FakerDemo2 {
	private final static String COUNTRY = "India";
	public static void main(String[] args) {

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
		int id =random.nextInt(25);
		Problems problem = new Problems(id+1, landmarkName); 
		System.out.println(problem);
	
		List<Problems> problemList = new ArrayList<Problems>();
		
		problemList.add(problem);
		
		CreateJobPOJO payload = new CreateJobPOJO(0, 2, 1, 1, customer, customerAddress, customerProduct, problemList);
		
	
	
	
	}
	


}
