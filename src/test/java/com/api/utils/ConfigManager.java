package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
//	Program to read from config.properties

	private static Properties prop = new Properties();
	
	private ConfigManager() {
		//private constructor- we cant create object outside of this class.
		//made this more robust- no one can create object outside of this class
	}

	// Operation of loading the properties file in the memory
	// Static block it will be executed once during class loading time
	static {
		File configFile = new File(System.getProperty("user.dir") + File.separator+"src"+ File.separator+"test"+ File.separator+"resources"+ File.separator+"config"+ File.separator+"config.properties");
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(configFile);
			prop.load(fileReader);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {

		return prop.getProperty(key);
	}

}
