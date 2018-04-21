package com.santhosh.naik;

public class AddConBean {
	
	String uname,email,age,phno,dob,company,gender;
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

public String validate()
{
	if(uname==null||uname.trim().equals(""))
		msg+="provide your name<br>";
	if(email==null||email.trim().equals(""))
		msg+=" enter email id<br>";
	if(age==null||age.trim().equals(""))
		msg+="enter your age <br>";
	if(dob==null||dob.trim().equals(""))
		msg+="Enter DOB in the given format <br>";
	if(gender==null||gender.trim().equals(""))
		msg+="select gender for sure <br>";
	if(company==null||company.trim().equals(""))
		msg+="enter company name<br>";
	if(phno==null||phno.trim().equals(""))
		msg+="requested to provide Ph.No";
	else
		return "SUCCESS";
	return msg;
}

@Override
public String toString() {
	return "AddConBean [uname=" + uname + ", email=" + email + ", age=" + age
			+ ", phno=" + phno + ", dob=" + dob + ", company=" + company
			+ ", gender=" + gender + ", msg=" + msg + "]";
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((age == null) ? 0 : age.hashCode());
	result = prime * result + ((company == null) ? 0 : company.hashCode());
	result = prime * result + ((dob == null) ? 0 : dob.hashCode());
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result + ((gender == null) ? 0 : gender.hashCode());
	result = prime * result + ((msg == null) ? 0 : msg.hashCode());
	result = prime * result + ((phno == null) ? 0 : phno.hashCode());
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
	AddConBean other = (AddConBean) obj;
	if (age == null) {
		if (other.age != null)
			return false;
	} else if (!age.equals(other.age))
		return false;
	if (company == null) {
		if (other.company != null)
			return false;
	} else if (!company.equals(other.company))
		return false;
	if (dob == null) {
		if (other.dob != null)
			return false;
	} else if (!dob.equals(other.dob))
		return false;
	if (email == null) {
		if (other.email != null)
			return false;
	} else if (!email.equals(other.email))
		return false;
	if (gender == null) {
		if (other.gender != null)
			return false;
	} else if (!gender.equals(other.gender))
		return false;
	if (msg == null) {
		if (other.msg != null)
			return false;
	} else if (!msg.equals(other.msg))
		return false;
	if (phno == null) {
		if (other.phno != null)
			return false;
	} else if (!phno.equals(other.phno))
		return false;
	if (uname == null) {
		if (other.uname != null)
			return false;
	} else if (!uname.equals(other.uname))
		return false;
	return true;
}
	

}
