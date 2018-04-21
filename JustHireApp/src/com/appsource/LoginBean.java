package com.appsource;

public class LoginBean {

	String uname;
	String pwd;
    String msg="";
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String validate()
	{
		if(uname==null||uname.trim().equals(" "))
		{
			msg +="please enter your uname id <br>";
		}
		if(pwd==null||pwd.trim().equals(""))
		{
			msg +="please enter ur password";
		}
		
		if(msg.equals(""))
			return "SUCCESS";
		else
			return msg;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginBean other = (LoginBean) obj;
		if (uname == null) {
			if (other.uname != null)
				return false;
		} else if (!uname.equals(other.uname))
			return false;
		if (pwd == null) {
			if (other.pwd != null)
				return false;
		} else if (!pwd.equals(other.pwd))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "LogBean [uname=" + uname + ", pwd=" + pwd + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uname == null) ? 0 : uname.hashCode());
		result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
		return result;
	}

}
