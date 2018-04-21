package com.justhire.mobile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.justhire.common.GPSLocationFinder;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public abstract class JustHireActivity extends Activity {

	public static final String DATEFORMAT = "dd/MM/yy";
	
	public static final String DATEANDTIMEFORMAT = "dd/MM/yy HH:mm";
	
	protected boolean gpsLoaded = false;
	public abstract void handleResponse(String response);
	public abstract void callService(Fragment activeFragement, String params);

	public void hideKeyboard() {

		// InputMethodManager inputManager = (InputMethodManager) this
		// .getSystemService(Context.INPUT_METHOD_SERVICE);

		// inputManager.hideSoftInputFromWindow(
		// this.getCurrentFocus()
		// .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	}
	
	public GPSLocationFinder getGPSLocationFinder(Context ctx)
	{
		GPSLocationFinder locationFinder = new GPSLocationFinder(ctx);
		return locationFinder;
	}
	
	protected boolean isInternetUp() {
		NetworkInfo info = ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE))
				.getActiveNetworkInfo();
		if (info == null || !info.isConnected()) {
			return false;
		}
		if (info.isRoaming()) {
			// if you want to disable Internet while roaming, just return false
			return true;
		}
		return true;
	}

	
	protected void showNoGpsAlertDialog() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(
				"Please enable GPS and Wireless Location on your device to read the location., do you want to enable it?")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(final DialogInterface dialog,
									final int id) {
								startActivity(new Intent(
										android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(final DialogInterface dialog,
							final int id) {
						dialog.cancel();
					}
				});
		final AlertDialog alert = builder.create();
		alert.show();
	}
	
	protected void showNoInternetAlertDialog() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(
				"The device has no Internet Connection., do you want to enable it?")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(final DialogInterface dialog,
									final int id) {
								startActivity(new Intent(
										android.provider.Settings.ACTION_WIRELESS_SETTINGS));
							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(final DialogInterface dialog,
							final int id) {
						dialog.cancel();
					}
				});
		final AlertDialog alert = builder.create();
		alert.show();
	}
	
	protected String getFormattedTime(Date date)
	{
		String hr=String.valueOf(date.getHours());
		if(date.getHours() < 10)
		{
			hr = "0" + date.getHours();
		}
		
		String min = String.valueOf(date.getMinutes());
		if(date.getMinutes() < 10)
		{
			min = "0" + date.getMinutes();
		}
		return hr + " : " + min;
	}
	
	protected void showOKDialog(String message) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(message)
				.setCancelable(false)
				.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {
							public void onClick(final DialogInterface dialog,
									final int id) {
								startActivity(new Intent(
										android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
							}
					
				});
		final AlertDialog alert = builder.create();
		alert.show();
	}

	

	
	protected void validateDates(String startDateVal, String endDateVal)
			throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(DATEANDTIMEFORMAT,
				Locale.ENGLISH);
		long startDateTime = 0;
		long endDateTime = 0;
		sdf.setLenient(false);
		Date dateNow = Calendar.getInstance().getTime();
		dateNow.setSeconds(0);

		if (startDateVal == null && endDateVal != null) {
			throw new Exception("Please enter Booking From Date and Time "
					+ sdf.format(dateNow));
		}
		
		if (startDateVal != null) {
			try {
				startDateTime = sdf.parse(startDateVal).getTime();
				Date date = new Date(startDateTime);
				date.setMinutes(date.getMinutes() - 1);

				if (date.compareTo(dateNow) < 0) {
					dateNow.setHours(dateNow.getHours() + 1);
					throw new Exception(
							"Booking can be done 1 hour in advance. Please select date on or after  "
									+ sdf.format(dateNow));
				}

			} catch (ParseException e) {
				throw new Exception(
						"Invalid date format. Please enter in dd/MM/yy HH:mm format.");

			}

			if (endDateVal != null) {
				try {

					endDateTime = sdf.parse(endDateVal).getTime();
					Date date = new Date(endDateTime);

					if (new Date(startDateTime).compareTo(date) > 0) {
						throw new Exception(
								"Please select the date and time on or after : "
										+ startDateVal);
					}

				} catch (ParseException e) {
					throw new Exception(
							"Invalid date format. Please enter in dd/MM/yy HH:ss format.");
				}
			}
		}

	}
}
