package com.justhire.driver;

public class ListviewContactItem {
	 
	private String name;
	private String phone;
	private String address;
	private String uname;
	private String pickUp;
	private String date;
public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date ="DateTime : "+date;
	}
public String getUname() {
		return "Name:"+uname;
	}
	public void setUname(String uname) {
		this.uname = "Name : "+uname;
	}
	public String getPickUp() {
		return pickUp;
	}
	public void setPick_up(String pick_up) {
		this.pickUp = "PickAddr : "+pick_up;
	}
public void SetName(String name) {
		// TODO Auto-generated method stub
		this.name=name;		
	}
public void SetPhone(String phone) 
{
		// TODO Auto-generated method stub
	this.phone="Mobile : "+phone;	
}
public void setAddress(String address)
{
	 this.address=address;
}
 public String getName()
 {
	return  "Name : "+name;
 }
 public String getPhone()
 {
	return phone;
 }
 
 public String getAddress()
 {
	return address;
 }
}
