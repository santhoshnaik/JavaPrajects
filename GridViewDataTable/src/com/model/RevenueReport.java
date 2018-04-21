package com.model;

public class RevenueReport {

	public RevenueReport(String company, String country, int year, int revenue) {
		this.company = company;
		this.country = country;
		this.year = year;
		this.revenue = revenue;
	}

	private String company;
	private String country;
	private int year;
	private int revenue;

	public String getCountry() {
		return country;
	}

	public int getRevenue() {
		return revenue;
	}

	public String getCompany() {
		return company;
	}

	public int getYear() {
		return year;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setRevenue(int revenue) {
		this.revenue = revenue;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setYear(int year) {
		this.year = year;
	}
}