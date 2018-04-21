package com.santhosh.naik;

public class DeleteConBean {
	String uname;
  String msg="";
	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
	 
	public String validate()
	{
		if(uname.trim().equals(""))
			msg+="please enter  contact name";
		if(msg.equals(""))
			return "SUCCESS";
		else
			return msg;
			
	}
}
