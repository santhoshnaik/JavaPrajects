package com.santhosh.naik;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.HashSet;
import org.hsqldb.jdbc.JDBCDriver;

public class Model {
	
	String msg;
 //REGISTRATION CODE
	public String register(RegBean rb)
	{
		System.out.println("inside the regiter() method going to call validate()");
		String msg=rb.validate();
		System.out.println(msg);
		if(msg.equals("SUCCESS"))
		{
			Connection c=null;
			PreparedStatement ps_sel=null;
			PreparedStatement ps_ins=null;
			ResultSet rs=null;
			String email=rb.getEmail();
			  String name=rb.getUname();
			  String pwd=rb.getPwd();
			  String rpwd=rb.getRpwd();
			try
			{
				c=JDBCHelper.getConnection();	
				if(c!=null)
				{
					System.out.println("data base get connected");
					  
					  ps_sel=c.prepareStatement("select email from registration");
					  rs=ps_sel.executeQuery();
					  
					  HashSet<String>hs=new HashSet<String>();
					  while(rs.next())
					  {
						  String email2=rs.getString("email");
						  System.out.println(email2);
						  hs.add(email2);
					  }
					  System.out.println(hs);
					  if(hs.contains(email))
					  {
						  return "this  email id is already registered by some one.please try something other";
					  }
					  ps_ins=c.prepareStatement("insert into registration(name,email,pwd,rpwd)values(?,?,?,?)");
					  ps_ins.setString(1,name);
					  ps_ins.setString(2, email);
					  ps_ins.setString(3, pwd);
					  ps_ins.setString(4, rpwd);
					  ps_ins.execute();			  
				}
				else
				{
					return "Oops something bad happened cantact to System Admin";
				}
			}
			catch(Exception e)
			{
			 e.getMessage();
			}
			finally
			{
			try{
			     if(ps_ins!=null)
			    ps_ins.close();
				if(ps_sel!=null)					
				ps_sel.close();					
			     if(rs!=null)					
			    rs.close();					
			     if(c!=null)
				c.close();						
				}
				catch(Exception e)
				{
				 e.getMessage();
				}
			}			
							  
		}
			
		return msg;
	}
	
	//LOGIN CODE
	public String login(LogBean lb)
	{  
		String msg=lb.validate();
		if(msg.equals("SUCCESS"))
		{
		
			Connection con=null;
			PreparedStatement ps_sel=null;
			PreparedStatement ps_ins=null;
			ResultSet rs=null;
			String email=lb.getEmail();
			System.out.println(email);		
			String pwd=lb.getPwd();
			System.out.println(pwd);
			String pwd2=null;
			try
			{
				con=JDBCHelper.getConnection();
				if(con!=null)
				{				
					ps_sel=con.prepareStatement("select pwd from registration where email=?");
				
				    ps_sel.setString(1,email);
					rs=ps_sel.executeQuery();
					
					while(rs.next())
					{
					  	pwd2=rs.getString("pwd");
					  	System.out.println(pwd2);
					}
					if(pwd.equals(pwd2))
					{
						msg="SUCCESS";
					}
					else
					{
						return "email/password is mismatch.please enter it properly";
					}
				}
				else
				{
					return "Oops Database not connected contact to System Adim";
				}
				
			}
			catch(Exception e)
			{
				return msg+e.getMessage();
			}
			finally
			{
				try
			{
				if(ps_ins!=null)
					ps_ins.close();
				if(ps_sel!=null)					
					ps_sel.close();					
				if(rs!=null)					
					rs.close();					
				if(con!=null)
				con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
	 }		
	return msg; 	
	}
	
	//EDITING LOGIN DETAILS
	
	public String editLogin(EditLoginBean elb,String email)
	{
		//getting email id for sql operation from http session 
		String email2=email;
		System.out.println("in edit loginBean 2 parameter calling "+email);
		System.out.println("inside the editLogin() of model");
		String msg=elb.validate();
		System.out.println("after calling validate() in edit login = "+msg);
		if(msg.equals("SUCCESS"))
		{
			PreparedStatement ps1=null,ps2=null;
			Connection con=null;
			ResultSet rs=null;
			try
			{			
			String name =elb.getUname();
			String pwd=elb.getPwd();
			con=JDBCHelper.getConnection();
			  if(con!=null)
			  {
				ps1=con.prepareStatement("update registration set name='"+name+"'"+",pwd='"+pwd+"'"+",rpwd='"+pwd+"'"+"where email='"+email2+"'");
				ps1.execute();
			   }
			  else
			  {
				  return "Oops database is not conncted please contact to santhosh";
			  }
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				try{
					if(con!=null)
						con.close();
					if(ps1!=null)
						ps1.close();
				}
				catch(Exception e)
				{
					e.getMessage();
				}
			}
		}
			
		return msg;
	}
	//ADDING CONTACT DETAILS 
	
	public String addContact(AddConBean acb)
	{ 
		System.out.println("inside model of AddContactBean method");
		String msg=acb.validate();
		
		if(msg.equals("SUCCESS"))
		{ 
			String name=acb.getUname();String age=acb.getAge();String company=acb.getCompany();String dob=acb.getDob();
			String email=acb.getEmail();String gender=acb.getGender();String phno=acb.getPhno();
			Connection con=null;
			PreparedStatement ps_sel=null;
			PreparedStatement ps_ins=null;
			ResultSet rs=null;
			try
			{
		    	con=JDBCHelper.getConnection();
		    	System.out.println("data base connection has been established");
		    	if(con!=null)
		    	{
		    		HashSet<String> hs=new HashSet<String>();
					 if(hs.add(email))
				   	 {					
						ps_ins=con.prepareStatement("insert into addContact(name,email,age,phno,company,gender,dob)values(?,?,?,?,?,?,?)");
						ps_ins.setString(1, name);
						ps_ins.setString(2, email);
						ps_ins.setString(3, age);
						ps_ins.setString(4, phno);
						ps_ins.setString(5, company);
						ps_ins.setString(6, gender);
						ps_ins.setString(7, dob);
						ps_ins.execute();
					 }
					 else
					 {
						 return "this email id has been using by some one...please try something other";
					 }
					
		    	}
		    	else
		    		return "OWWh database has not been conneced ,contact to System Admin";
			}		    
			catch(Exception e)
				{
					e.printStackTrace();
				}
				finally
				{
					 try{
						if(ps_ins!=null)
							ps_ins.close();
						if(ps_sel!=null)					
							ps_sel.close();					
						if(rs!=null)					
							rs.close();					
						if(con!=null)
						con.close();
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
						
					}
		    	}
		
		return msg;
	}
	
	//DELETE CODE
	public String deleteContact(DeleteConBean dl)
	{
		Connection con=null;
		PreparedStatement ps1=null,ps2=null;
		ResultSet rs=null;
		String name=dl.getUname();
		System.out.println("getUname = "+name);
		String msg=dl.validate();
		if(msg.equals("SUCCESS"))
		{   
			
			System.out.println("after calling validate method of delete contact bean class");
			try
			{			  
					ps2=con.prepareStatement("delete from addContact where name="+"'"+name+"'");
					ps2.execute();
					return "SUCCESS";						
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			 
			finally
			{      
				try
				{
				    ps1.close();
					rs.close();
				    con.close();	
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}		
			return msg;
	}
}
