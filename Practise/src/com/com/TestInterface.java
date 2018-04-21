package com.com;

public class TestInterface implements Printable,Showable{
	
	public void working(){
		System.out.println("inside testinterface"+i);
	}
public void show(){
	
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Printable t=new TestInterface();
		t.working();
		t.msg();
	}

}
