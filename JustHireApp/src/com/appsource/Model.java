package com.appsource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Model {

	Connection con=null;
	PreparedStatement pst=null;
	
	public String login(LoginBean lb)
	{
		String msg=lb.validate();
	    String pwd2=null;	
	    String pwd=lb.getPwd();
	    String uname=lb.getUname();
	    String uname2;
	    System.out.println("entered login details :"+uname+" "+pwd);
		if(msg.equals("SUCCESS"))
		{
			System.out.println("inside 1st if block");
			try{
				Class.forName("com.mysql.jdbc.Driver");
				 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","123456");
				 if(con!=null)
				 {
					 System.out.println("inside con if block");
				       pst=con.prepareStatement("select pwd from samdatabase.customer where uname=?");
				 
				        pst.setString(1,"uname");
				       
			           ResultSet rs=pst.executeQuery();
				 while(rs.next())
				   {
					 System.out.println("inside con while block block");
					 pwd2=rs.getString("pwd");
					 System.out.println("pwd2 is = "+pwd2);
					 }
				 
				 if(pwd.equals(pwd2))
				 {
					 msg="SUCCESS"; 
				 }
				 else
				 {
					 return "uname/passsword mismatch error";
				 }
				 }
				 else
				 {
					 return "database connction error";
				 }
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			finally{
				
				{
					try{
						if(con!=null)
							con.close();
						if(pst!=null)
							pst.close();
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage());
					}
				}
				
			}
			
			
		}
		return msg;
	}
	
}
