package com.model;

public class RevenueReport {
	
	private String SlNo;
	private String company;
	private String country;
	private String year;
	private String revenue;
//Constructor
	public RevenueReport(String SlNo,String company, String country, String year, String revenue) {
		this.company = company;
		this.country = country;
		this.year = year;
		this.revenue = revenue;
		this.SlNo=SlNo;
	}
    
    
	public void setSlNo(String slno)
	{
		this.SlNo=slno;
	}
	public String getSlNo()
	{
		return SlNo;
	}
	public String getCountry() {
		return country;
	}

	public String getRevenue() {
		return revenue;
	}

	public String getCompany() {
		return company;
	}

	public String getYear() {
		return year;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setRevenue(String revenue) {
		this.revenue = revenue;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setYear(String year) {
		this.year = year;
	}
}