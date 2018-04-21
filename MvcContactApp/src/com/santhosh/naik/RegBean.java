package com.santhosh.naik;

public class RegBean {
	public String uname,email,pwd,rpwd;
	String msg="";
	
	
    public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
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
	public String getRpwd() {
		return rpwd;
	}
	public void setRpwd(String rpwd) {
		this.rpwd = rpwd;
	}
	
    public String validate()
    {
    	System.out.println("inside the validate method");
    	
    	if(uname==null||uname.trim().equals(""))
    	{
    	 msg +="the name box should not be empty </br>";
    	}
    	if(email==null||email.trim().equals(""))
    	{
    		msg +="email is madatory</br>";
    	}
    	
    	if(pwd==null||pwd.trim().equals(""))
    	{
    		msg+="password is compulsory </br>";
    	}
        if(rpwd==null||rpwd.trim().equals(""))
        {
    	    	msg+="repeate the password";
        }  
        else
        	if(!(pwd.equals(rpwd)))
        	{
    		msg+="The password did't match repeate it properly  </br>";
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
		result = prime * result + ((msg == null) ? 0 : msg.hashCode());
		result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
		result = prime * result + ((rpwd == null) ? 0 : rpwd.hashCode());
		result = prime * result + ((uname == null) ? 0 : uname.hashCode());
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
		RegBean other = (RegBean) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (msg == null) {
			if (other.msg != null)
				return false;
		} else if (!msg.equals(other.msg))
			return false;
		if (pwd == null) {
			if (other.pwd != null)
				return false;
		} else if (!pwd.equals(other.pwd))
			return false;
		if (rpwd == null) {
			if (other.rpwd != null)
				return false;
		} else if (!rpwd.equals(other.rpwd))
			return false;
		if (uname == null) {
			if (other.uname != null)
				return false;
		} else if (!uname.equals(other.uname))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "RegBean [uname=" + uname + ", email=" + email + ", pwd=" + pwd
				+ ", rpwd=" + rpwd + ", msg=" + msg + "]";
	}
	
    
   
    
}
