<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
       <h1>please fill the  details  given below </h1>

      <form action="editLoginInt.jsp" method="post">
      Enter Your Name:<input type="text" name="uname"><br>
      Enter New pwd:<input type="password" name="pwd"><br>
      Repeat pwd:<input type="password" name=rpwd><br>
           <input type="submit" value="click here">
      </form>

    <%
       String msg=(String)request.getAttribute("error");
    if(msg!=null)
    	out.println(msg);
    
    %>
</body>
</html>