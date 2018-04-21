package com.justhire;



import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
  
public class LoginDao {  
  
public static boolean validate(String username,String userpass){  
 boolean status=false;  
  try{  
   Class.forName("org.hsqldb.jdbcDriver");  
   Connection con=DriverManager.getConnection(  
   "jdbc:hsqldb:mem:.","SA"," ");  
     
   PreparedStatement ps=con.prepareStatement(  
     "select * from user3333 where name=? and password=?");  
   ps.setString(1,username);  
   ps.setString(2,userpass); 
   
   ResultSet rs=ps.executeQuery();  
   status=rs.next();  
  }catch(Exception e){e.printStackTrace();}  
 return status;  
}  
}  