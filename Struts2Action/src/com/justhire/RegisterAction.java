package com.justhire;

import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport {

	 private String name,password;
	public void validate()
	{
		System.out.println("inside validate");
		if(name.length()<1)
			addFieldError("name","User Name cann't be blnk");
		
		if(password.length()<3)
			addFieldError("password","password wrong");
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String execute()
	{
		System.out.println("inside execute method");
		return "success";
	}
}
