package com.just;

import java.io.Serializable;

public class StingToken {
	
	class Inner{
		void msg(){
			System.out.println("inner msg");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        
	StingToken st=new StingToken();
		StingToken.Inner i=st.new Inner();
		
		i.msg();
	}

}
