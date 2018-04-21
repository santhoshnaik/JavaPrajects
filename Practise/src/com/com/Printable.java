package com.com;

public interface Printable {
	int i=10;
 void working();
 
 default void msg()
 {
	 System.out.println("inside default masg");
 }
	

}
