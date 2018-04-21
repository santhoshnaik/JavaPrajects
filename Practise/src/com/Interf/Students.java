package com.Interf;

public class Students implements Cloneable {
	
	
	String name;
	int id;
	
	public Students(int id,String name)
	{
		this.id=id;
		this.name=name;
		
	}
	
	public  Object clone() throws CloneNotSupportedException{
		return super.clone();
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try{
		Students s1=new Students(111,"Sandy");
		
		Students s2=(Students)s1.clone();
		
		System.out.println(s1.id);
		System.out.println(s2.name);
		}
		catch(CloneNotSupportedException e)
		{
			System.out.println(e);
		}
	}

}
