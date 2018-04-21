<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>

<body>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:form action="loginprocess">
<s:textfield name="username" lable="UserName" placeholder="uname"></s:textfield>
<s:password name="userpass" lable="Password"></s:password>
<s:submit value="Login" ></s:submit>
</s:form>
</body>

</html>