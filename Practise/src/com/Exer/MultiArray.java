package com.Exer;

public class MultiArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[][] arr={{1,2,3},{2,5,8},{4,8,9}};
		
		for(int i=0;i<arr.length;i++)
		{
			for(int j=0;j<arr.length;j++)
			{
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		
	}

}
