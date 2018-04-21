<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JSP page</title>
</head>
<body>





<%@ include file="NewFile.html" %>



<a href="SecondJspPage.jsp">click</a>


<%! String names="santhosh"; %>

<% out.print("Welcome to Jsp"); 


session.setAttribute("user", names);
%>

<form action=SecondJspPage.jsp method="get">

<input type=text name=uname>
<input type=submit value=go>


</form>

</body>
</html>