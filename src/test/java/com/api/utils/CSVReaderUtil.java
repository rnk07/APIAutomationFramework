package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.dataProviders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtil {

	// Constructor is private
	// Method static
	// Job of this to read csv file and map to Pojo/Bean

	private CSVReaderUtil() {
		// private- no once can create object out side of this class
	}

	public static Iterator<UserBean> loadCsv(String pathOfCSVFile) {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/loginCreds.csv");

		InputStreamReader isReader = new InputStreamReader(is);
		CSVReader csvReader = new CSVReader(isReader);

		CsvToBean<UserBean> csvToBean = new CsvToBeanBuilder(csvReader)
											.withType(UserBean.class)
											.withIgnoreEmptyLine(true)
											.build();

		List<UserBean> userList = csvToBean.parse();
		return userList.iterator();
	}

}
