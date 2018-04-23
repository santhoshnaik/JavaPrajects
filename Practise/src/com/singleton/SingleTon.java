package com.singleton;

public class SingleTon {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Santhosh santu=Santhosh.getInstance();
		
		System.out.println(santu.hashCode());
		Santhosh santu1=Santhosh.getInstance();
		System.out.println(santu1.hashCode());
		/*Santhosh santu2=new Santhosh();
		System.out.println(santu2.hashCode());*/
	}

}
