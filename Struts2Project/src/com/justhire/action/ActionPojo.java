package com.justhire.action;

public class ActionPojo {
	private String name;
	
	public void setName(String name)
	{
		this.name=name;
	}
	
	public String getName(){
		return name;
	}
	public String execute(){
		return "success";
	}

}
