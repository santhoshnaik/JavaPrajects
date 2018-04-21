package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import com.model.RevenueReport;

public class BusinessService {
	static Connection con=null;
static 	PreparedStatement ps=null;
static	ResultSet rs=null;

public static List<RevenueReport> getCompanyList() {

	List<RevenueReport> listOfCompany = new LinkedList<RevenueReport>();

	try
	{
		con=JDBCHelper.getConnection();
		ps=con.prepareStatement("select*from JqueryTable");
		rs=ps.executeQuery();
		while(rs.next())
		{
			RevenueReport rr=
			new RevenueReport(rs.getString("SlNo"),rs.getString("company"),
		   rs.getString("country"),rs.getString("year"),rs.getString("revenue"));
			
			listOfCompany.add(rr);
		}
	}
	catch(Exception e)
	{
		System.out.println(e.getMessage());
	}
	finally{
		JDBCHelper.close(rs);
		JDBCHelper.close(ps);
		JDBCHelper.close(con);
	}
	
	return listOfCompany;
	
}
}