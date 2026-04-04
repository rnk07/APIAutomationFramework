package com.demo.csv;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ReadCsvFile {

	public static void main(String[] args) throws IOException, CsvException {
		// Read CSV File in JAVA
		
		InputStream is= Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/loginCreds.csv");
		
//		File csvFile = new File(System.getProperty("user.dir")+"/src/main/resources/testData/loginCreds.csv");
//		System.out.println(csvFile);
//		FileReader fr = new FileReader(csvFile);
		
		InputStreamReader isReader=  new InputStreamReader(is);
		CSVReader csvReader = new CSVReader(isReader);
		//csv reader requires a reader
		
		List<String[]> dataList = csvReader.readAll();
		
		for(String[] dataArray :dataList) {
			
			for(String data : dataArray)
			System.out.println(data);
		}

	}

}
