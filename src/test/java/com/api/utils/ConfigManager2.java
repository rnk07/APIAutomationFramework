package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager2 {
//	Program to read from config.properties

	private static Properties prop = new Properties();
	private static String path ;
	private static String env;

	private ConfigManager2() {
		// private constructor- we cant create object outside of this class.
		// made this more robust- no one can create object outside of this class
	}

	// Operation of loading the properties file in the memory
	// Static block it will be executed once during class loading time
	static {

		env = System.getProperty("env","qa");
		env = env.toLowerCase().trim();
		switch (env) {

			case "dev": {
				path = "config/config.dev.properties";
				System.out.println("Running Tests on "+env+" Environment.");
				break;
			}

			case "qa": {
				path = "config/config.qa.properties";
				System.out.println("Running Tests on "+env+" Environment.");
				break;
			}

			case "uat": {
				path = "config/config.uat.properties";
				System.out.println("Running Tests on "+env+" Environment.");
				break;
			}

			default: {
				path = "config/config.qa.properties";
			}

		}

		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

		if (input == null) {
			throw new RuntimeException("Can Not Fine File at Path: " + path);
		}

		try {

			prop.load(input);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {

		return prop.getProperty(key);
	}

}
