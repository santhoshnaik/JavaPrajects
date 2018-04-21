package com.example.restexamples;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class JustHireSSLSocketFactory extends
		org.apache.http.conn.ssl.SSLSocketFactory {

	private javax.net.ssl.SSLSocketFactory sslSocketFactory = HttpsURLConnection
			.getDefaultSSLSocketFactory();

	public JustHireSSLSocketFactory(KeyStore truststore)
			throws NoSuchAlgorithmException, KeyManagementException,
			KeyStoreException, UnrecoverableKeyException {
		super(null);

		try {
			SSLContext context = SSLContext.getInstance("TLS");

			// Make trust manager to accept all type of certificates
			TrustManager[] trustManager = new TrustManager[] { new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return new java.security.cert.X509Certificate[] {};
				}

				public void checkClientTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
				}
			} };

			context.init(null, trustManager, new SecureRandom());
			sslSocketFactory = context.getSocketFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Socket createSocket(Socket socket, String host, int port,
			boolean autoClose) throws IOException, UnknownHostException {
		return sslSocketFactory.createSocket(socket, host, port, autoClose);
	}

	@Override
	public Socket createSocket() throws IOException {
		return sslSocketFactory.createSocket();
	}
}

