package com.api.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AllureEnvironmentWriterUtil {

		private static final Logger LOGGER = LogManager.getLogger(AllureEnvironmentWriterUtil.class);
	
		public static void createEnvironmentPropertiesFile() {

		String folderPath = "target/allure-results";
		File file = new File(folderPath);
		file.mkdir();
		
		
		//environment.properties
		
		//Create Properties file
		
		Properties prop = new Properties();
//		prop.setProperty("Name", "Ronak");
		prop.setProperty("Project Name", "API Automation Framework");
		prop.setProperty("Env", ConfigManager2.env);
		prop.setProperty("BASE_URI",ConfigManager2.getProperty("BASE_URI"));
		prop.setProperty("OPERATING SYSTEM",System.getProperty("os.name"));
		prop.setProperty("OS VERSION",System.getProperty("os.version"));
		prop.setProperty("JAVA VERSION",System.getProperty("java.version"));
		
		
		FileWriter fw;
		try {
			fw = new FileWriter(folderPath+"/environment.properties");
			prop.store(fw, "My Properties File");
			LOGGER.info("Created environment.properties file at {}",folderPath);
			
		
		} catch (IOException e) {
		LOGGER.error("Unable to create environment.properties file",e);
			e.printStackTrace();
		}

		
	}

}
