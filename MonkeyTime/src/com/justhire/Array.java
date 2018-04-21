package com.justhire;

class Array{ 
	
public static void main(String[] args){  
	
/*	 //Fibonacci
	int f1=0;
	int f2=1;
	int f3;
	int i;
	int count=10;
	System.out.print(f1+" "+f2);
	 for(i=3;i<count;i++)
	 {
		 f3=f1+f2;
		 System.out.print(" "+f3);
		 f1=f2;
		 f2=f3;
	 }*/
	 
	
	    
	        System.out.println(fibonacci(10));
	       // index++;
	    
}
	    public static long fibonacci (int i)
	    {
	        if (i == 0) return 0;
	        if (i<= 2) return 1;

	        long fibTerm = fibonacci(i - 1) + fibonacci(i - 2);
	        return fibTerm;
	    }
}
