<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Calculate</title>
</head>
<body>
<%@ page errorPage="error_page.jsp" %>
<%
String num1=request.getParameter("n1");
String num2=request.getParameter("n2"); 

int i1=Integer.parseInt(num1);
int i2=Integer.parseInt(num2);
int i3=i1/i2;
out.println("Divided num ="+i3);
%>

</body>
</html>