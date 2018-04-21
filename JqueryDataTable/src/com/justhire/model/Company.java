package com.justhire.model;

public class Company {
	
	public Company(String name,String address,String town)
	{
		this.name=name;
		this.town=town;
		this.address=address;
	}
	
	static int nextID=17;
	public String name;
	public String address;
	public String town;
	
	
	public String getName() {
		System.out.println("inside setter");
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}

}
