package com.justhire.common;

import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class GPSLocationFinder {

	protected LocationManager locationManager;
	protected Context mContext;
	protected double latitude;
	protected double longitude;
	protected boolean gpsEnabled, networkEnabled;
	protected NetLocationListnerImpl coarseListner = null;
	protected GPSLocationListnerImpl fineListner = null;
	private Timer timer;
	private static final int TWO_MINUTES = 1000 * 60 * 2;
	protected Location bestLocation;

	public GPSLocationFinder(Context mContext) {

		this.mContext = mContext;
		initLocationManager();
	}

	//TODO Handler implementation
	public GPSLocationFinder(Context mContext, Handler mHandler) {

		this(mContext);
	}

	public void initLocationManager() {
		locationManager = (LocationManager) mContext
				.getSystemService(Context.LOCATION_SERVICE);
		gpsEnabled = locationManager
				.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER);
		networkEnabled = locationManager
				.isProviderEnabled(android.location.LocationManager.NETWORK_PROVIDER);
		
	}

	public void initLocation() {
		Location location = null;
		Criteria locationCritera = null;

		if (gpsEnabled) {
			locationCritera = createFineCriteria();
			// if (fineListner == null)
			fineListner = new GPSLocationFinder.GPSLocationListnerImpl();
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30 * 1000, 5, fineListner);
			location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			// Initialize the location fields
			if (location != null) {
				this.updateBestLocation(location, false);
			}
		}

		if (networkEnabled) {
			locationCritera = createCoarseCriteria();
			// if (coarseListner == null)
			coarseListner = new GPSLocationFinder.NetLocationListnerImpl();
			locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 30 * 1000, 5,
					coarseListner);
			location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			// Initialize the location fields
			if (location != null) {
				this.updateBestLocation(location, false);
			}

		}

		if (timer == null) {
			timer = new Timer();
			timer.schedule(new LastLocation(), 35000);
		}
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @return the network_enabled
	 */
	public boolean isNetworkEnabled() {
		return networkEnabled;
	}

	/**
	 * @return the gps_enabled
	 */
	public boolean isGpsEnabled() {
		return gpsEnabled;
	}

	protected void updateBestLocation(Location location, boolean removeUpdates) {

		if (location != null) {
			boolean currentLocation = isBetterLocation(location);
			if (currentLocation) {

				bestLocation = location;
				latitude = bestLocation.getLatitude();
				longitude = bestLocation.getLongitude();
				
				if(removeUpdates)
				{
					if (timer != null)
						timer.cancel();
					if (coarseListner != null)
						locationManager.removeUpdates(coarseListner);
					if (fineListner != null)
						locationManager.removeUpdates(fineListner);
				}

			}
		}

	}

	public String getLocation() throws Exception {
		Geocoder geocoder;
		List<Address> addresses;
		geocoder = new Geocoder(mContext, Locale.getDefault());
		addresses = geocoder.getFromLocation(latitude, longitude, 1);
		String addressText = null;
		Address address;
		if (addresses != null && addresses.size() > 0) {
			address = addresses.get(0);
			int maxLines = address.getMaxAddressLineIndex();
			
			if(maxLines > 0 && maxLines < 2)
			{
				addressText = String.format(
					"%s, %s,%s",
					// If there's a street address, add it
					address.getMaxAddressLineIndex() > 0 ? address
							.getAddressLine(0) : "",
							// If there's a street address, add it
							address.getMaxAddressLineIndex() == 1 ? address
									.getAddressLine(1) : "",
					// Locality is usually a city
					address.getLocality()
			// ,
			// The postal code of the address
			// address.getPostalCode()
					);
			}
			else
			if(maxLines == 2)
			{
			addressText = String.format(
					"%s, %s, %s, %s",
					// If there's a street address, add it
					address.getMaxAddressLineIndex() > 0 ? address
							.getAddressLine(0) : "",
							address.getAddressLine(1),
							address.getAddressLine(2),
					address.getLocality()
					);
			}
			else
			if(maxLines == 3)
			{
				addressText = String.format(
						"%s, %s, %s, %s, %s",
						// If there's a street address, add it
						address.getMaxAddressLineIndex() > 0 ? address
								.getAddressLine(0) : "",
								address.getAddressLine(1),
								address.getAddressLine(2),
								address.getAddressLine(3),
						// Locality is usually a city
						address.getLocality()
					);
			}
		}

		return addressText;

	}

	public void removeLocationUpdates() {
		if (fineListner != null)
			locationManager.removeUpdates(fineListner);
		if (coarseListner != null)
			locationManager.removeUpdates(coarseListner);
		if (timer != null)
			timer.cancel();
	}

	/** this criteria will settle for less accuracy, high power, and cost */
	protected Criteria createCoarseCriteria() {

		Criteria c = new Criteria();
		c.setAccuracy(Criteria.ACCURACY_COARSE);
		c.setAltitudeRequired(false);
		c.setBearingRequired(false);
		c.setSpeedRequired(false);
		c.setCostAllowed(true);
		c.setPowerRequirement(Criteria.POWER_HIGH);
		return c;

	}

	/** this criteria needs high accuracy, high power, and cost */
	protected Criteria createFineCriteria() {

		Criteria c = new Criteria();
		c.setAccuracy(Criteria.ACCURACY_FINE);
		c.setAltitudeRequired(false);
		c.setBearingRequired(false);
		c.setSpeedRequired(false);
		c.setCostAllowed(true);
		c.setPowerRequirement(Criteria.POWER_HIGH);
		return c;

	}

	private class NetLocationListnerImpl implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			if (location != null) {
				updateBestLocation(location,false);
			}
		}

		@Override
		public void onProviderDisabled(String provider) {
			Log.d("Latitude", "disable");
		}

		@Override
		public void onProviderEnabled(String provider) {
			Log.d("Latitude", "enable");
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			Log.d("Latitude", "status");
		}

	}

	private class GPSLocationListnerImpl implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			if (location != null) {
				updateBestLocation(location,true);
			}
		}

		@Override
		public void onProviderDisabled(String provider) {
			Log.d("Latitude", "disable");
		}

		@Override
		public void onProviderEnabled(String provider) {
			Log.d("Latitude", "enable");
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			Log.d("Latitude", "status");
		}

	}

	// waits for 30 seconds for GPS
	private class LastLocation extends TimerTask {

		@Override
		public void run() {

			
			
			// Location net_loc = null, gps_loc = null;
			if (gpsEnabled) {
				Location gpsLocation = locationManager
						.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				updateBestLocation(gpsLocation, true);
			}
			
			if (networkEnabled) {
				Location netLocation = locationManager
						.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
				updateBestLocation(netLocation,true);

			}
			
			if (coarseListner != null)
				locationManager.removeUpdates(coarseListner);
			if (fineListner != null)
				locationManager.removeUpdates(fineListner);
			
			

		}
	}

	/**
	 * Determines whether one Location reading is better than the current
	 * Location fix
	 * 
	 * @param location
	 *            The new Location that you want to evaluate
	 * @param currentBestLocation
	 *            The current Location fix, to which you want to compare the new
	 *            one
	 */
	protected boolean isBetterLocation(Location location) {
		Location currentBestLocation = bestLocation;
		if (currentBestLocation == null) {
			// A new location is always better than no location
			return true;
		}

		if (location == null) {
			// A old location is the only way if no new location
			return false;
		}
		
		//always prefer GPS reading first.
		if(gpsEnabled && android.location.LocationManager.GPS_PROVIDER.equals(location.getProvider()))
		{
			return true;
		}
		

		// Check whether the new location fix is newer or older
		long timeDelta = location.getTime() - currentBestLocation.getTime();
		boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
		boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
		boolean isNewer = timeDelta > 0;

		// If it's been more than two minutes since the current location, use
		// the new location
		// because the user has likely moved
		if (isSignificantlyNewer) {
			return true;
			// If the new location is more than two minutes older, it must be
			// worse
		} else if (isSignificantlyOlder) {
			return false;
		}

		// Check whether the new location fix is more or less accurate
		int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation
				.getAccuracy());
		boolean isLessAccurate = accuracyDelta > 0;
		boolean isMoreAccurate = accuracyDelta < 0;
		boolean isSignificantlyLessAccurate = accuracyDelta > 200;

		// Check if the old and new location are from the same provider
		boolean isFromSameProvider = isSameProvider(location.getProvider(),
				currentBestLocation.getProvider());

		// Determine location quality using a combination of timeliness and
		// accuracy
		if (isMoreAccurate) {
			return true;
		} else if (isNewer && !isLessAccurate) {
			return true;
		} else if (isNewer && !isSignificantlyLessAccurate
				&& isFromSameProvider) {
			return true;
		}
		return false;
	}

	/** Checks whether two providers are the same */
	private boolean isSameProvider(String provider1, String provider2) {
		if (provider1 == null) {
			return provider2 == null;
		}
		return provider1.equals(provider2);
	}

}
