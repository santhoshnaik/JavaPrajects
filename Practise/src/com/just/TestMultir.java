package com.just;

public class TestMultir {
	
	
	
	
	public static void fibonacci(int flen)
	{
		
		int a=0;
		int b=1;int c=0;
		String fibo="";
		fibo +=a+" "+b+" ";
		for(int i=1;i<flen-1;i++)
		{
			c=a+b;
			fibo+=c+" ";
			a=b;
			b=c;
		}
		System.out.println("fibonacci series of "+flen+" =="+fibo);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		TestMultir.fibonacci(10);
		
		
		int[] nums={4,1,2,3};
		boolean result=false;
		for(int i=0;i<nums.length-2;i++)
		{

			   if(nums[i+1]-nums[i]==1&&nums[i+2]-nums[i+1]==1)
			   {
			   result=true;
			   System.out.println(result);
			    break;
			   }
			}
			System.out.println("result "+result);
		String s="jav ajaVA";
		
		String ss=s.toLowerCase();
		int count=0;
		char[] arr=ss.toCharArray();
		for(int i=0;i<arr.length;i++)
		{
			if(!(Character.isDigit(arr[i])))
			{
				if(!(arr[i]==' '||arr[i]=='a'||arr[i]=='e'||arr[i]=='o'||arr[i]=='u'||arr[i]=='i'))
					count++;
			}
		}
		System.out.println(count);
	}

}
