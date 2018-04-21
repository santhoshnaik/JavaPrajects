package com.justhire.httpclient;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

public class HttpsClientUtils {

	public static final String SERVICE_URL = "https://jh-justhireapp.rhcloud.com/hireme/service/";
	public static final int POST_TASK = 1;
	public static final int GET_TASK = 2;
	// connection timeout, in milliseconds (waiting to connect)
	private static final int CONN_TIMEOUT = 6000;
	// socket timeout, in milliseconds (waiting for data)
	private static final int SOCKET_TIMEOUT = 10000;
	private static HttpClient httpclient;

	public static synchronized HttpClient getHttpsClient() {

		if (httpclient != null) {
			return httpclient;
		}

		SSLSocketFactory sslFactory = null;
		try {
			sslFactory = new JustHireSSLSocketFactory(null);
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sslFactory
				.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

		// Enable HTTP parameters
		HttpParams httpParams = new BasicHttpParams();
		HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(httpParams, HTTP.UTF_8);
		ConnManagerParams.setTimeout(httpParams, 1000);
		HttpConnectionParams.setConnectionTimeout(httpParams, CONN_TIMEOUT);
		HttpConnectionParams.setSoTimeout(httpParams, SOCKET_TIMEOUT);

		// Register the HTTP and HTTPS Protocols.
		SchemeRegistry registry = new SchemeRegistry();
		registry.register(new Scheme("http", PlainSocketFactory
				.getSocketFactory(), 80));
		registry.register(new Scheme("https", sslFactory, 443));

		ClientConnectionManager clientConManager = new ThreadSafeClientConnManager(
				httpParams, registry);

		httpclient = new DefaultHttpClient(clientConManager, httpParams);
		return httpclient;

	}

}
