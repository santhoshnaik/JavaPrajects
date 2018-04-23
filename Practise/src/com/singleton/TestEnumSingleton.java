package com.singleton;

public class TestEnumSingleton {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		House home=House.INSTANCE;
		home.color="Blue";
		System.out.println(home.hashCode());
		home.houseColor();
		
		House home2=House.INSTANCE;
		home2.color="Yellow";
		System.out.println(home2.hashCode());
		home.houseColor();
				
	}

}
