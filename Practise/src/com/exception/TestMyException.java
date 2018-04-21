package com.exception;

public class TestMyException {

	public void check(int age) throws MyException
	{
		if(age<18)
			throw new MyException("age cannot be less than 18");
		else
			System.out.println("no exception occured");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		TestMyException t=new TestMyException();
		try{
		t.check(11);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}