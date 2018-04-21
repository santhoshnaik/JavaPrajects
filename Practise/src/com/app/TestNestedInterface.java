package com.app;

public class TestNestedInterface implements TestClass.A{
	
	
public void add()
{
	System.out.println("addinkjebf");
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
 
		
		TestClass.A t=new TestNestedInterface();
		t.add();
	}

}
