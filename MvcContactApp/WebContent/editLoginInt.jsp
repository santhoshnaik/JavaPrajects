<jsp:useBean id="editLogin" class="com.santhosh.naik.EditLoginBean" scope="request">
	<jsp:setProperty name="editLogin" property="*"/>
</jsp:useBean>
<jsp:forward page="editLoginSubmit.do"></jsp:forward>




