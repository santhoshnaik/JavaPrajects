package com.service;

import java.util.LinkedList;
import java.util.List;

import com.model.RevenueReport;

public class BusinessService {

public static List<RevenueReport> getCompanyList() {

	List<RevenueReport> listOfCompany = new LinkedList<RevenueReport>();

	listOfCompany.add(new RevenueReport("Bosch", "Germany",2012, 40000));
	listOfCompany.add(new RevenueReport("XYZ", "India",2014, 10000));
	listOfCompany.add(new RevenueReport("Robotics", "United Kingdom",2011, 35000));
	listOfCompany.add(new RevenueReport("Merch", "USA",2010, 20000));
	listOfCompany.add(new RevenueReport("Foxtron", "Indonesia",2009, 15000));
	listOfCompany.add(new RevenueReport("Benz", "Germany",2013, 50000));
	listOfCompany.add(new RevenueReport("Audi", "United Kingdom",2012, 60000));
	listOfCompany.add(new RevenueReport("Hyat", "France",2011, 30000));
	listOfCompany.add(new RevenueReport("HCL", "India",2007, 23000));
	listOfCompany.add(new RevenueReport("CTS", "USA",2010, 42000));
	listOfCompany.add(new RevenueReport("Accenture", "France",2008, 15000));
	listOfCompany.add(new RevenueReport("Air India", "India",2005, 10000));
	listOfCompany.add(new RevenueReport("Kingfisher", "India",2011, 8000));
	listOfCompany.add(new RevenueReport("Vodaphone", "Netharland",2006, 45000));
	
	return listOfCompany;
}
}