package com.justhire;

public class BubbleSortAsc {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		int[] arr={5,25,10,15,20,85,0};
		int n=arr.length;
		int temp=0;
		for(int i=0;i<=n;i++){
			
			for(int j=1;j<=n-1;j++){
			   if(arr[j-1]<arr[j])
			   {
				   temp=arr[j];
				   arr[j]=arr[j-1];
				   arr[j-1]=temp;
			   }
			
			}
		}
for(int k=0;k<arr.length;k++)
{
	System.out.print(" "+arr[k]);
}
	}

}
