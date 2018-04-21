package com.exception;

public class TestException {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		try{
			int i=10/0;
		}
		catch(ArithmeticException e)
		{
			System.out.println(e);
		}
	}

}
