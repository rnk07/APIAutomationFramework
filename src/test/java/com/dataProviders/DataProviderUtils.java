package com.dataProviders;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.api.utils.CSVReaderUtil;
import com.dataProviders.api.bean.UserBean;

public class DataProviderUtils {
	
	@DataProvider(name = "loginAPIDataProvider",parallel = true)
	public static Iterator<UserBean> loginAPIDataProvider() {
		//Data provider need to return something
		// it can return [] -1D Array
		//it can return [][] -2D Array
		//it can return Iterator<>
		
	 return	CSVReaderUtil.loadCsv("testData/loginCreds.csv");

	}
	

}
