package com.appsource;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControllerServlet
 */
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
        System.out.println("inside servlet constructor");
        // TODO Auto-generated constructor stub
    }
    public void init() throws ServletException {
		System.out.println("init() of S");
}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		process(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request,response);
	}
public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{
	String uri=request.getRequestURI();
	RequestDispatcher rd=null;
	Model md=new Model();
	if(uri.contains("/Login"))
	{
		LoginBean lb=(LoginBean)request.getAttribute("log");
		String result=md.login(lb);
		if(result.equals("SUCCESS"))
		{
		rd=request.getRequestDispatcher("Loginsuccesspage.jsp");
		rd.forward(request, response);
		}
		else{
			request.setAttribute("error", result);
			rd=request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
	}
}
}
