<jsp:useBean id="log" class="com.appsource.LoginBean" scope="request">
<jsp:setProperty name="log" property="*"/>

</jsp:useBean>

<jsp:forward page="Login.do"></jsp:forward>