package com.jh.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class RestClientServices {



    /* Call Web Service and Get Response */
    private HttpResponse getWebServiceResponse(String URL, ArrayList <NameValuePair> params)
    {
        HttpResponse httpResponse = null;
        try 
        {
            HttpParams httpParameters = new BasicHttpParams();

            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
            HttpPost httpPost = new HttpPost(URL);
            try 
            {
                httpPost.setEntity(new UrlEncodedFormEntity(params));
            } catch (UnsupportedEncodingException e) {
            }
            httpResponse = httpClient.execute(httpPost);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }       
        return httpResponse;
    }
    /* Get the Web Service response as a JSON Object */
    private JSONObject responseToJSON (HttpResponse httpResponse)
    {
        InputStream is = null;
        String json = "";
        JSONObject jObj = null;
        HttpEntity httpEntity  = null;
        try
        {
            if (httpResponse != null)
            {
                httpEntity = httpResponse.getEntity();
                try {
                    is = httpEntity.getContent();

                } catch (IllegalStateException e1) {
                    Log.v("a", "Error while calling web services " +e1);
                    e1.printStackTrace();
                } catch (IOException e1) {
                    Log.v("a", "Error while calling web services " +e1);
                    e1.printStackTrace();
                }           
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    reader.close();
                    // Closing the input stream will trigger connection release
                    is.close();
                    json = sb.toString();
                } catch (Exception e) {
                    Log.e("a", "Buffer Error. Error converting result " + e.toString());
                }

                // try parse the string to a JSON object
                try {
                    jObj = new JSONObject(json);
                    Log.v("a", "JSON Object is  " +jObj);
                } catch (JSONException e) {
                    Log.e("JSON Parser", "Error parsing data " + e.toString());
                }

            }
        }
        catch (Exception e)
        {
            Log.e("a", "Error while calling web services " +e);
        }
        finally
        {
            // Release the response
            try {

                if (httpEntity != null)
                {
                    httpEntity.consumeContent();
                }
                //httpResponse.getEntity().getContent().close();
            } catch (IllegalStateException e) {
                Log.e("a", "Error while calling web services " +e);
                e.printStackTrace();
            } catch (IOException e) {
                Log.e("a", "Error while calling web services " +e);
                e.printStackTrace();
            }       
        }


        // return JSON Object
        return jObj;
    }

    /* Call the web service and get JSON Object response */
    public JSONObject getResponseAsJSON (String URL, ArrayList <NameValuePair> params)
    {
        return responseToJSON(getWebServiceResponse(URL,params));
    }

}
