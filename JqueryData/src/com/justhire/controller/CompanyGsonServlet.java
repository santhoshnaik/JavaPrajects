package com.justhire.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.justhire.model.Company;
import com.justhire.model.DataRepository;
import com.justhire.model.JQueryDataTableParamModel;


public class CompanyGsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public CompanyGsonServlet() {
        super(); 
        System.out.println("No arg constructor");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("inside CompanyGsonServlet servlet");
JQueryDataTableParamModel param = DataTablesParamUtility.getParam(request);
		System.out.println("param :>"+param.toString());
		String sEcho = param.sEcho;
		System.out.println("sEcho : "+sEcho);
		int iTotalRecords; // total number of records (non filtered)
		int iTotalDisplayRecords; //value will be set when code filters companies by keyword
		JsonArray data = new JsonArray(); //data that will be shown in the table

		iTotalRecords = DataRepository.GetCompanies().size();
		System.out.println("iTotalRecords : "+iTotalRecords);
		List<Company> companies = new LinkedList<Company>();
		for(Company c : DataRepository.GetCompanies()){
			if(	c.getName().toLowerCase().contains(param.sSearch.toLowerCase())
	 			||
				c.getAddress().toLowerCase().contains(param.sSearch.toLowerCase())
				||
				c.getTown().toLowerCase().contains(param.sSearch.toLowerCase()))
			{
				companies.add(c); // add company that matches given search criterion
			}
		}
		iTotalDisplayRecords = companies.size(); // number of companies that match search criterion should be returned
		
		final int sortColumnIndex = param.iSortColumnIndex;
		final int sortDirection = param.sSortDirection.equals("asc") ? -1 : 1;
		
		Collections.sort(companies, new Comparator<Company>(){
		//override
			public int compare(Company c1, Company c2) {	
				switch(sortColumnIndex){
				case 0:
					return c1.getName().compareTo(c2.getName()) * sortDirection;
				case 1:
					return c1.getAddress().compareTo(c2.getAddress()) * sortDirection;
				case 2:
					return c1.getTown().compareTo(c2.getTown()) * sortDirection;
				}
				return 0;
			}
		});
		
		if(companies.size()< param.iDisplayStart + param.iDisplayLength) {
			companies = companies.subList(param.iDisplayStart, companies.size());
		} else {
			companies = companies.subList(param.iDisplayStart, param.iDisplayStart + param.iDisplayLength);
		}
	
		try {
			JsonObject jsonResponse = new JsonObject();			
			jsonResponse.addProperty("sEcho", sEcho);
			jsonResponse.addProperty("iTotalRecords", iTotalRecords);
			jsonResponse.addProperty("iTotalDisplayRecords", iTotalDisplayRecords);
			
			for(Company c : companies){
				JsonArray row = new JsonArray();
				row.add(new JsonPrimitive(c.getName()));
				row.add(new JsonPrimitive(c.getAddress()));
				row.add(new JsonPrimitive(c.getTown()));
				data.add(row);
			}
			jsonResponse.add("aaData", data);
			
			response.setContentType("application/Json");
			response.getWriter().print(jsonResponse.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setContentType("text/html");
			response.getWriter().print(e.getMessage());
		}

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
