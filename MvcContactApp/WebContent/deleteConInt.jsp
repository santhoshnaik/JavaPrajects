
<jsp:useBean id="delete" class="com.santhosh.naik.DeleteConBean" scope="request">
	<jsp:setProperty name="delete" property="*"/>
</jsp:useBean>
<jsp:forward page="deleteConOut.do"/>