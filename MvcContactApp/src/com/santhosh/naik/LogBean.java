package com.santhosh.naik;

public class LogBean {
	public String email,pwd;
	String msg="";


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String validate()
	{
		if(email==null||email.trim().equals(""))
		{
			msg +="please enter your email id <br>";
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogBean other = (LogBean) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
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
		return "LogBean [email=" + email + ", pwd=" + pwd + "]";
	}
	

}
