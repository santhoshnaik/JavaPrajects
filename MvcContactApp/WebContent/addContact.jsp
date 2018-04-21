<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
      <b>Fill all the details and submit</b>
      
      <form action="addConInt.jsp" method="post">
      Enter Name:<input type="text" name="uname"><br>
      Enter Email:<input type="text" name="email"><br>
      Enter Ph.No:<input type="text" name="phno"><br>
      Enter Age:<input type="text"  name="age"><br>
      Enter Gender:<input type="radio"  name="gender" value="male">Male<input type="radio" name="gender" value="female">Female<br>
      Enter DOB:<input type="text" name="dob">:YYYY-MM-DD<br>
      Company:<input type="text" name=company><br>
      <input type="submit" value="click me">
      </form>
      
      <%
         String msg=(String)request.getAttribute("error");
      if(msg!=null)
        out.println(msg);
      
      %>
</body>
</html>