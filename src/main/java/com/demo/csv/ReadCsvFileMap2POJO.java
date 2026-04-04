package com.demo.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

public class ReadCsvFileMap2POJO {

	public static void main(String[] args) throws IOException, CsvException {
		// Read CSV File in JAVA
		
		InputStream is= Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/loginCreds.csv");
		
	
		InputStreamReader isReader=  new InputStreamReader(is);
		CSVReader csvReader = new CSVReader(isReader);
		//csv reader requires a reader
		
		
		//write code to map csv to pojo
		
		CsvToBean<UserPOJO> csvToBean = new CsvToBeanBuilder(csvReader)
				.withType(UserPOJO.class)
				.withIgnoreEmptyLine(true)
				.build();
		
		List<UserPOJO> userList = csvToBean.parse();
		System.out.println(userList);
		

	}

}
