<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
         <h2>Registration Process </h2>
         
         <form action="RegInt.jsp" method="post">
         Enter Name :<input type="text" name="uname"><br><br>
         Enter email :<input type="text" name="email"><br><br>
         Enter Password :<input type="password" name="pwd"><br><br>
         Repeat password :<input type="password" name="rpwd"><br><br>
         <input type="submit" value="click here">
         
        </form>
         
         <%
         String msg=(String)request.getAttribute("error");
         if(msg!=null)        
          out.println(msg);                
         %>

</body>
</html>