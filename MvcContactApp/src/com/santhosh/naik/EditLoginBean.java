package com.santhosh.naik;

public class EditLoginBean {
	String uname,pwd,rpwd;
   String msg="";
	
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getRpwd() {
		return rpwd;
	}

	public void setRpwd(String rpwd) {
		this.rpwd = rpwd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
public String validate()
{
	System.out.println("inside the validate() of edit login bean object");
	if(uname==null||uname.trim().equals(""))
	{
		msg+="Enter your name <br>";
	}
	if(pwd==null||pwd.trim().equals(""))
	{
		msg+="enter pasword <br>";
	}
	if(rpwd==null||rpwd.trim().equals(""))
	{
		msg+="repeat password properly<br>";
	}
	else
	{
	  if(!pwd.equals(rpwd))
	  {
		msg+="password dint match";
	  }
	}
	  if(msg.equals(""))
		return "SUCCESS";
	else
		return msg;
}

@Override
public String toString() {
	return "EditLoginBean [uname=" + uname + ", pwd=" + pwd + ", rpwd=" + rpwd
			+ ", msg=" + msg + "]";
}

}
