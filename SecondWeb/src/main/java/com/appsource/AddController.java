package com.appsource;

import java.sql.DriverManager;
import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;





@Controller
public class AddController {
    @RequestMapping("/login")
	public ModelAndView add(HttpServletRequest request,HttpServletResponse response)
	{
		String uname=request.getParameter("uname");
		String email=request.getParameter("email");
		String pass=request.getParameter("pass");
		System.out.println(uname+" "+email+" "+pass);
		
		ModelAndView mv=new ModelAndView();
		
		ModelService ms=new ModelService();
		ms.service();
		mv.setViewName("output.jsp");
		/*
		if(b)
		{
			mv.setViewName("ouput.jsp");;
		}
		else
		{
			mv.setViewName("index.jsp");
		}
		*/
		return mv;
	}
}
