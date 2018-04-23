package com.singleton;

public class Santhosh {

	private Santhosh(String s)
	{
	System.out.println("inside constructor");	
	}
	
static	Santhosh santu=new Santhosh("Narayanapura");
	
	public static Santhosh getInstance()
	{
		return santu;
	}
}
