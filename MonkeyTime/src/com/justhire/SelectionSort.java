package com.justhire;

public class SelectionSort {
	
	public static void selectionSort(int[] arr)
	{
		int temp=0;
		for(int i=0;i<arr.length-1;i++)
		{
			int index=i; 
			
			for(int j=i+1;j<arr.length;j++){
				
				if(arr[j]<arr[index])
				{
					index=j;
				}
			}
			
			int smallNum=arr[index];
			arr[index]=arr[i];
			arr[i]=smallNum;
			
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] arr={2,5,65,-6,1};
		System.out.println("Before selection sort");
		for(int k=0;k<arr.length;k++)
		{
			System.out.print(" "+arr[k]);
		}
		
		selectionSort(arr);
		System.out.println("\n"+"Afete Selection sort");
		for(int m=0;m<arr.length;m++)
		System.out.print(" "+arr[m]);

	}

}
