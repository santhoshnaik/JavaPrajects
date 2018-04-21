package com.justhire.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

public class DownLoadImageRESTTask extends AsyncTask<String, Integer, Bitmap> {

	private Context mContext;
	private ProgressDialog pDlg = null;

	public DownLoadImageRESTTask(Context context) {
		this.mContext = context;
	}
	
	private void showProgressDialog() {

		pDlg = new ProgressDialog(mContext);
		pDlg.setMessage("Loading Please Wait....");
		pDlg.setProgressDrawable(mContext.getWallpaper());
		pDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pDlg.setCancelable(false);
		pDlg.show();

	}
	protected void onPreExecute() {
		showProgressDialog();
	}

	protected Bitmap doInBackground(String... urls) {
		Log.v("doInBackground", "Downloading Image....");
		return downloadImage(urls);
	}

	protected void onProgressUpdate(Integer... progress) {
		Log.v("onProgressUpdate", "Progress so far: " + progress[0]);
	}

	protected void onPostExecute(Bitmap result) {
		
		pDlg.dismiss();
	}

	private Bitmap downloadImage(String... urls) {
		HttpClient httpClient = HttpsClientUtils.getHttpsClient();

		try {

			HttpGet request = new HttpGet(urls[0]);
			HttpParams params = new BasicHttpParams();
			HttpConnectionParams.setSoTimeout(params, 60000);
			request.setParams(params);
			publishProgress(25);
			HttpResponse response = httpClient.execute(request);
		    final int statusCode = response.getStatusLine().getStatusCode();

			 if (statusCode != HttpStatus.SC_OK) {
                 Log.w("ImageDownLoadRESTTask", "Error " + statusCode +
                         " while downloading Image ");
                 return null;

             }
			publishProgress(50);
			
			byte[] image = EntityUtils.toByteArray(response.getEntity());
			publishProgress(75);
			Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
			publishProgress(100);

			return bitmap;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
