package com.api.utils;

import java.util.Locale;

import com.github.javafaker.Faker;

public class FakerDemo {

	public static void main(String[] args) {
		
		//faker library for test data generation
		//create fakerUtil that uses this faker library
		
		Locale locale = new Locale("en-IND");
		Faker faker = new Faker(locale);
		
		String fName =faker.name().firstName();
		System.out.println(fName);
		
		System.out.println(faker.address().buildingNumber());
		System.out.println(faker.address().streetAddress());
		System.out.println(faker.number().digits(11));
		System.out.println(faker.numerify("226#######"));
		System.out.println(faker.internet().emailAddress());
	}
	


}
