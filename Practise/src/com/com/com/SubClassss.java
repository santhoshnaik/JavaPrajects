package com.com.com;
import java.lang.String;
public class SubClassss {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s="santhosh";
		System.out.println(s.hashCode()+s);
		String s1=new String("santhosh");
		String s3=s.concat("naik");
		
		System.out.println(s1.hashCode()+s1);
		System.out.println(s.hashCode()+s);
		System.out.println(s3.hashCode() +s3);
	}

}
