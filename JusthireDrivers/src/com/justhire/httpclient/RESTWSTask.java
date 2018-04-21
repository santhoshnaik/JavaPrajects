package com.justhire.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import com.justhire.driver.MainActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;



public class RESTWSTask extends AsyncTask<String, Integer, String> {

	//public static final String SERVICE_URL = "http://192.168.3.194:8088/hireme/service/";
	public int taskType = HttpsClientUtils.GET_TASK;
	private static final String TAG = "RESTWSTask";
    private Context mContext = null;
	private String processMessage = "Processing...";
	private MainActivity activity = null;
	Handler handler = null;

	private ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();

	private ProgressDialog pDlg = null;
	private JSONObject jsonObject;

	public RESTWSTask(int taskType,Handler handler, Context mContext,String processMessage)
	{

		this.taskType = taskType;
	    this.mContext = mContext;
	    this.handler = handler;
		this.activity = (MainActivity) mContext;
		this.processMessage = processMessage;
	}

	public void addNameValuePair(String name, String value) {

		params.add(new BasicNameValuePair(name, value));
	}

	public void addJSONObject(Object bookJSON) {

		this.jsonObject = (JSONObject) bookJSON;
	}

	private void showProgressDialog() {
		
		

		pDlg = new ProgressDialog(mContext);
		pDlg.setMessage(processMessage);
		pDlg.setProgressDrawable(mContext.getWallpaper());
		pDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pDlg.setCancelable(false);
		pDlg.show();

	}

	@Override
	protected void onPreExecute() {

	//	activity.hideKeyboard();
		//showProgressDialog();

	}

	protected String doInBackground(String... urls) {

		String url = urls[0];
		String result = "";

		HttpResponse response = null;

		try {
			response = doResponse(url);
		} catch (ClientProtocolException e1) {
			result = " Failed " + e1.getLocalizedMessage();

			return result;
		} catch (IOException e1) {
			result = " Failed " + e1.getLocalizedMessage();
			return result;
		}

		if (response == null || !("".equals(result))) {
			return result+"nothing to show response is null";
		} else {

			try {
				  if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
				  {
					  //result = "Process Failed. " + response.getStatusLine().getReasonPhrase();
					  result = inputStreamToString(response.getEntity().getContent());
					  					  
					  return result;
				  }

				result = inputStreamToString(response.getEntity().getContent());

			} catch (IllegalStateException e) {
				Log.e(TAG, e.getLocalizedMessage(), e);

			} catch (IOException e) {
				Log.e(TAG, e.getLocalizedMessage(), e);
			}

		}

		return result;
	}

	@Override
	protected void onPostExecute(String response) {

		try {
			//activity.handleResponse(response);
			
			// Notify UI
			Message msg  = Message.obtain();
			
			Bundle b  = new  Bundle();
			msg.setData(b);
			msg.obj  = response;
			msg.what = 1;
			handler.handleMessage(msg);
			
			//pDlg.dismiss();
		} 
		catch (Exception e) {e.printStackTrace();
		}

	}

	private HttpResponse doResponse(String url) throws ClientProtocolException,
			IOException {
		
	     
		
		HttpClient httpclient = HttpsClientUtils.getHttpsClient();

		HttpResponse response = null;

		{
			switch (taskType) {

			case HttpsClientUtils.POST_TASK:
				HttpPost httpPost = new HttpPost(url);
				boolean paramsSet = false;
				if (this.params.size() > 0) {
					httpPost.setEntity(new UrlEncodedFormEntity(this.params));
					paramsSet = true;
				}

				if (jsonObject != null) {
					StringEntity stringEntity = new StringEntity(
							jsonObject.toString());
					stringEntity.setContentEncoding("UTF-8");
					stringEntity.setContentType("application/json");
					httpPost.setHeader("Accept", "application/json");
					httpPost.setHeader("Content-type", "application/json");
					//httpPost.setHeader("Authorization",getB64Auth());
					// Add parameters
					httpPost.setEntity(stringEntity);
					paramsSet = true;
				}

				if (paramsSet) {
					response = httpclient.execute(httpPost);
				}
				break;
			    case HttpsClientUtils.GET_TASK:
				HttpGet httpget = new HttpGet(url);
				response = httpclient.execute(httpget);
				break;
			}
			//httpclient.getConnectionManager().shutdown();
		}
		
		return response;
	}

	private String inputStreamToString(InputStream is) {

		String line = "";
		StringBuilder total = new StringBuilder();

		// Wrap a BufferedReader around the InputStream
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));

		try {
			// Read response until the end
			while ((line = rd.readLine()) != null) {
				total.append(line);
			}
		} catch (IOException e) {
			Log.e(TAG, e.getLocalizedMessage(), e);
		}

		// Return full string
		return total.toString();
	}
}