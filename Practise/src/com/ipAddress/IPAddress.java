package com.ipAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;
public class IPAddress {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String hostname="";
try {
	 hostname=InetAddress.getByName("192.18.97.39").getHostName();
} catch (UnknownHostException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

System.out.println("Host Name is = "+hostname);
	}

}


