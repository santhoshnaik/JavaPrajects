package com.servlet;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.*;
import com.dataTable.DataTableParameters;
import com.model.RevenueReport;
import com.service.BusinessService;

public class DataTableServlet extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		System.out.println("inside doget() of servlet");
		// Call business service class to get list of companies
		List<RevenueReport> listOfCompany = BusinessService.getCompanyList();
      
        //creating data table parameter class to initialize j-query table parameter
		DataTableParameters dataTableParam = new DataTableParameters();
		
		// Set the list fetched in aaData() method
		dataTableParam.setAaData(listOfCompany);
       
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		// Converting Java Objects into Json type
		String json = gson.toJson(dataTableParam);

		response.getWriter().print(json);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}