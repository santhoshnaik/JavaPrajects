package com.mvc.proj;

public class LoginBean {
	
	private String name,password;

	
	public void setName(String name)
	{
		this.name=name;
	}
	public void setPassword(String password)
	{
		this.password=password;
	}
	 public String getName(){
		 return name;
	 }
	 
	 public String getPassword(){
		 return password;
	 }
	 
	 public boolean validate(){
		 
		 if(password.equals("1234"))
		 {
			 return true;
		 }
		 else 
		 {
			 return false;
		 }
	 }
}
