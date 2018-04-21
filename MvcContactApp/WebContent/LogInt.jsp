<jsp:useBean id="log" class="com.santhosh.naik.LogBean" scope="request">
<jsp:setProperty name="log" property="*"/>

</jsp:useBean>
<jsp:forward page="Login.do"></jsp:forward>