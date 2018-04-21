package com.appsource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModelService {
	
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	public void service()
	{
		System.out.println("inside service method");
		try{
			Class.forName("com.mysql.jdbc.Driver");
			 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","123456");
			 ps=con.prepareStatement("select*from useracount.users");
			
			//ps.setString(1, "email");
			
			 rs=ps.executeQuery();
			while(rs.next())
			{
				String id=rs.getString("userid");
				String name=rs.getString("name");
				String email=rs.getString("email");
				String pass=rs.getString("password");
				
				System.out.println(id+" "+name+" "+email+" "+pass);
				
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally{
			try {
				rs.close();
				con.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
