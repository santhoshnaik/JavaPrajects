<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body>
<div>
<form action="LoginInt.jsp" method="post">
UserName : <input type="text" name=uname><br><br>
PassWord : <input type="password" name=pwd><br><br>
<input type="submit" value="submit">
</form>
    <%
      String msg=(String)request.getAttribute("error");
      if(msg!=null)
    	  out.println(msg);   
      %>
</div>
</body>
</html>