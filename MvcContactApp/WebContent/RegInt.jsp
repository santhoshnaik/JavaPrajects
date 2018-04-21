
<jsp:useBean id="reg" class="com.santhosh.naik.RegBean" scope="request">
	<jsp:setProperty name="reg" property="*"/>
</jsp:useBean>
<jsp:forward page="register.do"/>