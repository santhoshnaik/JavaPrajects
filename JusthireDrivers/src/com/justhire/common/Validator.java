package com.justhire.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	
	public static void isValidDate(String d) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy", Locale.getDefault());

		sdf.setLenient(false);
		try {
			sdf.parse(d);
		} catch (ParseException e) {
			throw new Exception("Invalid Date, Please try in format dd/MM/yy");
			
		}
		

	}
	
	public static void isValidEmailAddress(final String emailId) throws Exception {
		
		if(emailId != null && !("".equals(emailId)))
		{
			final String EMAIL_PATTERN = 
		
					"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			Pattern pattern = Pattern.compile(EMAIL_PATTERN);

			Matcher matcher = pattern.matcher(emailId);
			if(!matcher.matches())
			{
				throw new Exception("Invalid Email Address.");
			}
		}
		else
		{
			throw new Exception("Please Enter Email Address.");
		}

	}

	public static void isValidPhoneNo(final String phoneNo) throws Exception {
		
		if(phoneNo != null && !("".equals(phoneNo)))
		{
			Pattern pattern = Pattern.compile("^[+]?[0-9]{10,13}$");
			Matcher matcher = pattern.matcher(phoneNo);
			if(!matcher.matches())
			{
				throw new Exception("Invalid phone number.");
			}
			
			if(phoneNo.length() > 10)
			{
				throw new Exception("Invalid phone number. Please enter 10 digit number.");
			}
		}else
		{
			throw new Exception("Please enter phone number.");
		}

	}
	
	
public static void isValidPassword(final String password, final String confirmPwd) throws Exception {
		
		if(password != null && !("".equals(password)))
		{
			//Pattern pattern = Pattern.compile(""((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})");
			//Matcher matcher = pattern.matcher(password);
			//return matcher.matches();\
			if(password.length() < 8)
			{
				throw new Exception("Password length is too short. Try with minimum 8 characters.");
			}
		}else
		{
			throw new Exception("Please enter password.");
		}
		
		if(confirmPwd != null && !(password.equals(confirmPwd)))
		{
			throw new Exception("Confirm Password does not match with the Password.");
		}

	}

public static boolean isEmpty(String str) {
	// TODO Auto-generated method stub
	 if((str != null && !("".equals(str))))
	 {
		 return false;
	 }
	 return true;
}

}
