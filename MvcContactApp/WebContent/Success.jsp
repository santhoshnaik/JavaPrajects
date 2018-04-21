<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
       <h3>Registration is successfully completed</h3><br><br>
       <%
       
       String name=(String)request.getAttribute("uname");
       String email=(String)request.getAttribute("email");
       out.println("welcome "+ name+" you are succesfully registered with the email id = "+email);
       out.println(" ");
       String link=(String)request.getAttribute("link");
       out.println(link);
       %>

</body>
</html>
