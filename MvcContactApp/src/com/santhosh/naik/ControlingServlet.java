package com.santhosh.naik;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ControlingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ControlingServlet() {
        super();
            }	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}
protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	String uri=request.getRequestURI();
	RequestDispatcher rd =null;
	System.out.println(uri);
	Model model=new Model();
	
	if(uri.contains("/openRegisterView"))
	{
	 rd=request.getRequestDispatcher("Register.jsp");
	 rd.forward(request, response);
	}
	
	if(uri.contains("/register"))
	{
		RegBean rb=(RegBean)request.getAttribute("reg");
		System.out.println("the user email= "+rb.getEmail());
		request.setAttribute("uname", rb.getUname());
		request.setAttribute("email", rb.getEmail());
		String linkadd="<a href='Home Page.html'>click here to go Home page</a>";
		request.setAttribute("link", linkadd);
		
		String result=model.register(rb);
		System.out.println("the result="+result);
		
		if(result.equals("SUCCESS"))
		{
			
			rd=request.getRequestDispatcher("Success.jsp");
			rd.forward(request, response);
		}
		else{
			request.setAttribute("error",result);
			rd=request.getRequestDispatcher("Register.jsp");
			rd.forward(request, response);
		}
		
	}
	if(uri.contains("/openLoginView.do"))
	{
		System.out.println("inside login if block of login");
		rd=request.getRequestDispatcher("LoginPage.jsp");
		rd.forward(request, response);
	}
	if(uri.contains("/Login"))
	{       //going to call log bean class 
	        LogBean lb=(LogBean)request.getAttribute("log");//passing all log bean instances through id=log and set property=log
			String result=model.login(lb);//here we get result after calling model classes login()
			System.out.println("result = "+result);
			if(result.equals("SUCCESS"))
			{
				HttpSession s=request.getSession(true);
				s.setAttribute("email", lb.getEmail());
				s.setAttribute("pwd", lb.getPwd());
			rd=request.getRequestDispatcher("LoginOut.jsp");
			rd.forward(request, response);
			}
			else
			{
				request.setAttribute("error", result);//storing error msg returned from model class under label error
				rd=request.getRequestDispatcher("LoginPage.jsp");
				rd.forward(request, response);
			}
			
		}
	
	//LOGOUT CODE
	if(uri.contains("/logout"))
	{
		HttpSession s=request.getSession();
		if(s!=null)
		{
		s.removeAttribute("email");
		s.removeAttribute("pwd");
		s.invalidate();
		}
		rd=request.getRequestDispatcher("LoginPage.jsp");
		rd.forward(request, response);
	}
	
	//CODE FOR EDIT THE LOGIN DETAILS
	if(uri.contains("/editLoginDetails"))
	{
		System.out.println("inside edit login details if block");
		rd=request.getRequestDispatcher("editLoginPage.jsp");
		rd.forward(request, response);
	}
	
	if(uri.contains("/editLoginSubmit"))
	{
		
		EditLoginBean elb=(EditLoginBean)request.getAttribute("editLogin");
		HttpSession hs=request.getSession();
		String email=(String)hs.getAttribute("email");
		String result=model.editLogin(elb,email);
		System.out.println("result for edit login details = "+result);
		  
		   if(result.equals("SUCCESS"))
		   {
			   String linkad="<a href=LoginOut.jsp > click here to go back</a>";
			   request.setAttribute("link", linkad);
			   rd=request.getRequestDispatcher("editLoginOutputPage.jsp");
			   rd.forward(request, response);
		   }
		   else
		   {
			   request.setAttribute("error", result);
			   rd=request.getRequestDispatcher("editLoginPage.jsp");
			   rd.forward(request, response);
		   }
	}
	if(uri.contains("/MainMenu"))
	{
		rd=request.getRequestDispatcher("MainMenu.jsp");
		rd.forward(request, response);
	}
	
	//ADDING CONTACTS 
	if(uri.contains("/addContact"))
	{
		rd=request.getRequestDispatcher("addContact.jsp");
		rd.forward(request, response);				
	}
	if(uri.contains("/addConOutput"))
	{ 
        System.out.println("inside addConOutput if bloack");
		AddConBean acb=(AddConBean)request.getAttribute("addCon");
		String result=model.addContact(acb);
		System.out.println("inside addCon output result= "+result);
		String linkAdd="<a href='MainMenu.jsp'>click here to go back to Main Menu </a>";
		request.setAttribute("link", linkAdd);
		  if(result.equals("SUCCESS"))
		  {
			  rd=request.getRequestDispatcher("addContactOutput.jsp");
			  rd.forward(request, response);
		  }
		  else
		  {
			  request.setAttribute("error", result);
			  rd=request.getRequestDispatcher("addContact.jsp");
			  rd.forward(request, response);
		  }
	}
	//
	
	if(uri.contains("/deleteContact"))
	{  
		System.out.println("inside a if block of deleteContact");
		rd=request.getRequestDispatcher("deleteContact.jsp");
		rd.forward(request, response);
	}
	if(uri.contains("/deleteConOut"))
	{  
		System.out.println("inside if block of deleteConOut");
		DeleteConBean dl=(DeleteConBean)request.getAttribute("delete");
		
		String result=model.deleteContact(dl); 
		    if(result.equals("SUCCESS"))
		    	rd=request.getRequestDispatcher("deleteConSuccess.jsp");
		      rd.forward(request, response);
	}
	
	
	}
}
